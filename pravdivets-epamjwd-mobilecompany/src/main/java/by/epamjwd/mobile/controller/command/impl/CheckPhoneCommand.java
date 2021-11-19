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

public class CheckPhoneCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(CheckPhoneCommand.class);

	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserService userService = ServiceProvider.getInstance().getUserService();
		String phone = request.getParameter(ParameterName.PHONE);
		session.setAttribute(AttributeName.PHONE, phone);

		RouteHelper result;
		
		try {
			if (userService.doesPhoneExist(phone)) {
				result = new RouteHelper(PagePath.CODE_REQUEST_REDIRECT, RouteMethod.REDIRECT);
			} else {
				session.setAttribute(AttributeName.ERROR, AttributeValue.WRONG_PHONE);
				result = new RouteHelper(PagePath.PHONE_REQUEST_REDIRECT, RouteMethod.REDIRECT);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error while checking does phone exist - " + phone + e);
			return RouteHelper.ERROR;
		}

		return result;
	
	}

}
