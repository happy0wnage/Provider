package ua.nure.petrov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;

/**
 * Admin command to open information about services in the system
 * 
 * @author Petrov Vladyslav
 * 
 */
public class ServiceCommand extends Command {
	
	private static final long serialVersionUID = -3071536593627692473L;
	
	private static final Logger LOG = Logger.getLogger(ServiceCommand.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {		

		request.setAttribute("services", new AbstractDAO().findService());
		LOG.debug("Command finished");
		return Path.PAGE_SERVICES;
	}

}