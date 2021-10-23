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
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowSubsciberListByUserIdCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ShowSubsciberListByUserIdCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = String.valueOf(session.getAttribute(AttributeName.USER_ID));
		
		ServiceProvider provider = ServiceProvider.getInstance();
		SubscriberService subscriberService = provider.getSubscriberService();

		RouteHelper result = null;
		try {
			List<Subscriber> subscriberList = subscriberService.findSubscriberListByUserId(id);
			result = SubscriberCommandHelper.getInstance().handleSubscribersList(request, subscriberList);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain subscriber user data for ID " + id, e);
			result = new RouteHelper(PagePath.ERROR, RouteMethod.FORWARD);
		}
		return result;
	}

}
