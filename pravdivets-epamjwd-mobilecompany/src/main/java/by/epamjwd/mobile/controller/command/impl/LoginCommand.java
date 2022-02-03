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

public class LoginCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);

	/**
	 * A Subscriber can use his phone number or e-mail as the login, 
	 * other Users can use e-mail only as their login.
	 * 
	 * If a Subscriber has several phone numbers and uses an e-mail as a login, 
	 * the menu with all his phone numbers will appear.
	 * 
	 * If a Subscriber uses the phone number as a login, the detailed information about this
	 * particular phone number will appear only (despite the amount of phone numbers
	 * this Subscriber has).
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
		String password = request.getParameter(ParameterName.PASSWORD);

		if (login == null || login.isBlank() || password == null || password.isBlank()) {
			session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR_LOGIN);
			return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
		}

		try {
			Optional<User> userOptional = userService.findUserByLogin(login);
			if (userOptional.isPresent() && userService.isPasswordCorrect(userOptional.get(), password)) {
				user = userOptional.get();
				session.setAttribute(AttributeName.USER_ID, user.getId());
				session.setAttribute(AttributeName.FIRST_NAME_HEADER, user.getFirstName());
				session.setAttribute(AttributeName.LAST_NAME_HEADER, user.getLastName());
				session.setAttribute(AttributeName.ROLE, user.getRole());
			} else {
				session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR_LOGIN);
				session.setAttribute(AttributeName.LOGIN, login);
				session.setAttribute(AttributeName.PASSWORD, password);
				return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to retrieve user data for login - " + login, e);
			return RouteHelper.ERROR_500;
		}

		try {
			Optional<Subscriber> subscriberOptional = subscriberService.findSubscriberByPhone(login);
			if (subscriberOptional.isPresent()) {
				Subscriber subscriber = subscriberOptional.get();
				result = SubscriberCommandHelper.handleSubscriber(request, subscriber);
			} else { // this means login is an e-mail
				role = user.getRole();
				result = UserRolePathProvider.getInstance().providePath(role);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to retrieve Subscriber data for login - " + login, e);
			result = RouteHelper.ERROR_500;
		}

		return result;
	}

}
