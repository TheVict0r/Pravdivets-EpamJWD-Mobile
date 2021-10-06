package by.epamjwd.mobile.controller.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.command.impl.CustomerCommand;
import by.epamjwd.mobile.controller.command.impl.AbonentCommand;
import by.epamjwd.mobile.controller.command.impl.AdminCommand;
import by.epamjwd.mobile.controller.command.impl.AuthenticationCommand;
import by.epamjwd.mobile.controller.command.impl.ConsultantCommand;
import by.epamjwd.mobile.controller.command.impl.FullArticleCommand;
import by.epamjwd.mobile.controller.command.impl.FullPlanCommand;
import by.epamjwd.mobile.controller.command.impl.FullServiceCommand;
import by.epamjwd.mobile.controller.command.impl.GoToLoginPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToMainPageCommand;
import by.epamjwd.mobile.controller.command.impl.NoSuchCommand;
import by.epamjwd.mobile.controller.command.impl.ProfileEditorCommand;
import by.epamjwd.mobile.controller.command.impl.ProvideAllPlansCommand;
import by.epamjwd.mobile.controller.command.impl.ProvideAllServicesCommand;
import by.epamjwd.mobile.controller.command.impl.ProvideNewsCommand;
import by.epamjwd.mobile.controller.repository.CommandName;

public class CommandProvider {

	private final static Logger LOGGER = LogManager.getLogger(CommandProvider.class);

	private final Map<String, Command> allCommands = new HashMap<>();

	public CommandProvider() {
		allCommands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
		allCommands.put(CommandName.AUTHENTICATION, new AuthenticationCommand());
		allCommands.put(CommandName.EDIT_PROFILE, new ProfileEditorCommand());
		allCommands.put(CommandName.PROVIDE_NEWS, new ProvideNewsCommand());
		allCommands.put(CommandName.FULL_ARTICLE, new FullArticleCommand());
		allCommands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		allCommands.put(CommandName.GO_TO_LOGIN_PAGE, new GoToLoginPageCommand());
		allCommands.put(CommandName.PROVIDE_ALL_PLANS, new ProvideAllPlansCommand());
		allCommands.put(CommandName.FULL_PLAN, new FullPlanCommand());
		allCommands.put(CommandName.PROVIDE_ALL_SERVICES, new ProvideAllServicesCommand());
		allCommands.put(CommandName.FULL_SERVICE, new FullServiceCommand());
		allCommands.put(CommandName.ADMIN, new AdminCommand());
		allCommands.put(CommandName.CONSULTANT, new ConsultantCommand());
		allCommands.put(CommandName.CUSTOMER, new CustomerCommand());
		allCommands.put(CommandName.ABONENT, new AbonentCommand());
	}

	public Command getCommand(String commandName) {
		Command command = null;
		try {
			command = allCommands.get(commandName);
		} catch (IllegalArgumentException e) {
			LOGGER.error("The command does not exist", e);
			command = allCommands.get(CommandName.NO_SUCH_COMMAND);
		} catch (NullPointerException e) {
			LOGGER.error("Null value instead of command", e);
			command = allCommands.get(CommandName.NO_SUCH_COMMAND);
		}
		//возможно отсечётся через web.xml
		if (command == null) {
			LOGGER.error("Nonexistent command name" + commandName);
			command = allCommands.get(CommandName.NO_SUCH_COMMAND);
		}
		
		return command;
	}

}