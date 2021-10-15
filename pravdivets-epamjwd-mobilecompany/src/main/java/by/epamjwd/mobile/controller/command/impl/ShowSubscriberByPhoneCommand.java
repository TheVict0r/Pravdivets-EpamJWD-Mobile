package by.epamjwd.mobile.controller.command.impl;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneNumberFormatter;

public class ShowSubscriberByPhoneCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(ShowSubscriberByPhoneCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		ServiceProvider provider = ServiceProvider.getInstance();
		SubscriberService subscriberService = provider.getSubscriberService();
		HttpSession session = request.getSession();
		Role userRole = (Role)session.getAttribute(AttributeName.ROLE);
		
		String phoneNumber = request.getParameter(ParameterName.PHONE_NUMBER);
		
		RouteHelper result = null;
		try {
			Subscriber subscriber = subscriberService.findSubscriberByPhoneNumber(phoneNumber);
			request.setAttribute(AttributeName.SUBSCRIBER, subscriber);
			String phoneNumberFormat = PhoneNumberFormatter.formatPhomeNumber(phoneNumber);
			request.setAttribute(AttributeName.PHONE_NUMBER_FORMAT, phoneNumberFormat);
			result = new RouteHelper(PagePath.SUBSCRIBER, RouteMethod.FORWARD);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain data for phone number " + phoneNumber, e);
			request.setAttribute(AttributeName.ERROR, AttributeValue.ERROR_NULL_SUBSCRIBER);
			request.setAttribute(AttributeName.PHONE_NUMBER, phoneNumber);
			String path = getPathByRole(userRole);
			result = new RouteHelper(path, RouteMethod.FORWARD);
		}
		return result;
	}

	private String getPathByRole(Role userRole) {
		Map<Role, String> pathMap = new HashMap<>();
		
		pathMap.put(Role.CUSTOMER, PagePath.ERROR_404);
		pathMap.put(Role.CONSULTANT, PagePath.SUBSCRIBER_BASE);
		pathMap.put(Role.ADMIN, PagePath.ADMIN);
		
		String result = pathMap.get(userRole);
		
		if(result == null) {
			result = PagePath.ERROR_404;
		}
		
		return result;
	}

	
	
	
	
}
