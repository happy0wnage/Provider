package ua.nure.petrov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.entity.Tariff;
import ua.nure.petrov.SummaryTask4.pdf.PdfCreator;

/**
 * General command to generate pdf document
 * 
 * @author Petrov Vladyslav
 * 
 */
public class PftCreateCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(PftCreateCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		AbstractDAO dao = new AbstractDAO();
		List<Tariff> tariff = dao.findTariff();
		PdfCreator.createPDF(tariff, "d:/Tariffs.pdf");

		response.sendRedirect(request.getHeader("referer"));
		LOG.debug("Command finished");

		return null;
	}
}

