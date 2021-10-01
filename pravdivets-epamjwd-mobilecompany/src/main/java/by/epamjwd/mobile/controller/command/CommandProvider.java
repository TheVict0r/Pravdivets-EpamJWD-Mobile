package by.epamjwd.mobile.controller.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.command.impl.AuthenticationCommand;
import by.epamjwd.mobile.controller.command.impl.FullArticleCommand;
import by.epamjwd.mobile.controller.command.impl.FullPlanCommand;
import by.epamjwd.mobile.controller.command.impl.LoginCommand;
import by.epamjwd.mobile.controller.command.impl.MainPageCommand;
import by.epamjwd.mobile.controller.command.impl.NoSuchCommand;
import by.epamjwd.mobile.controller.command.impl.ProfileEditorCommand;
import by.epamjwd.mobile.controller.command.impl.ProvideAllPlansCommand;
import by.epamjwd.mobile.controller.command.impl.ProvideNewsCommand;
import by.epamjwd.mobile.controller.repository.CommandName;

public class CommandProvider {

	private final static Logger LOGGER = LogManager.getLogger(CommandProvider.class);

	private final Map<String, Command> allCommands = new HashMap<>();

	public CommandProvider() {
		allCommands.put(CommandName.MAIN_PAGE, new MainPageCommand());
		allCommands.put(CommandName.AUTHENTICATION, new AuthenticationCommand());
		allCommands.put(CommandName.EDIT_PROFILE, new ProfileEditorCommand());
		allCommands.put(CommandName.PROVIDE_NEWS, new ProvideNewsCommand());
		allCommands.put(CommandName.FULL_ARTICLE, new FullArticleCommand());
		allCommands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		allCommands.put(CommandName.LOGIN, new LoginCommand());
		allCommands.put(CommandName.PROVIDE_ALL_PLANS, new ProvideAllPlansCommand());
		allCommands.put(CommandName.FULL_PLAN, new FullPlanCommand());
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
		return command;
	}

}