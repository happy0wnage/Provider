package ua.nure.petrov.SummaryTask4.web.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.Path;
import ua.nure.petrov.SummaryTask4.db.AbstractDAO;
import ua.nure.petrov.SummaryTask4.db.entity.Service;
import ua.nure.petrov.SummaryTask4.db.entity.Tariff;

/**
 * 
 * General command for reading and downloading information about services and
 * their tariffs.
 * 
 * @author Vladyslav Petrov 
 * 
 */
public class CatalogCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;

	private static final Logger LOG = Logger.getLogger(CatalogCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String forward = Path.PAGE_CATALOG;

		AbstractDAO dao = new AbstractDAO();

		Map<String, List<Tariff>> map = new HashMap<String, List<Tariff>>();

		List<Service> service = dao.findService();

		for (Service s : service) {
			List<Tariff> tariffs = dao.findTariffsByIdService(s.getId());
			map.put(s.getName(), tariffs);
		}

		request.setAttribute("servicetariff", map);
		LOG.debug("Command finished");
		return forward;
	}
}