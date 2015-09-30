package ua.nure.petrov.SummaryTask4.web.listener;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.nure.petrov.SummaryTask4.Round;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.entity.Abonent;
import ua.nure.petrov.SummaryTask4.db.entity.Contract;
import ua.nure.petrov.SummaryTask4.db.entity.Payment;
import ua.nure.petrov.SummaryTask4.db.entity.Tariff;
import ua.nure.petrov.SummaryTask4.db.entity.TariffContract;

public class ContextListener implements ServletContextListener {

	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	public void contextInitialized(ServletContextEvent sce) {

		log("Servlet context initialization starts");

		ServletContext servletContext = sce.getServletContext();
		initLog4J(servletContext);
		initCommandContainer();
		initI18N(servletContext);

		log("Servlet context initialization finished");

		BalanceThread thread = new BalanceThread();
		thread.setDaemon(true);
		thread.start();
	}

	private void initLog4J(ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			PropertyConfigurator.configure(servletContext
					.getRealPath("WEB-INF/log4j.properties"));
		} catch (Exception ex) {
			LOG.error("Cannot configure Log4j", ex);
		}
		log("Log4J initialization finished");
		LOG.debug("Log4j has been initialized");
	}

	private void initI18N(ServletContext servletContext) {
		LOG.debug("I18N subsystem initialization started");

		String localesValue = servletContext.getInitParameter("locales");
		if (localesValue == null || localesValue.isEmpty()) {
			LOG.warn("'locales' init parameter is empty, the default encoding will be used");
		} else {
			List<String> locales = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(localesValue);
			while (st.hasMoreTokens()) {
				String localeName = st.nextToken();
				locales.add(localeName);
			}

			LOG.debug("Application attribute set: locales --> " + locales);
			servletContext.setAttribute("locales", locales);
			
		}

		LOG.debug("I18N subsystem initialization finished");
	}

	public class BalanceThread extends Thread {
		public void run() {
			while (true) {
				AbstractDAO dao = new AbstractDAO();
				try {
					log("Starting checking last date of withdrawal. Time: "
							+ new java.util.Date());
					double minus = 0;
					List<Payment> payment = dao.findPayment();

					for (Payment p : payment) {
						Date withdrawal = p.getLastDateOfWithdrawal();

						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy-MM-dd");

						Calendar calendar = Calendar.getInstance();
						calendar.add(Calendar.DATE, -1);
						Date yesterday = Date.valueOf(sdf.format(calendar
								.getTime()));

						if (withdrawal.equals(yesterday) && p.getStatus()) {
							System.out.println(withdrawal);
							System.out.println(yesterday);
							double sum = 0;
							Abonent abonent = dao.findAbonentByIdPayment(p);
							Contract contract = dao
									.findContractByIdAbonent(abonent);
							List<TariffContract> tc = dao
									.findTariffContractByIdContract(contract);

							for (TariffContract t : tc) {
								Tariff tar = dao
										.findTariffById(t.getIdTariff());
								sum += tar.getPrice();
							}

							minus = Round.roundResult(sum / 30, 2);
							double newBalance = Round.roundResult(
									p.getBalance() - minus, 2);

							if (p.checkBalance(newBalance)) {
								p.setBalance(newBalance);
								log("Withdrawal: " + minus
										+ " UAH the client: "
										+ abonent.getLogin() + "\t Time: "
										+ new java.util.Date());

							} else {
								p.setStatus(false);
								p.setBalance(0);
								dao.updatePaymentStatus(p);
							}

							p.setLastDateOfWithdrawal("");
							dao.updatePaymentWithdrawalDate(p);
						}
					}

					long time = 3600 * 1000;
					// long time = 10000;
					// long time = 24*3600*1000;
					Thread.sleep(time);

				} catch (InterruptedException e) {
					LOG.error(e);
				}
			}
		}
	}

	private void initCommandContainer() {

		try {
			Class.forName("ua.nure.petrov.SummaryTask4.web.command.CommandContainer");
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException(
					"Cannot initialize Command Container");
		}
	}

	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log("Servlet context destruction finished");
	}

}
