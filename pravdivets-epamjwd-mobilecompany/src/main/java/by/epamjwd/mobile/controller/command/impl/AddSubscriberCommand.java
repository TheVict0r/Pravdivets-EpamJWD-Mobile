package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.CustomerService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneGenerator;

public class AddSubscriberCommand implements Command {
	private final static Logger LOGGER = LogManager.getLogger(AddSubscriberCommand.class);
	private final static long EMPTY_ID = 0L;
	private final static long ERROR_ID = -1L;

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserService userService = ServiceProvider.getInstance().getUserService();
		SubscriberService subscriberService = ServiceProvider.getInstance().getSubscriberService();
		CustomerService customerService = ServiceProvider.getInstance().getCustomerService();

		RouteHelper result = RouteHelper.ERROR;
		
		long subscriberID = ERROR_ID;
		String passport = (String.valueOf(session.getAttribute(AttributeName.PASSPORT)));
		String subscriberUserFlag = String.valueOf(session.getAttribute(AttributeName.SUBSCRIBER_USER_FLAG));
		long planID = NumericParser.parseUnsignedLongValue(request.getParameter(ParameterName.PLAN_ID));
		String phone;

		try {
			phone = PhoneGenerator.getInstance().provideFreePhone();
		} catch (ServiceException e) {
			LOGGER.error("Error while providing new phone number ", e);
			return RouteHelper.ERROR_500;
		}
		
		if (passport == null || passport.isBlank() || planID == NumericParser.INVALID_VALUE) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.ADD_SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
		}

		
		if (subscriberUserFlag.equals(AttributeValue.NEW)) {
			String firstName = request.getParameter(ParameterName.SUBSCRIBER_FIRST_NAME);
			String middleName = request.getParameter(ParameterName.SUBSCRIBER_MIDDLE_NAME);
			String lastName = request.getParameter(ParameterName.SUBSCRIBER_LAST_NAME);
			String email = request.getParameter(ParameterName.EMAIL);

			if (firstName == null || firstName.isBlank() || lastName == null || lastName.isBlank() || 
					middleName == null || email == null || email.isBlank()) {
				session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
				return new RouteHelper(PagePath.ADD_SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
			}

			
			try {
				if (userService.findUserByEmail(email).isPresent()) {
					session.setAttribute(AttributeName.ERROR, AttributeValue.BOOKED_EMAIL);
					session.setAttribute(AttributeName.SUBSCRIBER_FIRST_NAME, firstName);
					session.setAttribute(AttributeName.SUBSCRIBER_MIDDLE_NAME, middleName);
					session.setAttribute(AttributeName.SUBSCRIBER_LAST_NAME, lastName);
					session.setAttribute(AttributeName.EMAIL, email);
					return new RouteHelper(PagePath.ADD_SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
				}
			} catch (ServiceException e1) {
				LOGGER.error("Error when verifying the existence of a user by e-mail " + email, e1);
				result = RouteHelper.ERROR_500;
			}

			removeUnusedAttributes(session);


			try {
				User user = userService.buildSubscriberUser(firstName, middleName, lastName, passport, email);
				Subscriber subscriber = subscriberService.buildSubscriber(phone, planID, EMPTY_ID);
				if (subscriber != null) { // it can be == null if there is no plan with planID
					subscriberID = customerService.addNewCustomer(user, subscriber);
				} else {
					result = RouteHelper.ERROR_404;
				}
			} catch (ServiceException e) {
				LOGGER.error("Error when adding a new subscriber with passport number - " + passport, e);
				result = RouteHelper.ERROR_500;
			}

		} else { // This user already has a phone number. We can add another one.
			User currentUser = (User) session.getAttribute(AttributeName.SUBSCRIBER_USER);

			if (currentUser == null) {
				session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
				return new RouteHelper(PagePath.ADD_SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
			}

			session.removeAttribute(AttributeName.SUBSCRIBER_USER);
			removeUnusedAttributes(session);

			long userID = currentUser.getId();
			try {
				Subscriber subscriber = subscriberService.buildSubscriber(phone, planID, userID);
				if (subscriber != null) { // it could be null in the case if there is no plan with planId
					subscriberID = subscriberService.addNewSubscriberToExistingUser(subscriber);
				} else {
					result = RouteHelper.ERROR_404;
				}
			} catch (ServiceException e) {
				LOGGER.error("Error when adding a new subscriber to existing user with passport number - " + passport, e);
				result = RouteHelper.ERROR_500;
			}
		}

		session.setAttribute(AttributeName.SUBSCRIBER_ID, subscriberID);
		result = new RouteHelper(PagePath.NEW_SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);

		return result;
	}

	private void removeUnusedAttributes(HttpSession session) {
		session.removeAttribute(AttributeName.SUBSCRIBER_USER_FLAG);
		session.removeAttribute(AttributeName.PASSPORT);
		session.removeAttribute(AttributeName.PHONE);

	}

}
