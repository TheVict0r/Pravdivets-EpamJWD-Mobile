package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.SubscriberCommandHelper;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowSubscriberListByFullNameCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(ShowSubscriberListByFullNameCommand.class);


	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		SubscriberService subscriberService = provider.getSubscriberService();

		String firstName = request.getParameter(ParameterName.FIRST_NAME);
		String middleName = request.getParameter(ParameterName.MIDDLE_NAME);
		String lastName = request.getParameter(ParameterName.LAST_NAME);
		List<Subscriber> subscriberList = null;
		RouteHelper result = null;
		
		try {
			subscriberList = subscriberService.findSubscriberListByFullName(firstName, middleName, lastName);
			result = SubscriberCommandHelper.getInstance().handleSubscribersList(request, subscriberList);
			if (subscriberList.isEmpty()) {
				request.setAttribute(AttributeName.ERROR, AttributeValue.WRONG_NAME);
				request.setAttribute(AttributeName.FIRST_NAME, firstName);
				request.setAttribute(AttributeName.MIDDLE_NAME, middleName);
				request.setAttribute(AttributeName.LAST_NAME, lastName);
				result = new RouteHelper(PagePath.SUBSCRIBER_BASE, RouteMethod.FORWARD);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain abonent data for " + firstName + " " + middleName + " " + lastName, e);
			result = new RouteHelper(PagePath.ERROR, RouteMethod.FORWARD);
		}
		return result;
	}

}
