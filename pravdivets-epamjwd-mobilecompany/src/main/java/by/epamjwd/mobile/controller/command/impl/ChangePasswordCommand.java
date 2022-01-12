package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.User;
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

public class ChangePasswordCommand implements Command {

	/*
	 * For consultant role only.  
	 * Subscribers use password repair option as it changes password in it's core 
	 */

	private final static Logger LOGGER = LogManager.getLogger(ChangePasswordCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserService userService = ServiceProvider.getInstance().getUserService();
		User user = (User)session.getAttribute(AttributeName.CONSULTANT);

		if(request.getParameter(ParameterName.OLD_PASSWORD)  == null || 
		   request.getParameter(ParameterName.NEW_PASSWORD1) == null ||
		   request.getParameter(ParameterName.NEW_PASSWORD2) == null){
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.CHANGE_PASSWORD_REDIRECT, RouteMethod.REDIRECT);
		}
		
		if( ! userService.isPasswordCorrect(user, request.getParameter(ParameterName.OLD_PASSWORD))) {
			return provideErrorMessage(session, AttributeValue.WRONG_PASSWORD);
		}
		
		if( ! (request.getParameter(ParameterName.NEW_PASSWORD1)).equals(request.getParameter(ParameterName.NEW_PASSWORD2))){
			return provideErrorMessage(session, AttributeValue.MISSMATCHED_PASSWORDS);		}

		if(!userService.isPasswordCorrect(request.getParameter(ParameterName.NEW_PASSWORD1))) {
			return provideErrorMessage(session, AttributeValue.INCORRECT_PASSWORD);
		}

		try {
			userService.updatePassword(user, request.getParameter(ParameterName.NEW_PASSWORD1));
		} catch (ServiceException e) {
			LOGGER.error("Error while updating password for user - " + user.getId() + e);
			return RouteHelper.ERROR_500;
		}
		
		session.setAttribute(AttributeName.CHANGE_PASSWORD, AttributeValue.TRUE);
		return new RouteHelper(PagePath.CONSULTANT_REDIRECT, RouteMethod.REDIRECT);
	}

	private RouteHelper provideErrorMessage(HttpSession session, String attributeValue) {
		session.setAttribute(AttributeName.ERROR, attributeValue);
		return new RouteHelper(PagePath.CHANGE_PASSWORD_REDIRECT, RouteMethod.REDIRECT);
	}

}
