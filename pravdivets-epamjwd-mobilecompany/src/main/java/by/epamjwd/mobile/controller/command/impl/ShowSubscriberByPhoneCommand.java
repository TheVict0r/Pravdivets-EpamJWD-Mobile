package by.epamjwd.mobile.controller.command.impl;


import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.command.SubscriberCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneFormatter;

public class ShowSubscriberByPhoneCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(ShowSubscriberByPhoneCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		SubscriberService subscriberService = provider.getSubscriberService();
		int phone;
		phone = NumericParser.parseIntValue(request.getParameter(ParameterName.PHONE));

		
		Subscriber subscriber = null;
		
		RouteHelper result = null;
		try {
			Optional<Subscriber> subscriberOptional = subscriberService.findSubscriberByPhone(phone);
			
			if (subscriberOptional.isPresent()) {
				subscriber = subscriberOptional.get();
				result = SubscriberCommandHelper.getInstance().handleSubscriber(request, subscriber);
			} else {
				request.setAttribute(AttributeName.ERROR, AttributeValue.WRONG_PHONE);
				request.setAttribute(AttributeName.PHONE, phone);
				result = new RouteHelper(PagePath.SUBSCRIBER_OPERATIONS, RouteMethod.FORWARD);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain data for phone number " + phone, e);
			result = new RouteHelper(PagePath.ERROR, RouteMethod.FORWARD);
		}
		return result;
	}
	
}
