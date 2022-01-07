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
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.SubscriberCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowSubscriberListByPassportCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(ShowSubscriberListByPassportCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		SubscriberService subscriberService = provider.getSubscriberService();
		RouteHelper result = RouteHelper.ERROR;
		HttpSession session = request.getSession();
		String passport = request.getParameter(ParameterName.PASSPORT);
		
		if( passport == null || passport.isBlank()) {
				session.setAttribute(AttributeName.ERROR, AttributeValue.WRONG_DATA);
				return new RouteHelper(PagePath.ADD_CONSULTANT_REDIRECT, RouteMethod.REDIRECT);
			}
		
		List<Subscriber> subscriberList = null;
		try {
			subscriberList = subscriberService.findSubscriberListByPassport(passport);
			if(subscriberList.isEmpty()) {
				request.setAttribute(AttributeName.ERROR, AttributeValue.WRONG_PASSPORT);
				request.setAttribute(AttributeName.PASSPORT, passport);
				result = new RouteHelper(PagePath.SUBSCRIBER_OPERATIONS, RouteMethod.FORWARD);	
			} else {
				result = SubscriberCommandHelper.getInstance().handleSubscriberListRedirect(request, subscriberList);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error in getting subscriber data for passport - " + passport, e);
			result = RouteHelper.ERROR_500;
		}
		return result;	
	}

}
