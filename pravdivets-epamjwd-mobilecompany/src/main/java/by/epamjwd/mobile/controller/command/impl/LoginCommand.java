package by.epamjwd.mobile.controller.command.impl;


import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class LoginCommand implements Command {

	public static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	public static final String PHONE_NUMBER_REGEX = "\\d{9}";

	private final static Logger LOGGER = LogManager.getLogger(LoginCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String login = request.getParameter(ParameterName.LOGIN);
		char[] password = request.getParameter(ParameterName.PASSWORD).toCharArray();
		
		if(login==null || String.valueOf(password)==null) {
			request.getSession().setAttribute(AttributeName.ERROR, AttributeName.LOGIN_ERROR);
			return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
		}
		
		// c паролем надо поработать
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		RouteHelper result = null;
		
		try {
			User user = userService.getUserByLogin(login).get();
			request.getSession().setAttribute(AttributeName.EMAIL, user.getEmail());
			request.getSession().setAttribute(AttributeName.FIRST_NAME, user.getFirstName());
			request.getSession().setAttribute(AttributeName.LAST_NAME, user.getLastName());
			String path = userService.getPathByUserType(user);
			result = new RouteHelper(path, RouteMethod.REDIRECT);
		} catch (ServiceException | NoSuchElementException e) {
			LOGGER.error("Unable to obtain user data for " + login, e);
			request.getSession().setAttribute(AttributeName.ERROR, AttributeName.LOGIN_ERROR);
			request.getSession().setAttribute(AttributeName.LOGIN, login);
			request.getSession().setAttribute(AttributeName.PASSWORD, String.valueOf(password));
			result = new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
		}
		return result;
	}

	
	
}
