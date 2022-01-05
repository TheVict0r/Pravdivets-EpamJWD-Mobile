package by.epamjwd.mobile.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.SubscriberCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.HashGenerator;

public class LoginCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		User user = null;
		SubscriberService subscriberService = provider.getSubscriberService();
		Subscriber subscriber = null;
		HttpSession session = request.getSession();
		String path = null;
		RouteHelper result = null;

		String login = request.getParameter(ParameterName.LOGIN);
		
		if (login == null || request.getParameter(ParameterName.PASSWORD) == null 
				|| request.getParameter(ParameterName.PASSWORD).isEmpty()) {
			session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR_LOGIN);
			return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
		}

		try {
			Optional<User> userOptional = userService.findUserByLogin(login);
			if (userOptional.isPresent()) { 
				user = userOptional.get();
			} else {
				path = prepareErrorPath(session, login, request.getParameter(ParameterName.PASSWORD));
			}
			
			Optional<Subscriber> subscriberOptional = subscriberService.findSubscriberByPhone(login);
			if (subscriberOptional.isPresent()) { //false means login is an e-mail, not a phone number
				subscriber = subscriberOptional.get();
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain user data for login - " + login, e);
			path = prepareErrorPath(session, login, request.getParameter(ParameterName.PASSWORD));
		}
		
		if(user != null && userService.isPasswordCorrect(user, request.getParameter(ParameterName.PASSWORD))) {
			session.setAttribute(AttributeName.USER_ID, user.getId());
			session.setAttribute(AttributeName.FIRST_NAME, user.getFirstName());
			session.setAttribute(AttributeName.LAST_NAME, user.getLastName());
			session.setAttribute(AttributeName.ROLE, user.getRole());
			path = findPathByUserRole(user.getRole());
		} else {
			user = null;
			path = prepareErrorPath(session, login, request.getParameter(ParameterName.PASSWORD));
		}

		if (subscriber != null && user != null) {
			try {
				result = SubscriberCommandHelper.getInstance().handleSubscriber(request, subscriber);
			} catch (ServiceException e) {
				LOGGER.error("Error in handling subscriber with ID - " + subscriber.getId(), e);
				result = RouteHelper.ERROR_500;
			}
		} else {
			result = new RouteHelper(path, RouteMethod.REDIRECT);
		}
		
		return result;
	}

	
	private String findPathByUserRole(Role role) {
		if (role == null) {
			return PagePath.LOGIN_REDIRECT;
		}
		String path = null;
		switch (role) {
		case SUBSCRIBER:
			path = PagePath.SUBSCRIBER_LIST_REDIRECT; //as subscriber may have few phone numbers
			break;
		case CONSULTANT:
			path = PagePath.SUBSCRIBER_OPERATIONS_REDIRECT;
			break;
		case ADMIN:
			path = PagePath.ADMIN_REDIRECT;
			break;
		default:
			path = PagePath.LOGIN_REDIRECT;
		}
		return path;
	}
	
	private String prepareErrorPath(HttpSession session, String login, String password) {
		session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR_LOGIN);
		session.setAttribute(AttributeName.LOGIN, login);
		session.setAttribute(AttributeName.PASSWORD, password);
		String path = PagePath.LOGIN_REDIRECT;
		
		return path;
	}
	
}
