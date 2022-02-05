package by.epamjwd.mobile.controller.command.impl;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.SubscriberStatus;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ChangeStatusCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ChangeStatusCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute(AttributeName.ACTIVATE_EDIT);
		int newStatusID = NumericParser.parseUnsignedIntValue(request.getParameter(ParameterName.NEW_STATUS));

		if (newStatusID == NumericParser.INVALID_VALUE) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
		}

		SubscriberStatus newStatus = SubscriberStatus.values()[newStatusID];

		Subscriber subscriber = (Subscriber) session.getAttribute(AttributeName.SUBSCRIBER);
		subscriber.setStatus(newStatus);
		subscriber.setStatusDate(new Date());
		long subscriberID = subscriber.getId();

		SubscriberService subscriberService = ServiceProvider.getInstance().getSubscriberService();
		try {
			subscriberService.updateSubscriber(subscriber);
		} catch (ServiceException e) {
			LOGGER.error("Error during updating subscriber data", e);
			return RouteHelper.ERROR_500;
		}

		try {
			Optional<Subscriber> updatedSubscriberOptional = subscriberService.findSubscriberById(subscriberID);
			if (updatedSubscriberOptional.isPresent()) {
				Subscriber updatedSubscriber = updatedSubscriberOptional.get();
				session.setAttribute(AttributeName.SUBSCRIBER, updatedSubscriber);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error retrieving updated subscriber by ID - " + subscriberID, e);
			return RouteHelper.ERROR_500;
		}

		return new RouteHelper(PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
	}

}
