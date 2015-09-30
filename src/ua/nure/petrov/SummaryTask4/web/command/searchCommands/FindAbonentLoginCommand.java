package ua.nure.petrov.SummaryTask4.web.command.searchCommands;

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
import ua.nure.petrov.SummaryTask4.db.entity.Abonent;
import ua.nure.petrov.SummaryTask4.db.entity.Payment;
import ua.nure.petrov.SummaryTask4.web.command.Command;

/**
 * Admin command to find abonents in the DB by login
 * 
 * @author Petrov Vladyslav
 * 
 */
public class FindAbonentLoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(FindAbonentLoginCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String forward = Path.PAGE_ABONENTS;

		HttpSession session = request.getSession();
		session.removeAttribute("back");
		
		AbstractDAO dao = new AbstractDAO();

		String login = request.getParameter("login");

		List<Abonent> abonents = null;
		List<Payment> payment = new ArrayList<Payment>();

		if (login.isEmpty()) {
			response.sendRedirect(request.getServletContext().getContextPath() + Path.COMMAND_ABONENT);
			return null;
		} else {
			abonents = dao.findAbonentsByLogin(login);
			for (Abonent a : abonents) {
				if (dao.findPaymentById(a.getIdPayment()).getId() != 0) {
					payment.add(dao.findPaymentById(a.getIdPayment()));
				}
			}
		}

		for (Payment p : payment) {
			p.setBalance(Round.roundResult(p.getBalance(), 2));
		}
		
		request.setAttribute("payment", payment);
		request.setAttribute("abonents", abonents);
		request.setAttribute("clear", "CLEAR");
		
		LOG.debug("Find by login command finished");

		return forward;
	}
}