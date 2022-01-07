package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.SubscriberCommandHelper;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowSubscriberListByUserIdCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ShowSubscriberListByUserIdCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		long id;
		id = NumericParser.parseLongValue(session.getAttribute(AttributeName.SUBSCRIBER_USER_ID));
		
		if(id == NumericParser.INVALID_VALUE) {
			id = NumericParser.parseLongValue(session.getAttribute(AttributeName.USER_ID));
		}

		if(id == NumericParser.INVALID_VALUE) {
			session.setAttribute(AttributeName.ERROR, AttributeValue.WRONG_DATA);
			return RouteHelper.ERROR_404;
		}
		
		ServiceProvider provider = ServiceProvider.getInstance();
		SubscriberService subscriberService = provider.getSubscriberService();

		RouteHelper result = null;
		try {
			List<Subscriber> subscriberList = subscriberService.findSubscriberListByUserId(id);
			if (subscriberList.isEmpty()) {
				result = RouteHelper.ERROR_404;
			} else {
				result = SubscriberCommandHelper.getInstance().handleSubscriberListForward(request, subscriberList);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error in getting subscriber list for ID - " + id, e);
			result = RouteHelper.ERROR_500;
		}
		return result;
	}

}
