package ua.nure.petrov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.entity.Abonent;
import ua.nure.petrov.SummaryTask4.db.entity.Payment;

/**
 * The admin command to view information about all registered users in the
 * system.
 * 
 * @author Petrov Vladyslav
 * 
 */
public class AbonentCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(AbonentCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		AbstractDAO dao = new AbstractDAO();
		List<Abonent> a = dao.findAbonent();
		List<Payment> p = dao.findPayment();

		for (Payment payment : p) {
			payment.setBalance(Math.rint(100.0 * payment.getBalance()) / 100.0);
		}

		request.setAttribute("abonents", a);
		request.setAttribute("payment", p);

		LOG.debug("Command finished");

		return Path.PAGE_ABONENTS;
	}

}