package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class NewPasswordCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(NewPasswordCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String phone = (String)session.getAttribute(AttributeName.PHONE);
		String password1 = request.getParameter(ParameterName.PASSWORD1);
		String password2 = request.getParameter(ParameterName.PASSWORD2);
		UserService userService = ServiceProvider.getInstance().getUserService();

		if(!password1.equals(password2)) {
			return provideErrorMessage(session, AttributeValue.MISSMATCHED_PASSWORDS);
		}
		
		if(!userService.isPasswordCorrect(password1)) {
			return provideErrorMessage(session, AttributeValue.INCORRECT_PASSWORD);
		}

		try {
			userService.updatePassword(phone, password1);
		} catch (ServiceException e) {
			LOGGER.error("Error while updating user's password. Users phone - " + phone + e);
			return RouteHelper.ERROR_500;
		}
		
		session.setAttribute(AttributeName.CHANGE_PASSWORD, AttributeValue.TRUE);
		session.removeAttribute(AttributeName.PHONE);
		session.removeAttribute(AttributeName.MODE);
		return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
	}

	
	private RouteHelper provideErrorMessage(HttpSession session, String attributeValue) {
		session.setAttribute(AttributeName.ERROR, attributeValue);
		return new RouteHelper(PagePath.NEW_PASSWORD_REDIRECT, RouteMethod.REDIRECT);
	}

}
