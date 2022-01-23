package by.epamjwd.mobile.controller.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import by.epamjwd.mobile.controller.command.impl.AddArticleCommand;
import by.epamjwd.mobile.controller.command.impl.AddConsultantCommand;
import by.epamjwd.mobile.controller.command.impl.AddPlanCommand;
import by.epamjwd.mobile.controller.command.impl.AddServiceCommand;
import by.epamjwd.mobile.controller.command.impl.AddSubscriberCommand;
import by.epamjwd.mobile.controller.command.impl.AddSubscriberPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.CalculatorCommand;
import by.epamjwd.mobile.controller.command.impl.CancelEditConsultantCommand;
import by.epamjwd.mobile.controller.command.impl.CancelEditSubscriberDataCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePasswordCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePhoneCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePhonePreparationCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePlanCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePlanPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.ChangeStatusCommand;
import by.epamjwd.mobile.controller.command.impl.ChangeStatusPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.CheckPhoneCommand;
import by.epamjwd.mobile.controller.command.impl.CodeReturnCommand;
import by.epamjwd.mobile.controller.command.impl.CodeSendCommand;
import by.epamjwd.mobile.controller.command.impl.EditConsultantCommand;
import by.epamjwd.mobile.controller.command.impl.EditConsultantPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.EditPersonalDataCommand;
import by.epamjwd.mobile.controller.command.impl.EditPersonalDataPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.FindAllNewsCommand;
import by.epamjwd.mobile.controller.command.impl.FindAllPlansCommand;
import by.epamjwd.mobile.controller.command.impl.FindAllServicesCommand;
import by.epamjwd.mobile.controller.command.impl.FindArticleByIdCommand;
import by.epamjwd.mobile.controller.command.impl.FindArticleByTitleCommand;
import by.epamjwd.mobile.controller.command.impl.FindBestPlanCommand;
import by.epamjwd.mobile.controller.command.impl.FindConsultantByEmailCommand;
import by.epamjwd.mobile.controller.command.impl.FindConsultantByIdCommand;
import by.epamjwd.mobile.controller.command.impl.FindConsultantByPassportCommand;
import by.epamjwd.mobile.controller.command.impl.FindFullArticleCommand;
import by.epamjwd.mobile.controller.command.impl.FindFullPlanAdminCommand;
import by.epamjwd.mobile.controller.command.impl.FindFullPlanCommand;
import by.epamjwd.mobile.controller.command.impl.FindFullServiceAdminCommand;
import by.epamjwd.mobile.controller.command.impl.FindFullServiceCommand;
import by.epamjwd.mobile.controller.command.impl.FindNewArticlesCommand;
import by.epamjwd.mobile.controller.command.impl.FindOldArticlesCommand;
import by.epamjwd.mobile.controller.command.impl.FindSubscriberByIDCommand;
import by.epamjwd.mobile.controller.command.impl.FindSubscriberByPhoneCommand;
import by.epamjwd.mobile.controller.command.impl.FindSubscriberListByFullNameCommand;
import by.epamjwd.mobile.controller.command.impl.FindSubscriberListByPassportCommand;
import by.epamjwd.mobile.controller.command.impl.FindSubscriberListByUserIdCommand;
import by.epamjwd.mobile.controller.command.impl.LoginCommand;
import by.epamjwd.mobile.controller.command.impl.LogoutCommand;
import by.epamjwd.mobile.controller.command.impl.NewPasswordCommand;
import by.epamjwd.mobile.controller.command.impl.NoSuchCommand;
import by.epamjwd.mobile.controller.command.impl.SwitchLocaleCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToAddArticlePageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToAddConsultantPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToAddPlanPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToAddServicePageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToAddSubscriberPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToAdminPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToAllnewsPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToArticleAdminPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToArticleOperationsPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToArticlePageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToCalculatorPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToChangePasswordPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToCodeReturnPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToCodeSendPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToConsultantOperationsPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToConsultantPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToEditConsultantPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToError404PageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToError500PageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToErrorPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToLoginPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToMainPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToNewPasswordPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToPasswordRepairPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToPhoneRequestPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToPlanAdminPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToPlanOperationsPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToPlanPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToProfilePageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToServiceAdminPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToServiceOperationsPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToServicePageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToSignupPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToSubscriberBillsPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToSubscriberOperationsPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToSubscriberPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToSubscriberServicesPageCommand;
import by.epamjwd.mobile.controller.repository.CommandName;

/**
 * Provides any command by it's name
 *
 */
public class CommandProvider {

	private final Map<String, Command> allCommands = new HashMap<>();

	private CommandProvider() {
		allCommands.put(CommandName.ADD_ARTICLE, new AddArticleCommand());
		allCommands.put(CommandName.ADD_CONSULTANT, new AddConsultantCommand());
		allCommands.put(CommandName.ADD_PLAN, new AddPlanCommand());
		allCommands.put(CommandName.ADD_SERVICE, new AddServiceCommand());
		allCommands.put(CommandName.ADD_SUBSCRIBER, new AddSubscriberCommand());
		allCommands.put(CommandName.ADD_SUBSCRIBER_PREPARATION, new AddSubscriberPreparationCommand());
		allCommands.put(CommandName.CALCULATOR, new CalculatorCommand());
		allCommands.put(CommandName.CANCEL_EDIT_CONSULTANT, new CancelEditConsultantCommand());
		allCommands.put(CommandName.CANCEL_EDIT_SUBSCRIBER_DATA, new CancelEditSubscriberDataCommand());
		allCommands.put(CommandName.CHANGE_PASSWORD, new ChangePasswordCommand());
		allCommands.put(CommandName.CHANGE_PHONE, new ChangePhoneCommand());
		allCommands.put(CommandName.CHANGE_PHONE_PREPARATION, new ChangePhonePreparationCommand());
		allCommands.put(CommandName.CHANGE_PLAN, new ChangePlanCommand());
		allCommands.put(CommandName.CHANGE_PLAN_PREPARATION, new ChangePlanPreparationCommand());
		allCommands.put(CommandName.CHANGE_STATUS, new ChangeStatusCommand());
		allCommands.put(CommandName.CHANGE_STATUS_PREPARATION, new ChangeStatusPreparationCommand());
		allCommands.put(CommandName.CHECK_PHONE, new CheckPhoneCommand());
		allCommands.put(CommandName.CODE_RETURN, new CodeReturnCommand());
		allCommands.put(CommandName.CODE_SEND, new CodeSendCommand());
		allCommands.put(CommandName.EDIT_CONSULTANT, new EditConsultantCommand());
		allCommands.put(CommandName.EDIT_CONSULTANT_PREPARATION, new EditConsultantPreparationCommand());
		allCommands.put(CommandName.EDIT_PERSONAL_DATA, new EditPersonalDataCommand());
		allCommands.put(CommandName.EDIT_PERSONAL_DATA_PREPARATION, new EditPersonalDataPreparationCommand());
		allCommands.put(CommandName.FIND_ALL_NEWS, new FindAllNewsCommand());
		allCommands.put(CommandName.FIND_ALL_PLANS, new FindAllPlansCommand());
		allCommands.put(CommandName.FIND_ALL_SERVICES, new FindAllServicesCommand());
		allCommands.put(CommandName.FIND_ARTICLE_BY_ID, new FindArticleByIdCommand());
		allCommands.put(CommandName.FIND_ARTICLE_BY_TITLE, new FindArticleByTitleCommand());
		allCommands.put(CommandName.FIND_BEST_PLAN, new FindBestPlanCommand());
		allCommands.put(CommandName.FIND_CONSULTANT_BY_EMAIL, new FindConsultantByEmailCommand());
		allCommands.put(CommandName.FIND_CONSULTANT_BY_ID, new FindConsultantByIdCommand());
		allCommands.put(CommandName.FIND_CONSULTANT_BY_PASSPORT, new FindConsultantByPassportCommand());
		allCommands.put(CommandName.FIND_FULL_ARTICLE, new FindFullArticleCommand());
		allCommands.put(CommandName.FIND_FULL_PLAN, new FindFullPlanCommand());
		allCommands.put(CommandName.FIND_FULL_PLAN_ADMIN, new FindFullPlanAdminCommand());
		allCommands.put(CommandName.FIND_FULL_SERVICE, new FindFullServiceCommand());
		allCommands.put(CommandName.FIND_FULL_SERVICE_ADMIN, new FindFullServiceAdminCommand());
		allCommands.put(CommandName.FIND_NEW_ARTICLES, new FindNewArticlesCommand());
		allCommands.put(CommandName.FIND_OLD_ARTICLES, new FindOldArticlesCommand());
		allCommands.put(CommandName.FIND_SUBSCRIBER_BY_ID, new FindSubscriberByIDCommand());
		allCommands.put(CommandName.FIND_SUBSCRIBER_BY_PHONE, new FindSubscriberByPhoneCommand());
		allCommands.put(CommandName.FIND_SUBSCRIBER_LIST_BY_FULL_NAME, new FindSubscriberListByFullNameCommand());
		allCommands.put(CommandName.FIND_SUBSCRIBER_LIST_BY_PASSPORT, new FindSubscriberListByPassportCommand());
		allCommands.put(CommandName.FIND_SUBSCRIBER_LIST_BY_USER_ID, new FindSubscriberListByUserIdCommand());
		allCommands.put(CommandName.GO_TO_ADD_ARTICLE_PAGE, new GoToAddArticlePageCommand());
		allCommands.put(CommandName.GO_TO_ADD_CONSULTANT_PAGE, new GoToAddConsultantPageCommand());
		allCommands.put(CommandName.GO_TO_ADD_PLAN_PAGE, new GoToAddPlanPageCommand());
		allCommands.put(CommandName.GO_TO_ADD_SERVICE_PAGE, new GoToAddServicePageCommand());
		allCommands.put(CommandName.GO_TO_ADD_SUBSCRIBER_PAGE, new GoToAddSubscriberPageCommand());
		allCommands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPageCommand());
		allCommands.put(CommandName.GO_TO_ALL_NEWS_PAGE, new GoToAllnewsPageCommand());
		allCommands.put(CommandName.GO_TO_ARTICLE_ADMIN_PAGE, new GoToArticleAdminPageCommand());
		allCommands.put(CommandName.GO_TO_ARTICLE_OPERATIONS_PAGE, new GoToArticleOperationsPageCommand());
		allCommands.put(CommandName.GO_TO_ARTICLE_PAGE, new GoToArticlePageCommand());
		allCommands.put(CommandName.GO_TO_CALCULATOR_PAGE, new GoToCalculatorPageCommand());
		allCommands.put(CommandName.GO_TO_CONSULTANT_PAGE, new GoToConsultantPageCommand());
		allCommands.put(CommandName.GO_TO_CHANGE_PASSWORD_PAGE, new GoToChangePasswordPageCommand());
		allCommands.put(CommandName.GO_TO_CODE_RETURN_PAGE, new GoToCodeReturnPageCommand());
		allCommands.put(CommandName.GO_TO_CODE_SEND_PAGE, new GoToCodeSendPageCommand());
		allCommands.put(CommandName.GO_TO_CONSULTANT_OPERATIONS_PAGE, new GoToConsultantOperationsPageCommand());
		allCommands.put(CommandName.GO_TO_EDIT_CONSULTANT_PAGE, new GoToEditConsultantPageCommand());
		allCommands.put(CommandName.GO_TO_ERROR_404_PAGE, new GoToError404PageCommand());
		allCommands.put(CommandName.GO_TO_ERROR_500_PAGE, new GoToError500PageCommand());
		allCommands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPageCommand());
		allCommands.put(CommandName.GO_TO_LOGIN_PAGE, new GoToLoginPageCommand());
		allCommands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
		allCommands.put(CommandName.GO_TO_NEW_PASSWORD_PAGE, new GoToNewPasswordPageCommand());
		allCommands.put(CommandName.GO_TO_PASSWORD_REPAIR_PAGE, new GoToPasswordRepairPageCommand());
		allCommands.put(CommandName.GO_TO_PHONE_REQUEST_PAGE, new GoToPhoneRequestPageCommand());
		allCommands.put(CommandName.GO_TO_PLAN_ADMIN_PAGE, new GoToPlanAdminPageCommand());
		allCommands.put(CommandName.GO_TO_PLAN_OPERATIONS_PAGE, new GoToPlanOperationsPageCommand());
		allCommands.put(CommandName.GO_TO_PLAN_PAGE, new GoToPlanPageCommand());
		allCommands.put(CommandName.GO_TO_PROFILE_PAGE, new GoToProfilePageCommand());
		allCommands.put(CommandName.GO_TO_SERVICE_ADMIN_PAGE, new GoToServiceAdminPageCommand());
		allCommands.put(CommandName.GO_TO_SERVICE_OPERATIONS_PAGE, new GoToServiceOperationsPageCommand());
		allCommands.put(CommandName.GO_TO_SERVICE_PAGE, new GoToServicePageCommand());
		allCommands.put(CommandName.GO_TO_SIGNUP_PAGE, new GoToSignupPageCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_BILLS_PAGE, new GoToSubscriberBillsPageCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_OPERATIONS_PAGE, new GoToSubscriberOperationsPageCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_PAGE, new GoToSubscriberPageCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_SERVICES_PAGE, new GoToSubscriberServicesPageCommand());
		allCommands.put(CommandName.LOGIN, new LoginCommand());
		allCommands.put(CommandName.LOGOUT, new LogoutCommand());
		allCommands.put(CommandName.NEW_PASSWORD, new NewPasswordCommand());
		allCommands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		allCommands.put(CommandName.SWITCH_LOCALE, new SwitchLocaleCommand());

	}

    public static CommandProvider getInstance() {
    	return Holder.INSTANCE;
    }
    
	/**
	 * Provides the Command by the string representation of it's name
	 * 
	 * @param commandName - the name of the command
	 * @return - Command object
	 */
	public Command getCommand(String commandName) {
        return Optional.ofNullable(allCommands.get(commandName)).orElse(allCommands.get(CommandName.NO_SUCH_COMMAND));
	}

	/**
	 * Adds a new Command to map, that contains all commands
	 * 
	 * @param key - string representation of commands's name
	 * @param value - Command object
	 */
	public void addCommand(String key, Command value) {
		allCommands.put(key, value);
	}
	
	private static class Holder {
		static final CommandProvider INSTANCE = new CommandProvider();
	}
	
}