package ua.nure.petrov.SummaryTask4.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.nure.petrov.SummaryTask4.web.command.modifyCommands.ChangeAbonentProfileCommand;
import ua.nure.petrov.SummaryTask4.web.command.modifyCommands.ChangeAbonentProfilePasswordCommand;
import ua.nure.petrov.SummaryTask4.web.command.modifyCommands.ChangeCommand;
import ua.nure.petrov.SummaryTask4.web.command.modifyCommands.ChangeTariffCommand;
import ua.nure.petrov.SummaryTask4.web.command.modifyCommands.DeactivateCommand;
import ua.nure.petrov.SummaryTask4.web.command.modifyCommands.DeleteTariffCommand;
import ua.nure.petrov.SummaryTask4.web.command.modifyCommands.NewTariffCommand;
import ua.nure.petrov.SummaryTask4.web.command.modifyCommands.RemoveServiceCommand;
import ua.nure.petrov.SummaryTask4.web.command.modifyCommands.UpdateSettingsCommand;
import ua.nure.petrov.SummaryTask4.web.command.searchCommands.FindAbonentIDCommand;
import ua.nure.petrov.SummaryTask4.web.command.searchCommands.FindAbonentLoginCommand;
import ua.nure.petrov.SummaryTask4.web.command.searchCommands.FindServiceCommand;
import ua.nure.petrov.SummaryTask4.web.command.sortCommands.SortIdCommand;
import ua.nure.petrov.SummaryTask4.web.command.sortCommands.SortNameCommand;
import ua.nure.petrov.SummaryTask4.web.command.sortCommands.SortPriceCommand;

/**
 * Holder for all commands.
 * 
 * @author Petrov Vladyslav
 * 
 */
public class CommandContainer {

	private static Map<String, Command> commands = new TreeMap<String, Command>();

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	static {
		// general commands
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("register", new RegisterCommand());
		commands.put("registerPage", new RegisterPageCommand());
		commands.put("accountFix", new ReloginCommand());
		commands.put("servicesCatalog", new CatalogCommand());
		commands.put("about", new AboutCommand());
		commands.put("updateLocale", new UpdateSettingsCommand());
		commands.put("savePdf", new PftCreateCommand());

		// admin commands
		commands.put("tariff", new TariffCommand());
		commands.put("contract", new ContractCommand());
		commands.put("abonent", new AbonentCommand());
		commands.put("service", new ServiceCommand());
		commands.put("changeTariff", new ChangeCommand());
		commands.put("deleteTariff", new DeleteTariffCommand());
		commands.put("modify", new ChangeTariffCommand());
		commands.put("newTariff", new NewTariffCommand());
		commands.put("findAbonent", new FindAbonentLoginCommand());
		commands.put("findAbonentById", new FindAbonentIDCommand());
		commands.put("removeService", new RemoveServiceCommand());
		commands.put("findServiceById", new FindServiceCommand());
		commands.put("sortId", new SortIdCommand());
		commands.put("sortName", new SortNameCommand());
		commands.put("sortPrice", new SortPriceCommand());
		commands.put("deactivate", new DeactivateCommand());

		// abonent commands
		commands.put("abonent_profile", new ProfileCommand());
		commands.put("abonent_account", new AccountCommand());
		commands.put("changeAbonent", new ChangeAbonentProfileCommand());
		commands.put("changeAbonentPassword",
				new ChangeAbonentProfilePasswordCommand());
		commands.put("pay", new PayCommand());
		commands.put("connection", new ConnectionCommand());
		commands.put("connectChoice", new ConnectionChoiceCommand());

		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	public static Command get(String name) {
		if (name == null || !commands.containsKey(name)) {
			return commands.get("noCommand");
		}
		return commands.get(name);
	}

}