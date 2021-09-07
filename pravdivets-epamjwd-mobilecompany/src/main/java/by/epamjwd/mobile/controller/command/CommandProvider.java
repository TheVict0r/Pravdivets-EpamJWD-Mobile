package by.epamjwd.mobile.controller.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.command.impl.AuthorizationCommand;
import by.epamjwd.mobile.controller.command.impl.FullArticleCommand;
import by.epamjwd.mobile.controller.command.impl.LoginCommand;
import by.epamjwd.mobile.controller.command.impl.NoSuchCommand;
import by.epamjwd.mobile.controller.command.impl.ProfileEditorCommand;
import by.epamjwd.mobile.controller.command.impl.ProvideNewsCommand;



public class CommandProvider {

	private final static Logger LOGGER = LogManager.getLogger(CommandProvider.class);

	private final Map<CommandName, Command> repository = new HashMap<>();

	public CommandProvider() {
		repository.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		repository.put(CommandName.EDIT_PROFILE, new ProfileEditorCommand());
		repository.put(CommandName.PROVIDE_NEWS, new ProvideNewsCommand());
		repository.put(CommandName.FULL_ARTICLE, new FullArticleCommand());
		repository.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		repository.put(CommandName.LOGIN, new LoginCommand());
	}

	
	public Command getCommand(String name) {

		CommandName commandName = null;
		Command command = null;

		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = repository.get(commandName);
		} catch (IllegalArgumentException e) {
			LOGGER.error("The command does not exist", e);
			command = repository.get(CommandName.NO_SUCH_COMMAND);
		} catch (NullPointerException e) {
			LOGGER.error("Null value instead of command", e);
			command = repository.get(CommandName.NO_SUCH_COMMAND);
		}
		return command;

	}

	
}