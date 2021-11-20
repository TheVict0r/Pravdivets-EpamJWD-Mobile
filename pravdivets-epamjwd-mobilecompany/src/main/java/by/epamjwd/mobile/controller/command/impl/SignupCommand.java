package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class SignupCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(SignupCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String phone     = request.getParameter(ParameterName.PHONE);
		String password1 = request.getParameter(ParameterName.PASSWORD1);
		String password2 = request.getParameter(ParameterName.PASSWORD2);
		
		UserService userService = ServiceProvider.getInstance().getUserService();
		HttpSession session = request.getSession();
		
		if(!password1.equals(password2)) {
			return provideErrorMessage(session, phone, AttributeValue.MISSMATCHED_PASSWORDS);
		}
		
		if(!userService.isPasswordCorrect(password1)) {
			return provideErrorMessage(session, phone, AttributeValue.INCORRECT_PASSWORD);
		}
		
		try {
			if(userService.doesPhoneExist(phone)) {
				if(userService.isSignupRequired(phone)) {
					userService.updatePassword(phone, password1);
					session.setAttribute(AttributeName.SIGN_UP, AttributeValue.TRUE);

					// ДОБАВИТЬ ПОДТВЕРЖДЕНИЕ ПО E-MAIL
					
				} else {
					return provideErrorMessage(session, phone, AttributeValue.ALREADY_SIGNED_UP);
				}
			} else {
				return provideErrorMessage(session, phone, AttributeValue.NO_USER);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error when signing up command for phone - " + phone, e);
			return RouteHelper.ERROR;
		}
		return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
	}

	private RouteHelper provideErrorMessage(HttpSession session, String phone, String attributeValue) {
		session.setAttribute(AttributeName.PHONE, phone);
		session.setAttribute(AttributeName.ERROR, attributeValue);
		return new RouteHelper(PagePath.SIGNUP_REDIRECT, RouteMethod.REDIRECT);
	}
	
}
