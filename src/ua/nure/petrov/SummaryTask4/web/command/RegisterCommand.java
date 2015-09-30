package ua.nure.petrov.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.Role;
import ua.nure.petrov.SummaryTask4.db.SelectLocale;
import ua.nure.petrov.SummaryTask4.db.entity.Abonent;
import ua.nure.petrov.SummaryTask4.db.entity.Payment;

/**
 * Register command.
 * 
 * @author Vladyslav Petrov
 * 
 */
public class RegisterCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(RegisterCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String forward = Path.PAGE_REGISTER;

		SelectLocale locale = new SelectLocale();

		HttpSession session = request.getSession();

		String redirRegister =  request.getServletContext().getContextPath()
				+ Path.COMMAND_REGISTER_PAGE;
		
		String login = request.getParameter("login");
		String pass = request.getParameter("password");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String phone = request.getParameter("phone");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String email = request.getParameter("email");

		AbstractDAO dao = new AbstractDAO();

		Abonent abonent = new Abonent();

		abonent.setLogin(login);
		abonent.setDob(dob);
		abonent.setContactPhone(phone);
		abonent.setEmail(email);
		abonent.setAddress(address);
		abonent.setPassword(pass);
		abonent.setName(name);
		abonent.setSurname(surname);
		abonent.setLocale("ru");
		
		if (login.isEmpty() || pass.isEmpty() || name.isEmpty()
				|| surname.isEmpty() || phone.isEmpty() || dob.isEmpty()
				|| address.isEmpty() || email.isEmpty()) {
			locale.setEn("Not all fields are field !");
			locale.setRu("Не все поля заполнены !");
			session.setAttribute("errorMessage", locale.getMessage(session));
			session.setAttribute("abonent", abonent);
			response.sendRedirect(redirRegister);
			LOG.error("ErrorMessage --> " + locale.getMessage(session));
			return null;
		} else {
			if (!dao.checkLogin(login)) {
				locale.setEn("Login is already exists !");
				locale.setRu("Такой логин уже существует !");
				session.setAttribute("errorMessage", locale.getMessage(session));
				session.setAttribute("abonent", abonent);
				response.sendRedirect(redirRegister);
				LOG.error("ErrorMessage --> " + locale.getMessage(session));
				return null;
			}

			if (!abonent.validateNameSurname(name)) {
				locale.setEn("The name must be entered with a capital letter !");
				locale.setRu("Имя должно быть введено с большой буквы !");
				session.setAttribute("errorMessage", locale.getMessage(session));
				session.setAttribute("abonent", abonent);
				response.sendRedirect(redirRegister);
				LOG.error("ErrorMessage --> " + locale.getMessage(session));
				return null;
			}

			if (!abonent.validateNameSurname(surname)) {
				locale.setEn("Last name must be entered with a capital letter !");
				locale.setRu("Фамилия должна быть введена с большой буквы !");
				session.setAttribute("errorMessage", locale.getMessage(session));
				session.setAttribute("abonent", abonent);
				response.sendRedirect(redirRegister);
				LOG.error("ErrorMessage --> " + locale.getMessage(session));
				return null;
			}

			if (!abonent.validateLoginPassword(login)) {
				locale.setEn("Incorrect login !");
				locale.setRu("Неверный логин !");
				session.setAttribute("errorMessage", locale.getMessage(session));
				session.setAttribute("abonent", abonent);
				response.sendRedirect(redirRegister);
				LOG.error("ErrorMessage --> " + locale.getMessage(session));
				return null;
			}

			if (!abonent.validateLoginPassword(pass)) {
				locale.setEn("Incorrect password !");
				locale.setRu("Неверный пароль !");
				session.setAttribute("errorMessage", locale.getMessage(session));
				session.setAttribute("abonent", abonent);
				response.sendRedirect(redirRegister);
				LOG.error("ErrorMessage --> " + locale.getMessage(session));
				return null;
			}

			if (!abonent.isCorrectDateFormat(dob)) {
				locale.setEn("Incorrect date format !");
				locale.setRu("Неверный формат даты !");
				session.setAttribute("errorMessage", locale.getMessage(session));
				session.setAttribute("abonent", abonent);
				response.sendRedirect(redirRegister);
				LOG.error("ErrorMessage --> " + locale.getMessage(session));
				return null;
			}

			if (!abonent.isCorrectDate(dob)) {
				locale.setEn("Only 18+ users can register in the system !");
				locale.setRu("Только 18+ пользователи могу зарегистрироваться в системе !");
				session.setAttribute("errorMessage", locale.getMessage(session));
				session.setAttribute("abonent", abonent);
				response.sendRedirect(redirRegister);
				LOG.error("ErrorMessage --> " + locale.getMessage(session));
				return null;
			}

			if (!abonent.validateEmail(email)) {
				locale.setEn("Incorrect email format !");
				locale.setRu("Неверный формат Email !");
				session.setAttribute("errorMessage", locale.getMessage(session));
				session.setAttribute("abonent", abonent);
				response.sendRedirect(redirRegister);
				LOG.error("ErrorMessage --> " + locale.getMessage(session));
				return null;
			}
			
			if (!dao.checkEmail(email)) {
				locale.setEn("Email is already exists !");
				locale.setRu("Такой Email уже существует !");
				session.setAttribute("errorMessage", locale.getMessage(session));
				session.setAttribute("abonent", abonent);
				response.sendRedirect(redirRegister);
				LOG.error("ErrorMessage --> " + locale.getMessage(session));
				return null;
			}

			if (!abonent.validatePhone(phone)) {
				locale.setEn("Incorrect phone format !");
				locale.setRu("Неверный формат номера !");
				session.setAttribute("errorMessage", locale.getMessage(session));
				session.setAttribute("abonent", abonent);
				response.sendRedirect(redirRegister);
				LOG.error("ErrorMessage --> " + locale.getMessage(session));
				return null;
			}

			Payment payment = new Payment();
			payment.setBalance(0);
			payment.setStatus(false);
			payment.setEndDateService("");
			payment.setLastDateOfWithdrawal("");
			
			dao.insertAbonentPayment(abonent, payment);

			locale.setEn("Successful registration !");
			locale.setRu("Успешная регистрация !");
			session.setAttribute("message", locale.getMessage(session));
			LOG.error("message --> " + locale.getMessage(session));
		}

		Role role = Role.getRole(login);

		if (dao.checkLoginPassword(login, pass)) {

			if (role == Role.ADMIN) {
				forward = Path.PAGE_LOGIN;
			}

			if (role == Role.CLIENT) {
				abonent = dao.findAbonentByLogin(login);
				Payment payment = dao.findPaymentById(abonent.getIdPayment());
				session.setAttribute("status", payment.getStatus());
				forward = Path.COMMAND_CONNECTION;
			}
		} else {
			locale.setEn("Cannot find user with such login/password");
			locale.setRu("Невозможно найти пользовател с таким логином/паролем");
			session.setAttribute("errorMessage", locale.getMessage(session));
			response.sendRedirect(redirRegister);
			LOG.error("ErrorMessage --> " + locale.getMessage(session));
			return null;
		}

		session.setAttribute("user", login);
		session.setAttribute("userRole", role.getName());
		LOG.debug("Register command finished");
		return forward;
	}
}