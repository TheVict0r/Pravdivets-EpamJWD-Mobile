package by.epamjwd.mobile.controller.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import by.epamjwd.mobile.controller.command.impl.ShowSubscriberListByUserIdCommand;
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
import by.epamjwd.mobile.controller.command.impl.transit.GoToConsultantOperationsPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToEditConsultantPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToSignupPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToSubscriberBillsPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToSubscriberOperationsPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToSubscriberPageCommand;
import by.epamjwd.mobile.controller.command.impl.transit.GoToSubscriberServicesPageCommand;
import by.epamjwd.mobile.controller.command.impl.ShowSubscriberByPhoneCommand;
import by.epamjwd.mobile.controller.command.impl.LoginCommand;
import by.epamjwd.mobile.controller.command.impl.LogoutCommand;
import by.epamjwd.mobile.controller.command.impl.CodeReturnCommand;
import by.epamjwd.mobile.controller.command.impl.ShowSubscriberListByFullNameCommand;
import by.epamjwd.mobile.controller.command.impl.ShowSubscriberListByPassportCommand;
import by.epamjwd.mobile.controller.command.impl.ShowFullArticleCommand;
import by.epamjwd.mobile.controller.command.impl.ShowFullPlanAdminCommand;
import by.epamjwd.mobile.controller.command.impl.ShowFullPlanCommand;
import by.epamjwd.mobile.controller.command.impl.ShowFullServiceAdminCommand;
import by.epamjwd.mobile.controller.command.impl.ShowFullServiceCommand;
import by.epamjwd.mobile.controller.command.impl.ShowNextNewsCommand;
import by.epamjwd.mobile.controller.command.impl.ShowPreviousNewsCommand;
import by.epamjwd.mobile.controller.command.impl.NoSuchCommand;
import by.epamjwd.mobile.controller.command.impl.CodeSendCommand;
import by.epamjwd.mobile.controller.command.impl.EditConsultantCommand;
import by.epamjwd.mobile.controller.command.impl.EditConsultantPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePhonePreparationCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePlanPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.ChangeStatusCommand;
import by.epamjwd.mobile.controller.command.impl.ChangeStatusPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.CheckPhoneCommand;
import by.epamjwd.mobile.controller.command.impl.EditPersonalDataPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.ShowSubscriberByIDCommand;
import by.epamjwd.mobile.controller.command.impl.AddArticleCommand;
import by.epamjwd.mobile.controller.command.impl.AddConsultantCommand;
import by.epamjwd.mobile.controller.command.impl.AddPlanCommand;
import by.epamjwd.mobile.controller.command.impl.AddServiceCommand;
import by.epamjwd.mobile.controller.command.impl.AddSubscriberCommand;
import by.epamjwd.mobile.controller.command.impl.CalculatorCommand;
import by.epamjwd.mobile.controller.command.impl.CancelEditConsultantCommand;
import by.epamjwd.mobile.controller.command.impl.CancelEditSubscriberDataCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePasswordCommand;
import by.epamjwd.mobile.controller.command.impl.NewPasswordCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePhoneCommand;
import by.epamjwd.mobile.controller.command.impl.ChangePlanCommand;
import by.epamjwd.mobile.controller.command.impl.EditPersonalDataCommand;
import by.epamjwd.mobile.controller.command.impl.AddSubscriberPreparationCommand;
import by.epamjwd.mobile.controller.command.impl.ShowAllPlansCommand;
import by.epamjwd.mobile.controller.command.impl.ShowAllServicesCommand;
import by.epamjwd.mobile.controller.command.impl.ShowArticleByIdCommand;
import by.epamjwd.mobile.controller.command.impl.ShowBestPlanCommand;
import by.epamjwd.mobile.controller.command.impl.ShowConsultantByEmailCommand;
import by.epamjwd.mobile.controller.command.impl.ShowConsultantByIdCommand;
import by.epamjwd.mobile.controller.command.impl.ShowConsultantByPassportCommand;
import by.epamjwd.mobile.controller.command.impl.ShowAllNewsCommand;
import by.epamjwd.mobile.controller.repository.CommandName;

public class CommandProvider {

	private final Map<String, Command> allCommands = new HashMap<>();

	private CommandProvider() {
		allCommands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
		allCommands.put(CommandName.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
		allCommands.put(CommandName.GO_TO_LOGIN_PAGE, new GoToLoginPageCommand());
		allCommands.put(CommandName.GO_TO_SIGNUP_PAGE, new GoToSignupPageCommand());
		allCommands.put(CommandName.LOGIN, new LoginCommand());
		allCommands.put(CommandName.LOGOUT, new LogoutCommand());
		allCommands.put(CommandName.SHOW_ALL_NEWS, new ShowAllNewsCommand());
		allCommands.put(CommandName.GO_TO_ALL_NEWS_PAGE, new GoToAllnewsPageCommand());
		allCommands.put(CommandName.SHOW_PREVIOUS_NEWS, new ShowPreviousNewsCommand());
		allCommands.put(CommandName.SHOW_NEXT_NEWS, new ShowNextNewsCommand());
		allCommands.put(CommandName.SHOW_FULL_ARTICLE, new ShowFullArticleCommand());
		allCommands.put(CommandName.SHOW_ALL_PLANS, new ShowAllPlansCommand());
		allCommands.put(CommandName.SHOW_FULL_PLAN, new ShowFullPlanCommand());
		allCommands.put(CommandName.SHOW_FULL_PLAN_ADMIN, new ShowFullPlanAdminCommand());
		allCommands.put(CommandName.GO_TO_PLAN_ADMIN_PAGE, new GoToPlanAdminPageCommand());
		allCommands.put(CommandName.SHOW_ALL_SERVICES, new ShowAllServicesCommand());
		allCommands.put(CommandName.SHOW_FULL_SERVICE, new ShowFullServiceCommand());
		allCommands.put(CommandName.SHOW_FULL_SERVICE_ADMIN, new ShowFullServiceAdminCommand());
		allCommands.put(CommandName.SHOW_SUBSCRIBER_LIST_BY_USER_ID, new ShowSubscriberListByUserIdCommand());
		allCommands.put(CommandName.SHOW_SUBSCRIBER_LIST_BY_FULL_NAME, new ShowSubscriberListByFullNameCommand());
		allCommands.put(CommandName.SHOW_SUBSCRIBER_LIST_BY_PASSPORT, new ShowSubscriberListByPassportCommand());
		allCommands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPageCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_OPERATIONS_PAGE, new GoToSubscriberOperationsPageCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_PAGE, new GoToSubscriberPageCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_SERVICES_PAGE, new GoToSubscriberServicesPageCommand());
		allCommands.put(CommandName.GO_TO_SUBSCRIBER_BILLS_PAGE, new GoToSubscriberBillsPageCommand());
		allCommands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPageCommand());
		allCommands.put(CommandName.GO_TO_ERROR_404_PAGE, new GoToError404PageCommand());
		allCommands.put(CommandName.GO_TO_ERROR_500_PAGE, new GoToError500PageCommand());
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
		allCommands.put(CommandName.GO_TO_PHONE_REQUEST_PAGE, new GoToPhoneRequestPageCommand());
		allCommands.put(CommandName.GO_TO_CODE_RETURN_PAGE, new GoToCodeReturnPageCommand());
		allCommands.put(CommandName.CODE_SEND, new CodeSendCommand());
		allCommands.put(CommandName.CODE_RETURN, new CodeReturnCommand());
		allCommands.put(CommandName.NEW_PASSWORD, new NewPasswordCommand());
		allCommands.put(CommandName.GO_TO_NEW_PASSWORD_PAGE, new GoToNewPasswordPageCommand());
		allCommands.put(CommandName.GO_TO_PASSWORD_REPAIR_PAGE, new GoToPasswordRepairPageCommand());
		allCommands.put(CommandName.GO_TO_ADD_CONSULTANT_PAGE, new GoToAddConsultantPageCommand());
		allCommands.put(CommandName.GO_TO_ADD_PLAN_PAGE, new GoToAddPlanPageCommand());
		allCommands.put(CommandName.ADD_PLAN, new AddPlanCommand());
		allCommands.put(CommandName.ADD_CONSULTANT, new AddConsultantCommand());
		allCommands.put(CommandName.ADD_SERVICE, new AddServiceCommand());
		allCommands.put(CommandName.GO_TO_ADD_SERVICE_PAGE, new GoToAddServicePageCommand());
		allCommands.put(CommandName.GO_TO_ADD_ARTICLE_PAGE, new GoToAddArticlePageCommand());
		allCommands.put(CommandName.ADD_ARTICLE, new AddArticleCommand());
		allCommands.put(CommandName.SHOW_CONSULTANT_BY_ID, new ShowConsultantByIdCommand());
		allCommands.put(CommandName.EDIT_CONSULTANT_PREPARATION, new EditConsultantPreparationCommand());
		allCommands.put(CommandName.EDIT_CONSULTANT, new EditConsultantCommand());
		allCommands.put(CommandName.CANCEL_EDIT_CONSULTANT, new CancelEditConsultantCommand());
		allCommands.put(CommandName.GO_TO_CONSULTANT_OPERATIONS_PAGE, new GoToConsultantOperationsPageCommand());
		allCommands.put(CommandName.SHOW_CONSULTANT_BY_EMAIL, new ShowConsultantByEmailCommand());
		allCommands.put(CommandName.SHOW_CONSULTANT_BY_PASSPORT, new ShowConsultantByPassportCommand());
		allCommands.put(CommandName.GO_TO_EDIT_CONSULTANT_PAGE, new GoToEditConsultantPageCommand());
		allCommands.put(CommandName.GO_TO_CHANGE_PASSWORD_PAGE, new GoToChangePasswordPageCommand());
		allCommands.put(CommandName.CHANGE_PASSWORD, new ChangePasswordCommand());
		allCommands.put(CommandName.GO_TO_PLAN_OPERATIONS_PAGE, new GoToPlanOperationsPageCommand());
		allCommands.put(CommandName.GO_TO_PLAN_PAGE, new GoToPlanPageCommand());
		allCommands.put(CommandName.GO_TO_SERVICE_PAGE, new GoToServicePageCommand());
		allCommands.put(CommandName.GO_TO_SERVICE_OPERATIONS_PAGE, new GoToServiceOperationsPageCommand());
		allCommands.put(CommandName.GO_TO_ARTICLE_OPERATIONS_PAGE, new GoToArticleOperationsPageCommand());
		allCommands.put(CommandName.GO_TO_ARTICLE_PAGE, new GoToArticlePageCommand());
		allCommands.put(CommandName.GO_TO_SERVICE_ADMIN_PAGE, new GoToServiceAdminPageCommand());
		allCommands.put(CommandName.GO_TO_ARTICLE_ADMIN_PAGE, new GoToArticleAdminPageCommand());
		allCommands.put(CommandName.SHOW_ARTICLE_BY_ID, new ShowArticleByIdCommand());
		allCommands.put(CommandName.FIND_ARTICLE_BY_TITLE, new FindArticleByTitleCommand());

	}

    public static CommandProvider getInstance() {
    	return Holder.INSTANCE;
    }
    
	public Command getCommand(String commandName) {
        return Optional.ofNullable(allCommands.get(commandName)).orElse(allCommands.get(CommandName.NO_SUCH_COMMAND));
	}

	public void addCommand(String key, Command value) {
		allCommands.put(key, value);
	}
	
	private static class Holder {
		static final CommandProvider INSTANCE = new CommandProvider();
	}
	
}