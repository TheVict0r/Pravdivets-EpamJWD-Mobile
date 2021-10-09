package by.epamjwd.mobile.controller.command.impl;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.AbonentService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class LoginCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		User user = null;
		AbonentService abonentService = provider.getAbonentService();
		Abonent abonent = null;
		
		String path = null;
		HttpSession session = request.getSession();
		RouteHelper result = null;

		String login = request.getParameter(ParameterName.LOGIN);
		char[] password = request.getParameter(ParameterName.PASSWORD).toCharArray();
		
		if (login == null || String.valueOf(password) == null) {
			session.setAttribute(AttributeName.ERROR, AttributeValue.LOGIN_ERROR);
			return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
		}

		try {
			if (abonentService.isPhoneNumber(login)) {
				int phoneNumber = Integer.parseInt(login);
				user = userService.findUserByPhoneNumber(phoneNumber);
				abonent = abonentService.findAbonentByPhoneNumber(phoneNumber);
				session.setAttribute(AttributeName.ABONENT_ID, abonent.getId());
				path = PagePath.ABONENT_REDIRECT;
			} else if (userService.isEmail(login)) {
				user = userService.findUserByEmail(login);
				path = userService.findPathByUserRole(user.getRole());
			} else {
				LOGGER.error("Unable to obtain user data for login - " + login);
				setErrorAttributes(session, login, password);
				path = PagePath.LOGIN_REDIRECT;
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain user data for login - " + login, e);
			setErrorAttributes(session, login, password);
			path = PagePath.LOGIN_REDIRECT;
		}

		if(!userService.isPasswordValid(user, password)) {
			setErrorAttributes(session, login, password);
			user = null;
		}
		
		Arrays.fill(password, ' ');
		
		if (user != null) {
			session.setAttribute(AttributeName.USER_ID, user.getId());
			session.setAttribute(AttributeName.FIRST_NAME, user.getFirstName());
			session.setAttribute(AttributeName.LAST_NAME, user.getLastName());
			session.setAttribute(AttributeName.ROLE, user.getRole());
		} else {
			session.setAttribute(AttributeName.ERROR, AttributeValue.LOGIN_ERROR);
			path = PagePath.LOGIN_REDIRECT;
		}
		
		result = new RouteHelper(path, RouteMethod.REDIRECT);

		return result;
	}

	private void setErrorAttributes(HttpSession session, String login, char[] password) {
		session.setAttribute(AttributeName.ERROR, AttributeValue.LOGIN_ERROR);
		session.setAttribute(AttributeName.LOGIN, login);
		session.setAttribute(AttributeName.PASSWORD, String.valueOf(password));
	}

}
