
package ua.nure.petrov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.Role;
import ua.nure.petrov.SummaryTask4.db.entity.Abonent;
import ua.nure.petrov.SummaryTask4.db.entity.Administrator;
import ua.nure.petrov.SummaryTask4.db.entity.Payment;

/**
 * Login command.
 * 
 * @author Vladyslav Petrov
 * 
 */
public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger
			.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		AbstractDAO dao = new AbstractDAO();

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		if (login.isEmpty() || password.isEmpty()) {
			response.sendRedirect(request.getServletContext().getContextPath()
					+ Path.COMMAND_RELOGIN);
			return null;
		}

		Role role = Role.getRole(login);

		if (dao.checkLoginPassword(login, password)) {

			Administrator admin = dao.findAdminByLogin(login);

			if (role == Role.ADMIN) {
				Config.set(session, "javax.servlet.jsp.jstl.fmt.locale",
						admin.getLocale());
				response.sendRedirect(request.getServletContext()
						.getContextPath() + Path.PAGE_LOGIN);
				session.setAttribute("defaultLocale", admin.getLocale());
			}

			if (role == Role.CLIENT) {
				Abonent abonent = dao.findAbonentByLogin(login);
				Payment payment = dao.findPaymentById(abonent.getIdPayment());
				session.setAttribute("status", payment.getStatus());
				Config.set(session, "javax.servlet.jsp.jstl.fmt.locale",
						abonent.getLocale());
				response.sendRedirect(request.getServletContext()
						.getContextPath() + Path.COMMAND_ABONENT_ACCOUNT);
				session.setAttribute("defaultLocale", abonent.getLocale());
			}
			
			LOG.debug("Log In with userRole = " + role + ", user = " + login);

		} else {
			response.sendRedirect(request.getServletContext().getContextPath()
					+ Path.COMMAND_RELOGIN);
			return null;
		}

		session.setAttribute("user", login);
		session.setAttribute("userRole", role.getName());

		LOG.debug("Command finished");

		return null;
	}
}