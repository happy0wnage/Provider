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
 * 
 * Sort tariffs by name command
 * 
 * @author Petrov Vladyslav
 * 
 */
public class SortNameCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(SortNameCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String forward = null;
		AbstractDAO dao = new AbstractDAO();
		forward = Path.PAGE_TARIFF;
		List<Tariff> sortedTariffs = dao.findTariff();

		HttpSession session = request.getSession();

		String flag1 = String.valueOf(session.getAttribute("flag"));
		int flag = Integer.parseInt(flag1);

		if (flag == 1) {
			Collections.sort(sortedTariffs, CompareTariffs.SORT_BY_NAME);
			flag = 0;
		} else {
			Collections
					.sort(sortedTariffs, CompareTariffs.SORT_BY_NAME_REVERSE);
			flag = 1;
		}

		session.setAttribute("flag", flag);

		request.setAttribute("tariffs", sortedTariffs);
		
		//response.sendRedirect(request.getHeader("referer"));
		
		LOG.debug("Sort command finished");
		
		return forward;
	}

}
