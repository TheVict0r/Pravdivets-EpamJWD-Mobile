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
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class CheckPhoneCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(CheckPhoneCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		SubscriberService subscriberService = ServiceProvider.getInstance().getSubscriberService();
		
		String phone = request.getParameter(ParameterName.PHONE);
		session.setAttribute(AttributeName.PHONE, phone);
		String mode = (String)session.getAttribute(AttributeName.MODE);
		
		if( phone == null || phone.isBlank()  || 
			 mode == null || mode.isBlank()) {
			 session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			 return RouteHelper.ERROR;
		}
		RouteHelper result;
		
		try {
			if (subscriberService.isPhoneExist(phone)) {
				if(AttributeValue.SIGN_UP.equals(mode) && !subscriberService.isSignupRequired(phone)) {
					return provideErrorMessage(session, AttributeValue.ALREADY_SIGNED_UP);
				}
				result = new RouteHelper(PagePath.CODE_SEND_REDIRECT, RouteMethod.REDIRECT);
			} else {
				return provideErrorMessage(session, AttributeValue.WRONG_PHONE);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error while checking does phone exist - " + phone + e);
			return RouteHelper.ERROR_500;
		}

		return result;
	}

	
	private RouteHelper provideErrorMessage(HttpSession session, String attributeValue) {
		session.setAttribute(AttributeName.ERROR, attributeValue);
		return new RouteHelper(PagePath.PHONE_REQUEST_REDIRECT, RouteMethod.REDIRECT);
	}
	
}
