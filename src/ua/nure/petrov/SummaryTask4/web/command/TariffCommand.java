package ua.nure.petrov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;

/**
 * Admin command to open information about tariffs in the system
 * @author Petrov Vladyslav
 *
 */
public class TariffCommand extends Command {
	
	private static final long serialVersionUID = -3071536593627692473L;
	
	private static final Logger LOG = Logger.getLogger(TariffCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {		

		String forward = Path.PAGE_TARIFF;

		HttpSession session = request.getSession();
		
		session.setAttribute("flag", 1);
		
		request.setAttribute("tariffs", new AbstractDAO().findTariff());
		
		LOG.debug("Command finished");
		
		return forward;
	}

}