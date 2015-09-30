package ua.nure.petrov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;

/**
 * Admin command to open contract information for all of the users
 * 
 * @author Petrov Vladyslav
 * 
 */
public class ContractCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(ContractCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.setAttribute("contracts", new AbstractDAO().findContract());
		request.setAttribute("tariffContract",
				new AbstractDAO().findTariffContract());

		LOG.debug("Command finished");

		return Path.PAGE_CONTRACTS;
	}

}