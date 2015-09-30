package ua.nure.petrov.SummaryTask4.web.command.modifyCommands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.entity.Abonent;
import ua.nure.petrov.SummaryTask4.db.entity.Contract;
import ua.nure.petrov.SummaryTask4.db.entity.Payment;
import ua.nure.petrov.SummaryTask4.db.entity.Tariff;
import ua.nure.petrov.SummaryTask4.db.entity.TariffContract;
import ua.nure.petrov.SummaryTask4.web.command.Command;

/**
 * Admin command to delete selected service from abonent contract
 * @author Petrov Vladyslav
 *
 */
public class RemoveServiceCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(RemoveServiceCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		
		AbstractDAO dao = new AbstractDAO();

		int idTariff = Integer.parseInt(request.getParameter("idTariff"));
		int idContract = Integer.parseInt(request.getParameter("idContract"));

		Tariff tariff = dao.findTariffById(idTariff);
		Contract contract = dao.findContractById(idContract);
		Abonent abonent = dao.findAbonentById(contract.getIdAbonent());
		Payment payment = dao.findPaymentById(abonent.getIdPayment());
		
		dao.deleteTariffFromContractById(tariff, contract);
		LOG.trace("Deactivation completed");
		
		List<TariffContract> tarcontr = dao
				.findTariffContractByIdContract(contract);

		double sum = 0;
		int count = 0;
		
		for (TariffContract t : tarcontr) {
			Tariff tar = dao.findTariffById(t.getIdTariff());
			sum += tar.getPrice();
			count++;
		}

		payment.setEndDateService("");
		dao.updatePaymentDate(payment);
		
		if(count == 0) {
			payment.setStatus(false);
			payment.setLastDateOfWithdrawal("");
			dao.updatePayment(payment);
		} else {
			int days = (int) ((payment.getBalance() * 30) / sum);
			if (payment.getBalance() != 0) {
				dao.addDatePaymentById(days, payment);
			}
		}
		
		LOG.debug("Update abonent payment completed");
		
		session.setAttribute("countTariffs", count);
		response.sendRedirect(request.getHeader("referer"));
		
		return null;
	}

}