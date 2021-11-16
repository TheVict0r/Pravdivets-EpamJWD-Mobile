package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String phone     = request.getParameter(ParameterName.PHONE);
		String password1 = request.getParameter(ParameterName.PASSWORD1);
		String password2 = request.getParameter(ParameterName.PASSWORD2);
		
		UserService userService = ServiceProvider.getInstance().getUserService();
		HttpSession session = request.getSession();
		
		if(!password1.equals(password2)) {
			session.setAttribute(AttributeName.PHONE, phone);
			session.setAttribute(AttributeName.ERROR, AttributeValue.MISSMATCHED_PASSWORDS);
			return new RouteHelper(PagePath.SIGNUP_REDIRECT, RouteMethod.REDIRECT);
		}
		
		if(!userService.isPasswordCorrect(password1)) {
			session.setAttribute(AttributeName.PHONE, phone);
			session.setAttribute(AttributeName.ERROR, AttributeValue.INCORRECT_PASSWORDS);
			return new RouteHelper(PagePath.SIGNUP_REDIRECT, RouteMethod.REDIRECT);
		}
		
		
		try {
			if(userService.isPhoneExist(phone)) {
				if(userService.isSignupRequired(phone)) {
					userService.signup(phone, password1);
				} else {
					session.setAttribute(AttributeName.PHONE, phone);
					session.setAttribute(AttributeName.ERROR, AttributeValue.ALREADY_SIGNEDUP);
					return new RouteHelper(PagePath.SIGNUP_REDIRECT, RouteMethod.REDIRECT);
				}
			} else {
				session.setAttribute(AttributeName.PHONE, phone);
				session.setAttribute(AttributeName.ERROR, AttributeValue.NO_USER);
				return new RouteHelper(PagePath.SIGNUP_REDIRECT, RouteMethod.REDIRECT);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		return new RouteHelper(PagePath.SIGNUP, RouteMethod.FORWARD);

	}

}
