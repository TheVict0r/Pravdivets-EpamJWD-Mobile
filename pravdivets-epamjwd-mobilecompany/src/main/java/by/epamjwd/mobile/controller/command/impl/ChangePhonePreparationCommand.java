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
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneFormatter;
import by.epamjwd.mobile.util.PhoneGenerator;

public class ChangePhonePreparationCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(ChangePhonePreparationCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();		
		session.setAttribute(AttributeName.ACTIVATE_EDIT, AttributeValue.PHONE);
		RouteHelper result = RouteHelper.ERROR;

		String newPhone;
		String newPhoneFormat;
		try {
			newPhone = PhoneGenerator.generatePhone();
			newPhoneFormat = PhoneFormatter.formatPhone(newPhone);
			session.setAttribute(AttributeName.NEW_PHONE, newPhone);
			session.setAttribute(AttributeName.NEW_PHONE_FORMAT, newPhoneFormat);
		} catch (ServiceException e) {
			LOGGER.error("Error during new phone number generation pricess", e);
			result = RouteHelper.ERROR_500;
		}
		
		result = new RouteHelper(PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
		return result;

	}

}
