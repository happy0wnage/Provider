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
 * Subscriber command to change their personal data.
 * 
 * @author Petrov Vladyslav
 * 
 */
public class ChangeAbonentProfileCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger
			.getLogger(ChangeAbonentProfileCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		int id = Integer.parseInt(request.getParameter("idAbonent"));

		SelectLocale locale = new SelectLocale();

		AbstractDAO dao = new AbstractDAO();
		String redir = request.getServletContext().getContextPath()
				+ Path.COMMAND_ABONENT_PROFILE;

		if (phone.isEmpty() || dob.isEmpty() || address.isEmpty()
				|| email.isEmpty()) {
			locale.setEn("Not all fields are field !");
			locale.setRu("Не все поля заполнены !");
			session.setAttribute("errorMessage", locale.getMessage(session));
			LOG.error("ErrorMessage:     "
					+ session.getAttribute("errorMessage"));
			response.sendRedirect(redir);
			return null;
		} else {
			Abonent abonent = new Abonent();
			
			if (!abonent.isCorrectDateFormat(dob)) {
				locale.setEn("Incorrect date format !");
				locale.setRu("Неверный формат даты !");
				session.setAttribute("errorMessage", locale.getMessage(session));
				LOG.error("ErrorMessage:     "
						+ session.getAttribute("errorMessage"));
				response.sendRedirect(redir);
				return null;
			}
			
			if (!abonent.isCorrectDate(dob)) {
				locale.setEn("Only 18+ users can register in the system !");
				locale.setRu("Только 18+ пользователи могу зарегистрироваться в системе !");
				session.setAttribute("errorMessage", locale.getMessage(session));
				LOG.error("ErrorMessage --> " + session.getAttribute("errorMessage"));
				response.sendRedirect(redir);
				return null;
			}

			if (!abonent.validateEmail(email)) {
				locale.setEn("Incorrect email format !");
				locale.setRu("Неверный формат Email адреса!");
				session.setAttribute("errorMessage", locale.getMessage(session));
				LOG.error("ErrorMessage:     "
						+ session.getAttribute("errorMessage"));
				response.sendRedirect(redir);
				return null;
			}

			if (!abonent.validatePhone(phone)) {
				locale.setEn("Incorrect phone format !");
				locale.setRu("Неверный введен номер телефона!");
				session.setAttribute("errorMessage", locale.getMessage(session));
				LOG.error("ErrorMessage:     "
						+ session.getAttribute("errorMessage"));
				response.sendRedirect(redir);
				return null;
			}

			abonent.setContactPhone(phone);
			abonent.setDob(dob);
			abonent.setEmail(email);
			abonent.setAddress(address);
			abonent.setId(id);

			dao.updateAbonent(abonent);

			locale.setEn("Successful update !");
			locale.setRu("Успешное обновление !");
			session.setAttribute("message", locale.getMessage(session));
			LOG.error("Message:     " + session.getAttribute("message"));
		}

		response.sendRedirect(redir);

		LOG.debug("Command finished");

		return null;
	}
}