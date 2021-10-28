package by.epamjwd.mobile.controller.command.impl;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.command.SubscriberCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneFormatter;

public class ShowSubscriberByIDCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(ShowSubscriberByIDCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		ServiceProvider provider = ServiceProvider.getInstance();
		SubscriberService subscriberService = provider.getSubscriberService();
		HttpSession session = request.getSession();
		 
		long id;
		id = NumericParser.parseLongValue(session.getAttribute(AttributeName.SUBSCRIBER_ID));
			
			if (id == -1) {
				id = NumericParser.parseLongValue(request.getParameter(ParameterName.ID));
			}
			
		RouteHelper result = null;
		try {
			Subscriber subscriber = subscriberService.findSubscriberById(id).get();
			
			result = SubscriberCommandHelper.getInstance().handleSubscriber(request, subscriber);
		} catch (ServiceException | NoSuchElementException e) {
			LOGGER.error("Error in getting subscriber data for ID " + id, e);
			result = RouteHelper.ERROR;
		}
		return result;
	}

}
