package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.SubscriberCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowSubscriberFromSessionCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(ShowSubscriberFromSessionCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Subscriber subscriber = (Subscriber)session.getAttribute(AttributeName.SUBSCRIBER);
		
		RouteHelper result;
		try {
			result = SubscriberCommandHelper.getInstance().handleSubscriber(request, subscriber);
		} catch (ServiceException e) {
			LOGGER.error("Error in SubscriberCommandHelper for subscriber with ID - " + subscriber.getId(), e);
			result = new RouteHelper(PagePath.ERROR, RouteMethod.FORWARD);
		}
		
		return result;
	}

}
