package by.epamjwd.mobile.controller.command.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
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

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = ServiceProvider.getInstance().getUserService();
		SubscriberService subscriberService = ServiceProvider.getInstance().getSubscriberService();
		HttpSession session = request.getSession();
		RouteHelper result = RouteHelper.ERROR;
		User user = null;

		String login = request.getParameter(ParameterName.LOGIN);

		if (    login == null || login.isBlank() 
				|| request.getParameter(ParameterName.PASSWORD) == null
				|| request.getParameter(ParameterName.PASSWORD).isBlank()) {
			session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR_LOGIN);
			return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
		}

		try {
			Optional<User> userOptional = userService.findUserByLogin(login);

			if (userOptional.isPresent() 
					&& userService.isPasswordCorrect(userOptional.get(),
					request.getParameter(ParameterName.PASSWORD))) {
				user = userOptional.get();
				session.setAttribute(AttributeName.USER_ID, user.getId());
				session.setAttribute(AttributeName.FIRST_NAME_HEADER, user.getFirstName());
				session.setAttribute(AttributeName.LAST_NAME_HEADER, user.getLastName());
				session.setAttribute(AttributeName.ROLE, user.getRole());
			} else {
				session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR_LOGIN);
				session.setAttribute(AttributeName.LOGIN, login);
				session.setAttribute(AttributeName.PASSWORD, 
						request.getParameter(ParameterName.PASSWORD));
				return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to retrieve user data for login - " + login, e);
			return RouteHelper.ERROR_500;
		}
		
		switch (user.getRole()) {
		case ADMIN:
			result = new RouteHelper(PagePath.ADMIN_REDIRECT, RouteMethod.REDIRECT);
			break;
		case CONSULTANT:
			result = new RouteHelper(PagePath.SUBSCRIBER_OPERATIONS_REDIRECT, RouteMethod.REDIRECT);
			break;
		case SUBSCRIBER:
			try {
				if (InputDataValidator.isPhone(login) && subscriberService.isPhoneExist(login)) {
					Subscriber subscriber = subscriberService.findSubscriberByPhone(login).get();
					result = SubscriberCommandHelper.getInstance().handleSubscriber(request, subscriber);
				} else {// this means login is an e-mail and one User could have multiple phone numbers (and multiple Subscriber entities as well)
					List<Subscriber> subscriberList = subscriberService.findSubscriberListByUserId(user.getId());
					result = SubscriberCommandHelper.getInstance().handleSubscriberListRedirect(request,
							subscriberList);
				}
			} catch (ServiceException e) {
				LOGGER.error("Unable to retrieve Subscriber data for user ID - " + user.getId(), e);
				result = RouteHelper.ERROR_500;
			}
			break;
		default:
			result = new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
		}
		
		return result;
	}

}
