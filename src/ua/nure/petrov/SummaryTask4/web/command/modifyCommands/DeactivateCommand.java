package ua.nure.petrov.SummaryTask4.web.command.modifyCommands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.entity.Payment;
import ua.nure.petrov.SummaryTask4.web.command.Command;

/**
 * Admin command to deactivate user (Change status to 0)
 * 
 * @author Petrov Vladyslav
 *
 */
public class DeactivateCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(DeactivateCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		AbstractDAO dao = new AbstractDAO();

		int idPayment = Integer.parseInt(request.getParameter("idPayment"));

		Payment payment = dao.findPaymentById(idPayment);

		payment.setStatus(false);
		
		dao.updatePaymentStatus(payment);
		
		LOG.debug("Deactivate payment completed");
		
		response.sendRedirect(request.getHeader("referer"));
		
		return null;
	}

}