package by.epamjwd.mobile.controller.command.impl;


import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
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
		
		String phoneNumber = request.getParameter(ParameterName.PHONE_NUMBER);
		Subscriber subscriber = null;
		
		RouteHelper result = null;
		try {
			Optional<Subscriber> subscriberOptional = subscriberService.findSubscriberByPhoneNumber(phoneNumber);
			
			if (subscriberOptional.isPresent()) {
				subscriber = subscriberOptional.get();
				request.setAttribute(AttributeName.SUBSCRIBER, subscriber);
				String phoneNumberFormat = PhoneNumberFormatter.formatPhomeNumber(phoneNumber);
				request.setAttribute(AttributeName.PHONE_NUMBER_FORMAT, phoneNumberFormat);
				result = new RouteHelper(PagePath.SUBSCRIBER, RouteMethod.FORWARD);
			} else {
				request.setAttribute(AttributeName.ERROR, AttributeValue.WRONG_PHONE);
				request.setAttribute(AttributeName.PHONE_NUMBER, phoneNumber);
				result = new RouteHelper(PagePath.SUBSCRIBER_BASE, RouteMethod.FORWARD);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain data for phone number " + phoneNumber, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}
	
}
