package by.epamjwd.mobile.controller.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.command.impl.ShowCustomerByUserIdCommand;
import by.epamjwd.mobile.controller.command.impl.ShowSubscriberByPhoneCommand;
import by.epamjwd.mobile.controller.command.impl.ShowAdminCommand;
import by.epamjwd.mobile.controller.command.impl.LoginCommand;
import by.epamjwd.mobile.controller.command.impl.LogoutCommand;
import by.epamjwd.mobile.controller.command.impl.GoToSubscriberВaseCommand;
import by.epamjwd.mobile.controller.command.impl.ShowSubscriberListByFullNameCommand;
import by.epamjwd.mobile.controller.command.impl.ShowFullArticleCommand;
import by.epamjwd.mobile.controller.command.impl.ShowFullPlanCommand;
import by.epamjwd.mobile.controller.command.impl.ShowFullServiceCommand;
import by.epamjwd.mobile.controller.command.impl.GoToLoginPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToMainPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToProfilePageCommand;
import by.epamjwd.mobile.controller.command.impl.NoSuchCommand;
import by.epamjwd.mobile.controller.command.impl.ShowSubscriberByIDCommand;
import by.epamjwd.mobile.controller.command.impl.AddSubscriberCommand;
import by.epamjwd.mobile.controller.command.impl.CalculatorCommand;
import by.epamjwd.mobile.controller.command.impl.EditProfileCommand;
import by.epamjwd.mobile.controller.command.impl.GoToAddSubscriberPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToCalculatorPageCommand;
import by.epamjwd.mobile.controller.command.impl.ShowAllPlansCommand;
import by.epamjwd.mobile.controller.command.impl.ShowAllServicesCommand;
import by.epamjwd.mobile.controller.command.impl.ShowBestPlanCommand;
import by.epamjwd.mobile.controller.command.impl.ShowAllNewsCommand;
import by.epamjwd.mobile.controller.repository.CommandName;

public class CommandProvider {

	private final static Logger LOGGER = LogManager.getLogger(CommandProvider.class);

	private final Map<String, Command> allCommands = new HashMap<>();

	private CommandProvider() {
		allCommands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		allCommands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
		allCommands.put(CommandName.GO_TO_LOGIN_PAGE, new GoToLoginPageCommand());
		allCommands.put(CommandName.EDIT_PROFILE, new EditProfileCommand());
		allCommands.put(CommandName.LOGIN, new LoginCommand());
		allCommands.put(CommandName.LOGOUT, new LogoutCommand());
		allCommands.put(CommandName.SHOW_ALL_NEWS, new ShowAllNewsCommand());
		allCommands.put(CommandName.SHOW_FULL_ARTICLE, new ShowFullArticleCommand());
		allCommands.put(CommandName.SHOW_ALL_PLANS, new ShowAllPlansCommand());
		allCommands.put(CommandName.SHOW_FULL_PLAN, new ShowFullPlanCommand());
		allCommands.put(CommandName.SHOW_ALL_SERVICES, new ShowAllServicesCommand());
		allCommands.put(CommandName.SHOW_FULL_SERVICE, new ShowFullServiceCommand());
		allCommands.put(CommandName.SHOW_CUSTOMER_BY_USER_ID, new ShowCustomerByUserIdCommand());
		allCommands.put(CommandName.SHOW_SUBSCRIBER_LIST_BY_FULL_NAME, new ShowSubscriberListByFullNameCommand());
		allCommands.put(CommandName.SHOW_ADMIN, new ShowAdminCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_BASE, new GoToSubscriberВaseCommand());
		allCommands.put(CommandName.SHOW_SUBSCRIBER_BY_PHONE, new ShowSubscriberByPhoneCommand());
		allCommands.put(CommandName.SHOW_SUBSCRIBER_BY_ID, new ShowSubscriberByIDCommand());
		allCommands.put(CommandName.GO_TO_PROFILE_PAGE, new GoToProfilePageCommand());
		allCommands.put(CommandName.GO_TO_CALCULATOR_PAGE, new GoToCalculatorPageCommand());
		allCommands.put(CommandName.CALCULATOR, new CalculatorCommand());
		allCommands.put(CommandName.SHOW_BEST_PLAN, new ShowBestPlanCommand());
		allCommands.put(CommandName.ADD_SUBSCRIBER, new AddSubscriberCommand());
		allCommands.put(CommandName.GO_TO_ADD_SUBSCRIBER_PAGE, new GoToAddSubscriberPageCommand());
	}

    public static CommandProvider getInstance() {
    	return Holder.INSTANCE;
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
//		if (command == null) {
//			LOGGER.error("Nonexistent command name - " + commandName);
//			command = allCommands.get(CommandName.NO_SUCH_COMMAND);
//		}
		
		return command;
	}

	private static class Holder {
		static final CommandProvider INSTANCE = new CommandProvider();
	}
	
}