package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.Role;
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
import by.epamjwd.mobile.util.HashGenerator;

public class LoginCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		AbonentService abonentService = provider.getAbonentService();
		User user = null;
		Abonent abonent = null;
		
		String path = null;
		HttpSession session = request.getSession();
		HashGenerator hashGenerator = new HashGenerator();
		RouteHelper result = null;

		String login = request.getParameter(ParameterName.LOGIN);
		String hashPassword = hashGenerator.generateHash(request.getParameter(ParameterName.PASSWORD));
		
		if (login == null || request.getParameter(ParameterName.PASSWORD) == null) {
			session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR_LOGIN);
			return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
		}

		try {
			user = userService.findUserByLogin(login);
			abonent = abonentService.findAbonentByPhoneNumber(login);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain data for login - " + login, e);
			setErrorAttributes(session, login, request.getParameter(ParameterName.PASSWORD));
			path = PagePath.LOGIN_REDIRECT;
		}
		
		if(!userService.isPasswordValid(user, hashPassword)) {
			user = null;
			setErrorAttributes(session, login, request.getParameter(ParameterName.PASSWORD));
			path = PagePath.LOGIN_REDIRECT;
		}
		
		if (user != null) {
			session.setAttribute(AttributeName.USER_ID, user.getId());
			session.setAttribute(AttributeName.FIRST_NAME, user.getFirstName());
			session.setAttribute(AttributeName.LAST_NAME, user.getLastName());
			session.setAttribute(AttributeName.ROLE, user.getRole());
			path = findPathByUserRole(user.getRole());
		} else {
			setErrorAttributes(session, login, request.getParameter(ParameterName.PASSWORD));
			path = PagePath.LOGIN_REDIRECT;
		}
		
		if (abonent != null && user != null) {
			session.setAttribute(AttributeName.ABONENT_ID, abonent.getId());
			path = PagePath.ABONENT_REDIRECT;
		}
		
		result = new RouteHelper(path, RouteMethod.REDIRECT);
		return result;
	}

	private String findPathByUserRole(Role role) {
		if (role == null) {
			return PagePath.LOGIN_REDIRECT;
		}
		String path = null;
		switch (role) {
		case CUSTOMER:
			path = PagePath.CUSTOMER_REDIRECT;
			break;
		case CONSULTANT:
			path = PagePath.CONSULTANT_REDIRECT;
			break;
		case ADMIN:
			path = PagePath.ADMIN_REDIRECT;
			break;
		default:
			path = PagePath.LOGIN_REDIRECT;
		}
		return path;
	}
	
	private void setErrorAttributes(HttpSession session, String login, String password) {
		session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR_LOGIN);
		session.setAttribute(AttributeName.LOGIN, login);
		session.setAttribute(AttributeName.PASSWORD, password);
	}
	
}
