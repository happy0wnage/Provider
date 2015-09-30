package ua.nure.petrov.SummaryTask4.web.command.sortCommands;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.CompareTariffs;
import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.entity.Tariff;
import ua.nure.petrov.SummaryTask4.web.command.Command;

/**
 * Sort tariffs by id command.
 * 
 * @author Petrov Vladyslav
 * 
 */
public class SortIdCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(SortIdCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String forward = Path.PAGE_TARIFF;
		
		AbstractDAO dao = new AbstractDAO();

		List<Tariff> sortedTariffs = dao.findTariff();

		HttpSession session = request.getSession();

		String flag1 = String.valueOf(session.getAttribute("flag"));
		int flag = Integer.parseInt(flag1);

		if (flag == 1) {
			Collections.sort(sortedTariffs, CompareTariffs.SORT_BY_ID);
			flag = 0;
		} else {
			Collections.sort(sortedTariffs, CompareTariffs.SORT_BY_ID_REVERSE);
			flag = 1;
		}

		session.setAttribute("flag", flag);

		request.setAttribute("tariffs", sortedTariffs);

		//response.sendRedirect(request.getHeader("referer"));
		
		LOG.debug("Sort command finished");

		return forward;
	}

}
