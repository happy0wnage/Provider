package ua.nure.petrov.SummaryTask4.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.web.command.Command;
import ua.nure.petrov.SummaryTask4.web.command.CommandContainer;

public class Controller extends HttpServlet {

	private static final long serialVersionUID = 2423353715955164816L;

	private static final Logger LOG = Logger.getLogger(Controller.class);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LOG.debug("Controller starts");

		String commandName = request.getParameter("command");
		LOG.trace("Request parameter: command --> " + commandName);

		Command command = CommandContainer.get(commandName);
		LOG.trace("Obtained command --> " + command);

		String forward = command.execute(request, response);
		LOG.trace("Forward address --> " + forward);

		LOG.debug("Controller finished, now go to forward address --> " + forward);

		if (forward != null) {
			RequestDispatcher disp = request.getRequestDispatcher(forward);
			disp.forward(request, response);
		}
	}

}