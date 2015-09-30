package ua.nure.petrov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.SelectLocale;
import ua.nure.petrov.SummaryTask4.db.entity.Abonent;
import ua.nure.petrov.SummaryTask4.db.entity.Contract;
import ua.nure.petrov.SummaryTask4.db.entity.Payment;
import ua.nure.petrov.SummaryTask4.db.entity.Tariff;
import ua.nure.petrov.SummaryTask4.db.entity.TariffContract;

/**
 * Pay command
 * 
 * @author Vladyslav Petrov
 * 
 */
public class PayCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(PayCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		int idAbonent = Integer.parseInt(request.getParameter("idAbonent"));
		String value = request.getParameter("amount");

		HttpSession session = request.getSession();

		SelectLocale locale = new SelectLocale();

		String redir = request.getServletContext().getContextPath()
				+ Path.COMMAND_ABONENT_ACCOUNT;

		if (value.isEmpty()) {
			locale.setEn("Field is not filled");
			locale.setRu("Заполнены не все поля ввода");
			session.setAttribute("errorMessage", locale.getMessage(session));
			LOG.error("ErrorMessage:     "
					+ session.getAttribute("errorMessage"));
			response.sendRedirect(redir);
			return null;
		} else {

			AbstractDAO dao = new AbstractDAO();

			Abonent abonent = dao.findAbonentById(idAbonent);

			Payment payment = dao.findPaymentById(abonent.getIdPayment());

			double money = 0;

			try {
				money = Double.parseDouble(value);
			} catch (Exception e) {
				locale.setEn("Incorrect value!");
				locale.setRu("Неверное значение!");
				session.setAttribute("errorMessage", locale.getMessage(session));
				LOG.error("ErrorMessage:     "
						+ session.getAttribute("errorMessage"));
				response.sendRedirect(redir);
				return null;
			}

			Contract c = dao.findContractByIdAbonent(abonent);

			List<TariffContract> tarcontr = dao
					.findTariffContractByIdContract(c);

			double sum = 0;

			for (TariffContract t : tarcontr) {
				Tariff tar = dao.findTariffById(t.getIdTariff());
				sum += tar.getPrice();
			}

			double payPerDay = Math.rint(100.0 * (sum / 30)) / 100.0;

			if (money < payPerDay) {
				locale.setEn("Minimal payment is " + payPerDay + " UAH ");
				locale.setRu("Минимальное пополнение: " + payPerDay + " UAH ");
				session.setAttribute("errorMessage", locale.getMessage(session));
				LOG.error("ErrorMessage:     "
						+ session.getAttribute("errorMessage"));
				response.sendRedirect(redir);
				return null;
			}

			payment.addBalance(money);
			int days = (int) ((payment.getBalance() * 30) / sum);
			payment.setEndDateService("");
			payment.setLastDateOfWithdrawal("");
			dao.updatePaymentDate(payment);
			dao.updatePaymentWithdrawalDate(payment);

			if (payment.getBalance() != 0) {
				dao.addDatePaymentById(days, payment);
				locale.setEn("Successful replenishment");
				locale.setRu("Успешное пополнение");
				session.setAttribute("message", locale.getMessage(session));
				LOG.error("Message:     " + session.getAttribute("message"));

				response.sendRedirect(redir);
				return null;
			}
			session.setAttribute("status", payment.getStatus());
		}

		response.sendRedirect(redir);

		LOG.debug("Command finished");
		return null;
	}
}