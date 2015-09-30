package ua.nure.petrov.SummaryTask4.web.command.modifyCommands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.web.command.Command;


/**
 * Admin command to go to the change tariff page
 * 
 * @author Petrov Vladyslav
 */
public class ChangeCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(ChangeCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String idModify = request.getParameter("idModify");
		int id = Integer.parseInt(idModify);

		request.setAttribute("tariff", new AbstractDAO().findTariffById(id));
		LOG.debug("Command finished");
		return Path.PAGE_CHANGE;
	}

}