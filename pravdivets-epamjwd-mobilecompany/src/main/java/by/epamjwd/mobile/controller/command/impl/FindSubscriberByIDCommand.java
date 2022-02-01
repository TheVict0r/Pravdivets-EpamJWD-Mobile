package by.epamjwd.mobile.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.command.helpers.SubscriberCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class FindSubscriberByIDCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(FindSubscriberByIDCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		SubscriberService subscriberService = provider.getSubscriberService();
		HttpSession session = request.getSession();
		 
		long id;
		id = NumericParser.parseLongValue(session.getAttribute(AttributeName.SUBSCRIBER_ID));
			
			if (id == NumericParser.INVALID_VALUE) {
				id = NumericParser.parseLongValue(request.getParameter(ParameterName.ID));
			}

			if(id == NumericParser.INVALID_VALUE) {
				session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
				return RouteHelper.ERROR_404;
			}
			
		RouteHelper result = RouteHelper.ERROR;
		try {
			Optional<Subscriber> subscriberOptional = subscriberService.findSubscriberById(id);
			if (subscriberOptional.isPresent()) {
				Subscriber subscriber = subscriberOptional.get();
				result = SubscriberCommandHelper.handleSubscriber(request, subscriber);
			} else {
				result = RouteHelper.ERROR_404;
			}
		} catch (ServiceException e) {
			LOGGER.error("Error while getting subscriber data for ID - " + id, e);
			result = RouteHelper.ERROR_500;
		}
		
		return result;
	}

}
