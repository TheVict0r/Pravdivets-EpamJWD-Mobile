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
import by.epamjwd.mobile.util.PhoneFormatter;

public class PasswordRepair1SendCodeCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(PasswordRepair1SendCodeCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserService userService = ServiceProvider.getInstance().getUserService();
		String phone = request.getParameter(ParameterName.PHONE);
		
		try {
			if(!userService.doesPhoneExist(phone)) {
				session.setAttribute(AttributeName.PHONE, phone);
				session.setAttribute(AttributeName.ERROR, AttributeValue.WRONG_PHONE);
				return new RouteHelper(PagePath.PASSWORD_REPAIR_REDIRECT, RouteMethod.REDIRECT);
			}
		} catch (ServiceException e1) {
			LOGGER.error("Error while checking does phone exist - " + phone + e1);
			return RouteHelper.ERROR;
		}
		
		String phoneFormat = PhoneFormatter.formatPhone(phone);
		session.setAttribute(AttributeName.PHONE_FORMAT, phoneFormat);
		try {
			int code = userService.sendCodeByMail(phone);
		} catch (ServiceException e) {
			LOGGER.error("Error while sending an authentication code to " + phone + e);
			return RouteHelper.ERROR;
		}
		
		
		System.out.println("ПХОН - " + phoneFormat);
		
		return new RouteHelper(PagePath.PASSWORD_REPAIR_REDIRECT, RouteMethod.REDIRECT);
	}

}
