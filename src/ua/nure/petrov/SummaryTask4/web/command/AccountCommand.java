package ua.nure.petrov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.Round;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.SelectLocale;
import ua.nure.petrov.SummaryTask4.db.ServiceTariffContainer;
import ua.nure.petrov.SummaryTask4.db.entity.Abonent;
import ua.nure.petrov.SummaryTask4.db.entity.Contract;
import ua.nure.petrov.SummaryTask4.db.entity.Payment;
import ua.nure.petrov.SummaryTask4.db.entity.Service;
import ua.nure.petrov.SummaryTask4.db.entity.Tariff;

/**
 * 
 * Subscriber command to open a personal account. In the personal account user
 * can view the status of your account expiration date services and replenish
 * personal account.
 * 
 * @author Petrov Vladyslav
 * 
 */
public class AccountCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(AccountCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		SelectLocale locale = new SelectLocale();

		String login = (String) session.getAttribute("user");

		AbstractDAO dao = new AbstractDAO();

		Abonent abonent = dao.findAbonentByLogin(login);
		Payment payment = dao.findPaymentById(abonent.getIdPayment());

		Contract contract = dao.findContractByIdAbonent(abonent);

		if (contract.getId() == 0 || payment.getId() == 0) {
			return Path.COMMAND_CONNECTION;
		}

		List<Tariff> tariff = dao.findTariffsByIdContract(contract);

		double sum = 0;

		List<ServiceTariffContainer> stc = new ArrayList<ServiceTariffContainer>();

		int count = 0;

		for (Tariff t : tariff) {
			ServiceTariffContainer serviceTariffContainer = new ServiceTariffContainer();

			Service s = dao.findServiceById(t.getIdService());

			serviceTariffContainer.setServiceName(s.getName());
			serviceTariffContainer.setTariffName(t.getName());
			serviceTariffContainer.setTariffPrice(t.getPrice());

			stc.add(serviceTariffContainer);
			sum += t.getPrice();
			count++;
		}

		LOG.trace("Number of services --> " + count);
		LOG.trace("Total value --> " + sum + " UAH");

		double moneyPerDay = Math.rint(100.0 * (sum / 30)) / 100.0;
		payment.setBalance(Round.roundResult(payment.getBalance(), 2));

		request.setAttribute("perDay", moneyPerDay);
		request.setAttribute("abonent", abonent);
		request.setAttribute("payment", payment);
		request.setAttribute("contract", contract);
		request.setAttribute("serviceTariffContainer", stc);
		request.setAttribute("totalPrice", sum);

		if (!payment.getStatus()) {
			locale.setEn("You have to fill up the balance to unblock your account");
			locale.setRu("Вы должны пополнить баланс для разблокировки аккаунта");
			request.setAttribute("errorMessage_1", locale.getMessage(session));
		}

		session.setAttribute("countTariffs", count);
		LOG.debug("Command finished");
		return Path.PAGE_ABONENT_ACCOUNT;

	}

}