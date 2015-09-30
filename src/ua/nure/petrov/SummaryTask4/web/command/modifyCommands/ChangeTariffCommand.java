package ua.nure.petrov.SummaryTask4.web.command.modifyCommands;

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
import ua.nure.petrov.SummaryTask4.db.entity.Contract;
import ua.nure.petrov.SummaryTask4.db.entity.Payment;
import ua.nure.petrov.SummaryTask4.db.entity.Tariff;
import ua.nure.petrov.SummaryTask4.db.entity.TariffContract;
import ua.nure.petrov.SummaryTask4.web.command.Command;

/**
 * Admin command to change tariff
 * 
 * @author D.Kolesnikov
 * 
 */
public class ChangeTariffCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger
			.getLogger(ChangeTariffCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		int id = Integer.parseInt(request.getParameter("idModify"));
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String description = request.getParameter("description");

		SelectLocale locale = new SelectLocale();

		String redirModify = request.getHeader("referer");

		String redirTariff = request.getServletContext().getContextPath()
				+ Path.COMMAND_TARIFF;

		if (name.isEmpty() || price.isEmpty() || description.isEmpty()) {
			locale.setEn("Not all fields are filled");
			locale.setRu("Заполнены не все поля ввода");
			session.setAttribute("errorMessage", locale.getMessage(session));
			LOG.error("ErrorMessage:     "
					+ session.getAttribute("errorMessage"));
			response.sendRedirect(redirModify);
			return null;
		} else {
			
			AbstractDAO dao = new AbstractDAO();
			Tariff tariff = new Tariff();

			try {
				double money = Double.parseDouble(price);

				if (!tariff.checkPrice(money)) {
					locale.setEn("Price is less than 0");
					locale.setRu("Цена меньше 0");
					session.setAttribute("errorMessage",
							locale.getMessage(session));
					LOG.error("ErrorMessage:     "
							+ session.getAttribute("errorMessage"));
					response.sendRedirect(redirModify);
					return null;
				}

				tariff.setId(id);
				tariff.setName(name);
				tariff.setPrice(money);
				tariff.setDescription(description);
				dao.updateTariff(tariff);

				List<TariffContract> tc = dao
						.findTariffContractByIdTariff(tariff);

				for (TariffContract tariffContract : tc) {
					Contract c = dao.findContractById(tariffContract
							.getIdContract());
					Payment payment = dao.findPaymentByContract(c);

					List<TariffContract> tarcontr = dao
							.findTariffContractByIdContract(c);

					double sum = 0;

					for (TariffContract t : tarcontr) {
						Tariff tar = dao.findTariffById(t.getIdTariff());
						sum += tar.getPrice();
					}

					int days = (int) ((payment.getBalance() * 30) / sum);

					payment.setEndDateService("");
					dao.updatePaymentDate(payment);

					if (payment.getBalance() != 0) {
						dao.addDatePaymentById(days, payment);
						LOG.debug("Recalculation done");
						System.out.println(dao.findPaymentById(payment.getId()));
					}
				}
				System.out.println(dao.findPayment());

			} catch (NumberFormatException e) {
				locale.setEn("Incorrect price");
				locale.setRu("Неверная цена");
				session.setAttribute("errorMessage", locale.getMessage(session));
				LOG.error("ErrorMessage:     "
						+ session.getAttribute("errorMessage"));
				response.sendRedirect(redirModify);
				return null;
			}
		}

		response.sendRedirect(redirTariff);

		LOG.debug("Command finished");

		return null;
	}
}