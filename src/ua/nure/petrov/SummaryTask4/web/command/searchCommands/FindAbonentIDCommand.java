package ua.nure.petrov.SummaryTask4.web.command.searchCommands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.Round;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.entity.Abonent;
import ua.nure.petrov.SummaryTask4.db.entity.Payment;
import ua.nure.petrov.SummaryTask4.web.command.Command;

/**
 * Admin command to find abonents in the DB by id.
 * 
 * @author Petrov Vladyslav
 * 
 */
public class FindAbonentIDCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger
			.getLogger(FindAbonentIDCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String forward = Path.PAGE_ABONENTS;

		AbstractDAO dao = new AbstractDAO();

		List<Abonent> abonent = null;
		List<Payment> payment = new ArrayList<Payment>();

		int id = 0;

		try {
			id = Integer.parseInt(request.getParameter("idAbonent"));
			abonent = dao.findAbonentsById(id);

			for (Abonent a : abonent) {
				if (dao.findPaymentById(a.getIdPayment()).getId() != 0) {
					payment.add(dao.findPaymentById(a.getIdPayment()));
				}
			}
		} catch (Exception e) {
			response.sendRedirect(request.getServletContext().getContextPath()
					+ Path.COMMAND_ABONENT);
			return null;
		}

		for (Payment p : payment) {
			p.setBalance(Round.roundResult(p.getBalance(), 2));
		}

		request.setAttribute("abonents", abonent);
		request.setAttribute("payment", payment);
		request.setAttribute("clear", "CLEAR");

		LOG.debug("Find by id command finished");

		return forward;
	}
}