package by.epamjwd.mobile.controller.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.command.impl.ShowSubscriberListByUserIdCommand;
import by.epamjwd.mobile.controller.command.impl.SwitchLocaleCommand;
import by.epamjwd.mobile.controller.command.impl.ShowSubscriberByPhoneCommand;
import by.epamjwd.mobile.controller.command.impl.ShowAdminCommand;
import by.epamjwd.mobile.controller.command.impl.LoginCommand;
import by.epamjwd.mobile.controller.command.impl.LogoutCommand;
import by.epamjwd.mobile.controller.command.impl.CodeReturnCommand;
import by.epamjwd.mobile.controller.command.impl.GoToSubscriberOperationsPageCommand;
import by.epamjwd.mobile.controller.command.impl.ShowSubscriberListByFullNameCommand;
import by.epamjwd.mobile.controller.command.impl.ShowSubscriberListByPassportCommand;
import by.epamjwd.mobile.controller.command.impl.ShowFullArticleCommand;
import by.epamjwd.mobile.controller.command.impl.ShowFullPlanCommand;
import by.epamjwd.mobile.controller.command.impl.ShowFullServiceCommand;
import by.epamjwd.mobile.controller.command.impl.GoToLoginPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToMainPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToPasswordRepairPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToProfilePageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToSignupPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToSubscriberBillsPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToSubscriberPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToSubscriberServicesPageCommand;
import by.epamjwd.mobile.controller.command.impl.NoSuchCommand;
import by.epamjwd.mobile.controller.command.impl.CodeSendCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePhonePreparationCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePlanPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.ChangeStatusCommand;
import by.epamjwd.mobile.controller.command.impl.ChangeStatusPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.CheckPhoneCommand;
import by.epamjwd.mobile.controller.command.impl.EditPersonalDataPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.ShowSubscriberByIDCommand;
import by.epamjwd.mobile.controller.command.impl.AddSubscriberCommand;
import by.epamjwd.mobile.controller.command.impl.CalculatorCommand;
import by.epamjwd.mobile.controller.command.impl.CancelEditSubscriberDataCommand;
import by.epamjwd.mobile.controller.command.impl.NewPasswordCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePhoneCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePlanCommand;
import by.epamjwd.mobile.controller.command.impl.EditPersonalDataCommand;
import by.epamjwd.mobile.controller.command.impl.AddSubscriberPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.EditProfileCommand;
import by.epamjwd.mobile.controller.command.impl.GoToAddSubscriberPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToCalculatorPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToNewPasswordPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToCodeReturnPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToCodeSendPageCommand;
import by.epamjwd.mobile.controller.command.impl.GoToErrorPageCommand;
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
		allCommands.put(CommandName.GO_TO_SIGNUP_PAGE, new GoToSignupPageCommand());
		allCommands.put(CommandName.EDIT_PROFILE, new EditProfileCommand());
		allCommands.put(CommandName.LOGIN, new LoginCommand());
		allCommands.put(CommandName.LOGOUT, new LogoutCommand());
		allCommands.put(CommandName.SHOW_ALL_NEWS, new ShowAllNewsCommand());
		allCommands.put(CommandName.SHOW_FULL_ARTICLE, new ShowFullArticleCommand());
		allCommands.put(CommandName.SHOW_ALL_PLANS, new ShowAllPlansCommand());
		allCommands.put(CommandName.SHOW_FULL_PLAN, new ShowFullPlanCommand());
		allCommands.put(CommandName.SHOW_ALL_SERVICES, new ShowAllServicesCommand());
		allCommands.put(CommandName.SHOW_FULL_SERVICE, new ShowFullServiceCommand());
		allCommands.put(CommandName.SHOW_SUBSCRIBER_LIST_BY_USER_ID, new ShowSubscriberListByUserIdCommand());
		allCommands.put(CommandName.SHOW_SUBSCRIBER_LIST_BY_FULL_NAME, new ShowSubscriberListByFullNameCommand());
		allCommands.put(CommandName.SHOW_SUBSCRIBER_LIST_PASSPORT, new ShowSubscriberListByPassportCommand());
		allCommands.put(CommandName.SHOW_ADMIN, new ShowAdminCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_OPERATIONS_PAGE, new GoToSubscriberOperationsPageCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_PAGE, new GoToSubscriberPageCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_SERVICES_PAGE, new GoToSubscriberServicesPageCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_BILLS_PAGE, new GoToSubscriberBillsPageCommand());
		allCommands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPageCommand());
		allCommands.put(CommandName.SHOW_SUBSCRIBER_BY_PHONE, new ShowSubscriberByPhoneCommand());
		allCommands.put(CommandName.SHOW_SUBSCRIBER_BY_ID, new ShowSubscriberByIDCommand());
		allCommands.put(CommandName.GO_TO_ADD_SUBSCRIBER_PAGE, new GoToAddSubscriberPageCommand());
		allCommands.put(CommandName.GO_TO_PROFILE_PAGE, new GoToProfilePageCommand());
		allCommands.put(CommandName.GO_TO_CALCULATOR_PAGE, new GoToCalculatorPageCommand());
		allCommands.put(CommandName.CALCULATOR, new CalculatorCommand());
		allCommands.put(CommandName.SHOW_BEST_PLAN, new ShowBestPlanCommand());
		allCommands.put(CommandName.ADD_SUBSCRIBER, new AddSubscriberCommand());
		allCommands.put(CommandName.ADD_SUBSCRIBER_PREPARATION, new AddSubscriberPreparationCommand());
		allCommands.put(CommandName.SWITCH_LOCALE, new SwitchLocaleCommand());
		allCommands.put(CommandName.EDIT_PERSONAL_DATA, new EditPersonalDataCommand());
		allCommands.put(CommandName.EDIT_PERSONAL_DATA_PREPARATION, new EditPersonalDataPreparationCommand());
		allCommands.put(CommandName.CHANGE_PHONE, new ChangePhoneCommand());
		allCommands.put(CommandName.CHANGE_PLAN, new ChangePlanCommand());
		allCommands.put(CommandName.CHANGE_PHONE_PREPARATION, new ChangePhonePreparationCommand());
		allCommands.put(CommandName.CHANGE_PLAN_PREPARATION, new ChangePlanPreparationCommand());
		allCommands.put(CommandName.CHANGE_STATUS, new ChangeStatusCommand());
		allCommands.put(CommandName.CHANGE_STATUS_PREPARATION, new ChangeStatusPreparationCommand());
		allCommands.put(CommandName.CANCEL_EDIT_SUBSCRIBER_DATA, new CancelEditSubscriberDataCommand());
		allCommands.put(CommandName.CHECK_PHONE, new CheckPhoneCommand());
		allCommands.put(CommandName.GO_TO_CODE_SEND_PAGE, new GoToCodeSendPageCommand());
		allCommands.put(CommandName.GO_TO_CODE_RETURN_PAGE, new GoToCodeReturnPageCommand());
		allCommands.put(CommandName.CODE_SEND, new CodeSendCommand());
		allCommands.put(CommandName.CODE_RETURN, new CodeReturnCommand());
		allCommands.put(CommandName.NEW_PASSWORD, new NewPasswordCommand());
		allCommands.put(CommandName.GO_TO_NEW_PASSWORD_PAGE, new GoToNewPasswordPageCommand());
		allCommands.put(CommandName.GO_TO_PASSWORD_REPAIR_PAGE, new GoToPasswordRepairPageCommand());

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
		return command;
	}

	private static class Holder {
		static final CommandProvider INSTANCE = new CommandProvider();
	}
	
}