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
import ua.nure.petrov.SummaryTask4.db.entity.Tariff;
import ua.nure.petrov.SummaryTask4.web.command.Command;

/**
 * Admin command to create new tariff in the system
 * 
 * @author Petrov Vladyslav
 * 
 */
public class NewTariffCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(NewTariffCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();

		SelectLocale locale = new SelectLocale();

		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String description = request.getParameter("description");

		int idService = 0;

		String redir = request.getServletContext().getContextPath()
				+ Path.COMMAND_TARIFF;

		AbstractDAO dao = new AbstractDAO();

		if (name.isEmpty() || price.isEmpty() || description.isEmpty()) {
			locale.setEn("Not all fields are field !");
			locale.setRu("Заполнены не все поля !");
			session.setAttribute("errorMessage_1", locale.getMessage(session));
			LOG.error("ErrorMessage:     "
					+ session.getAttribute("errorMessage_1"));
			response.sendRedirect(redir);
			return null;
		}

		try {
			idService = Integer.parseInt(request.getParameter("service"));
		} catch (Exception e) {
			locale.setEn("Incorrect idService !");
			locale.setRu("Неверное ИД Сервиса !");
			session.setAttribute("errorMessage_1", locale.getMessage(session));
			LOG.error("ErrorMessage:     "
					+ session.getAttribute("errorMessage_1"));
			response.sendRedirect(redir);
			return null;
		}

		if (!dao.checkTariffName(name)) {
			locale.setEn("Name is already exists !");
			locale.setRu("Имя уже используется !");
			session.setAttribute("errorMessage_1", locale.getMessage(session));
			LOG.error("ErrorMessage:     "
					+ session.getAttribute("errorMessage_1"));
			response.sendRedirect(redir);
			return null;
		} else {
			Tariff tariff = new Tariff();

			try {
				Double.parseDouble(price);
			} catch (Exception e) {
				locale.setEn("Incorrect price !");
				locale.setRu("Неверный формат цены !");
				session.setAttribute("errorMessage_1",
						locale.getMessage(session));
				LOG.error("ErrorMessage:     "
						+ session.getAttribute("errorMessage_1"));
				response.sendRedirect(redir);
				return null;
			}

			if (!tariff.checkPrice(Double.parseDouble(price))) {
				locale.setEn("Price is less than 0");
				locale.setRu("Цена меньше 0");
				session.setAttribute("errorMessage_1",
						locale.getMessage(session));
				LOG.error("ErrorMessage:     "
						+ session.getAttribute("errorMessage_1"));
				response.sendRedirect(redir);
				return null;
			}

			tariff.setName(name);
			tariff.setIdService(idService);
			tariff.setPrice(Double.parseDouble(price));
			tariff.setDescription(description);
			dao.insertTariff(tariff);

			locale.setEn("Successful !");
			locale.setRu("Новый тариф создан");
			session.setAttribute("message_1", locale.getMessage(session));
			LOG.error("Message:     " + session.getAttribute("message"));
			session.setAttribute("tariffs", new AbstractDAO().findTariff());
		}

		response.sendRedirect(redir);
		LOG.debug("Command finished");
		return null;
	}
}