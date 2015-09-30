package ua.nure.petrov.SummaryTask4.web.command.modifyCommands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.web.command.Command;

/**
 * Admin command to delete selected tariff from Data Base
 * 
 * @author Petrov Vladyslav
 * 
 */
public class DeleteTariffCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger
			.getLogger(DeleteTariffCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		AbstractDAO dao = new AbstractDAO();

		int id = Integer.parseInt(request.getParameter("idDelete"));
		dao.deleteTariffById(id);
		LOG.debug("Delete tariff completed");

		response.sendRedirect(request.getHeader("referer"));
		LOG.debug("Command finished");
		return null;
	}

}