package ua.nure.petrov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


import ua.nure.petrov.SummaryTask4.Path;

/**
 * Logout command.
 * 
 * @author Vladyslav Petrov
 * 
 */
public class LogoutCommand extends Command {

	private static final long serialVersionUID = -2785976616686657267L;

	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		
		response.sendRedirect(request.getServletContext().getContextPath() + Path.PAGE_LOGIN);
		LOG.debug("Command finished");
		return null;
	}

}