package by.epamjwd.mobile.controller.command.impl;

import java.util.NoSuchElementException;

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
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneNumberFormatter;

public class ShowSubscriberByIDCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(ShowSubscriberByIDCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		ServiceProvider provider = ServiceProvider.getInstance();
		SubscriberService subscriberService = provider.getSubscriberService();
		HttpSession session = request.getSession();
		
		String id = null;
			id = String.valueOf(session.getAttribute(AttributeName.SUBSCRIBER_ID));
			
			if (id.equals("null")) {
				id = request.getParameter(ParameterName.SUBSCRIBER_ID);
			}
			
		RouteHelper result = null;
		try {
			Subscriber subscriber = subscriberService.findSubscriberById(id).get();
			request.setAttribute(AttributeName.SUBSCRIBER, subscriber);
			String phoneNumberFormat = PhoneNumberFormatter.formatPhomeNumber(String.valueOf(subscriber.getPhoneNumber()));
			request.setAttribute(AttributeName.PHONE_NUMBER_FORMAT, phoneNumberFormat);
			result = new RouteHelper(PagePath.SUBSCRIBER, RouteMethod.FORWARD);
		} catch (ServiceException | NoSuchElementException e) {
			LOGGER.error("Unable to obtain data for subscriber with ID " + id, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}

}
