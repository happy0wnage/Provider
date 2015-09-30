package ua.nure.petrov.SummaryTask4.web.command.searchCommands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.entity.Service;
import ua.nure.petrov.SummaryTask4.web.command.Command;

/**
 * Admin command to find services in the system by id
 * 
 * @author Petrov Vladyslav
 * 
 */
public class FindServiceCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger
			.getLogger(FindServiceCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String forward = Path.PAGE_SERVICES;

		AbstractDAO dao = new AbstractDAO();

		HttpSession session = request.getSession();

		List<Service> service = new ArrayList<Service>();

		int id = 0;

		try {
			id = Integer.parseInt(request.getParameter("idService"));
			service.add(dao.findServiceById(id));
			LOG.debug("ID Service --> " + id);
			System.out.println(service);

		} catch (Exception e) {
			service = dao.findService();
		}

		request.setAttribute("services", service);

		session.setAttribute("back", "Previous page");

		LOG.debug("Find service by id command finished");

		return forward;
	}
}