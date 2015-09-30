package ua.nure.petrov.SummaryTask4.web.command.modifyCommands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.SelectLocale;
import ua.nure.petrov.SummaryTask4.db.entity.Abonent;
import ua.nure.petrov.SummaryTask4.web.command.Command;

/**
 * Subscriber command to change the password
 * 
 * @author Petrov Vladyslav
 * 
 */
public class ChangeAbonentProfilePasswordCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger
			.getLogger(ChangeAbonentProfilePasswordCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		String curPass = request.getParameter("curPass");
		String newPass = request.getParameter("newPass");
		int id = Integer.parseInt(request.getParameter("idAbonent"));

		SelectLocale locale = new SelectLocale();

		String redir = request.getServletContext().getContextPath()
				+ Path.COMMAND_ABONENT_PROFILE;

		AbstractDAO dao = new AbstractDAO();

		if (curPass.isEmpty() || newPass.isEmpty()) {
			locale.setEn("Fields are not filled");
			locale.setRu("Заполнены не все поля ввода");
			session.setAttribute("errorMessage_1", locale.getMessage(session));
			LOG.error("ErrorMessage:     "
					+ session.getAttribute("errorMessage_1"));
			response.sendRedirect(redir);
			return null;
		} else {
			Abonent abonent = dao.findAbonentById(id);

			if (abonent.getPassword().equals(curPass)) {
				if (curPass.equals(newPass)) {
					locale.setEn("Passwords are the same");
					locale.setRu("Пароли одинаковы");
					session.setAttribute("errorMessage_1",
							locale.getMessage(session));
					LOG.error("ErrorMessage:     "
							+ session.getAttribute("errorMessage_1"));
					response.sendRedirect(redir);
					return null;
				}
				if (!abonent.validateLoginPassword(newPass)) {
					locale.setEn("Minimal password length = 3, maximum = 16. Available symbols: a-z, A-Z, 0-9, _, -");
					locale.setRu("Минимальная длина пароля = 3, максимальная = 16. Допустимые символы: a-z, A-Z, 0-9, _, -");
					session.setAttribute("errorMessage_1",
							locale.getMessage(session));
					LOG.error("ErrorMessage:     "
							+ session.getAttribute("errorMessage_1"));
					response.sendRedirect(redir);
					return null;
				} else {
					abonent.setPassword(newPass);
					locale.setEn("Successful update");
					locale.setRu("Успешное обновление");
					session.setAttribute("message_1",
							locale.getMessage(session));
					LOG.error("Message:     "
							+ session.getAttribute("message_1"));
					dao.updateAbonentPassword(abonent);
				}
			} else {
				locale.setEn("Password is incorrect !");
				locale.setRu("Неверный пароль !");
				session.setAttribute("errorMessage_1",
						locale.getMessage(session));
				LOG.error("ErrorMessage:     "
						+ session.getAttribute("errorMessage_1"));
				response.sendRedirect(redir);
				return null;
			}

		}

		response.sendRedirect(redir);

		LOG.debug("Command finished");
		return null;
	}
}