package ua.nure.petrov.SummaryTask4.web.command.modifyCommands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.Role;
import ua.nure.petrov.SummaryTask4.db.entity.Abonent;
import ua.nure.petrov.SummaryTask4.db.entity.Administrator;
import ua.nure.petrov.SummaryTask4.web.command.Command;

/**
 * General command to select system language
 * 
 * @author Petrov Vladyslav
 * 
 */
public class UpdateSettingsCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger
			.getLogger(UpdateSettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Command starts");

		String localeToSet = request.getParameter("locale");

		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("user");
		AbstractDAO dao = new AbstractDAO();

		if (localeToSet != null && !localeToSet.isEmpty()) {

			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale",
					localeToSet);
			session.setAttribute("defaultLocale", localeToSet);

			try {
				String role = (String) session.getAttribute("userRole");

				if (!role.isEmpty()) {
					if (role.equalsIgnoreCase(Role.ADMIN.getName())) {
						Administrator admin = dao.findAdminByLogin(login);
						admin.setLocale(localeToSet);
						dao.updateAdminLocale(admin);
						LOG.trace("Admin locale was set: localeToSet --> "
								+ localeToSet);
					} else if (role.equalsIgnoreCase(Role.CLIENT.getName())) {
						Abonent abonent = dao.findAbonentByLogin(login);
						abonent.setLocale(localeToSet);
						dao.updateAbonentLocale(abonent);
						LOG.trace("User locale was set: localeToSet --> "
								+ localeToSet);
					}
				}
			} catch (NullPointerException e) {
				LOG.debug("Setting locale without DB update!");
			}
		}

		response.sendRedirect(request.getHeader("referer"));
		LOG.debug("Command finished");

		return null;
	}
}
