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
import ua.nure.petrov.SummaryTask4.db.entity.Service;
import ua.nure.petrov.SummaryTask4.db.entity.Tariff;
import ua.nure.petrov.SummaryTask4.db.entity.TariffContract;

/**
 * 
 * Abonent command to choice service to connect
 * 
 * @author Petrov Vladyslav
 * 
 */
public class ConnectionChoiceCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger
			.getLogger(ConnectionChoiceCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		SelectLocale locale = new SelectLocale();

		String redir = request.getServletContext().getContextPath()
				+ Path.COMMAND_CONNECTION;

		int idTariff = Integer.parseInt(request.getParameter("idTariff"));
		String login = (String) session.getAttribute("user");

		AbstractDAO dao = new AbstractDAO();

		Abonent abonent = dao.findAbonentByLogin(login);
		Payment payment = dao.findPaymentById(abonent.getIdPayment());

		Contract contract = dao.findContractByIdAbonent(abonent);
		Tariff tariff = dao.findTariffById(idTariff);
		Service service = dao.findServiceById(tariff.getIdService());

		List<TariffContract> tariffContract = dao
				.findTariffContractByIdContract(contract);

		for (TariffContract tc : tariffContract) {
			Tariff t = dao.findTariffById(tc.getIdTariff());
			Service s = dao.findServiceById(t.getIdService());

			if (s.getName().equals(service.getName())) {
				locale.setRu("Вы не можете подключиться к: "
						+ service.getName());
				locale.setEn("You can't connected to the: " + service.getName());

				session.setAttribute("errormessage", locale.getMessage(session));
				LOG.error("ErrorMessage:     "
						+ session.getAttribute("errormessage"));
				response.sendRedirect(redir);
				return null;
			}
		}

		if (contract.getId() == 0) {
			int number = contract.generateNumber();
			contract.setNumber(number);
			contract.setIdAbonent(abonent.getId());
			contract.setStartDate("");
			contract.setEndDate("");
			dao.insertContract(contract);
		}

		contract = dao.findContractByIdAbonent(abonent);

		TariffContract tc = new TariffContract();
		tc.setIdContract(contract.getId());
		tc.setIdTariff(idTariff);
		dao.insertTariffContract(tc);

		List<TariffContract> tarcontr = dao
				.findTariffContractByIdContract(contract);

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
		}

		locale.setEn("You have been successfully connected to the "
				+ service.getName());
		locale.setRu("Вы успешно подключились к услуге: " + service.getName());
		session.setAttribute("message", locale.getMessage(session));
		LOG.error("Message:     " + session.getAttribute("message"));

		response.sendRedirect(redir);
		return null;
	}
}