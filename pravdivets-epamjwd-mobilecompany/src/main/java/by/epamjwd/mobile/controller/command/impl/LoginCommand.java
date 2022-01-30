package by.epamjwd.mobile.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.UserRolePathProvider;
import by.epamjwd.mobile.controller.command.helpers.SubscriberCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class LoginCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);

	/**
	 * The login could be a phone number for Subscribers and an e-mail for all kind of Users
	 * (including Subscribers). 
	 * 
	 * If Subscriber has multiple phone numbers and use an e-mail as a login, 
	 * the menu with all his/her numbers will appear. 
	 * 
	 * If Subscriber use phone number as a login 
	 * the detailed information about this particular phone number only will appear
	 * (despite the amount of phone numbers this Subscriber has).
	 */
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = ServiceProvider.getInstance().getUserService();
		SubscriberService subscriberService = ServiceProvider.getInstance().getSubscriberService();
		HttpSession session = request.getSession();
		RouteHelper result = RouteHelper.ERROR;
		User user = null;
		Role role = null;

		String login = request.getParameter(ParameterName.LOGIN);

		if (login == null || login.isBlank() || request.getParameter(ParameterName.PASSWORD) == null
				|| request.getParameter(ParameterName.PASSWORD).isBlank()) {
			session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR_LOGIN);
			return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
		}

		try {
			Optional<User> userOptional = userService.findUserByLogin(login);

			if (userOptional.isPresent() && userService.isPasswordCorrect(userOptional.get(),
					request.getParameter(ParameterName.PASSWORD))) {
				user = userOptional.get();
				session.setAttribute(AttributeName.USER_ID, user.getId());
				session.setAttribute(AttributeName.FIRST_NAME_HEADER, user.getFirstName());
				session.setAttribute(AttributeName.LAST_NAME_HEADER, user.getLastName());
				session.setAttribute(AttributeName.ROLE, user.getRole());
			} else {
				session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR_LOGIN);
				session.setAttribute(AttributeName.LOGIN, login);
				session.setAttribute(AttributeName.PASSWORD, request.getParameter(ParameterName.PASSWORD));
				return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to retrieve user data for login - " + login, e);
			return RouteHelper.ERROR_500;
		}

		role = user.getRole();
		result = UserRolePathProvider.getInstance().providePath(role);

		try {
			if (InputDataValidator.isPhone(login) && subscriberService.isPhoneExist(login)) {
				Subscriber subscriber = subscriberService.findSubscriberByPhone(login).get();
				/* we can safely use .get() method as just checked that this 
				 * Optional<Subscriber> is not empty in the previous line - 
				 * see subscriberService.isPhoneExist(login)
				 */
				result = SubscriberCommandHelper.getInstance().handleSubscriber(request, subscriber);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to retrieve Subscriber data for phone - " + login, e);
			result = RouteHelper.ERROR_500;
		}

		return result;
	}

}
