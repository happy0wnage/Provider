package ua.nure.petrov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.entity.Abonent;

/**
 * Subscriber command to open their profile
 * 
 * @author Petrov Vladyslav
 * 
 */
public class ProfileCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(ProfileCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		String login = (String) session.getAttribute("user");

		AbstractDAO dao = new AbstractDAO();

		Abonent abonent = dao.findAbonentByLogin(login);

		request.setAttribute("abonent", abonent);
		LOG.debug("Command finiished");
		return Path.PAGE_ABONENT_PROFILE;
	}

}