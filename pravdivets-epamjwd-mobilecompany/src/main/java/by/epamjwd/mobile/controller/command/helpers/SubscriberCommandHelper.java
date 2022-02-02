package by.epamjwd.mobile.controller.command.helpers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.SubscriberStatus;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneFormatter;

public class SubscriberCommandHelper {

	private final static Logger LOGGER = LogManager.getLogger(SubscriberCommandHelper.class);
	
	private SubscriberCommandHelper() {
	}

	/**
	 * Makes preparation work to present all subscriber's data 
	 * and sets these data to the session. 
	 * 
	 * Retrieves from data storage all entities that are relevant 
	 * to the particular {@code Subscriber} - User and tariff Plan.
	 * 
	 * @param request - http request from servlet
	 * 
	 * @param subscriber - Subscriber instance
	 * 
	 * @return - RouteHelper containing path to page and route method
	 * 
	 * @throws ServiceException
	 */
	public static RouteHelper handleSubscriber(HttpServletRequest request, Subscriber subscriber) throws ServiceException {
		RouteHelper result = RouteHelper.ERROR;
		HttpSession session = request.getSession();
		UserService userService = ServiceProvider.getInstance().getUserService();

		User subscriberUser = null;
		long planID = subscriber.getPlanId();
		String phone = subscriber.getPhone();
		String phoneFormat = PhoneFormatter.formatPhone(phone);
		
		session.setAttribute(AttributeName.PHONE_FORMAT, phoneFormat);
		session.setAttribute(AttributeName.SUBSCRIBER, subscriber);
		
		Optional<User> userOptional = userService.findUserByPhone(phone);
		if (userOptional.isPresent()) {
			subscriberUser = userOptional.get();
			session.setAttribute(AttributeName.SUBSCRIBER_USER, subscriberUser);
		} else {
			LOGGER.error("Can't find user for phone number - " + phone);
			return RouteHelper.ERROR_404;
		}
		
		result = PlanCommandHelper.handlePlanByID(session, planID, PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT, LOGGER);
		
		if (subscriber.getStatus() == SubscriberStatus.DEACTIVATED 
				&& (Role)(session.getAttribute(AttributeName.ROLE)) == Role.SUBSCRIBER) {
			result = handleDeactivatedSubscriber(session);
		}
		
		return result;
	}


	/**
	 * Handles multiple subscribers stored in the List.
	 * 
	 * @param request - http request from servlet
	 * 
	 * @param subscriberList - List with Subscribers
	 * 
	 * @return - RouteHelper containing path to page and route method
	 * 
	 * @throws ServiceException
	 */
	public static RouteHelper handleSubscriberList(HttpServletRequest request, List<Subscriber> subscriberList)
			throws ServiceException {
		HttpSession session = request.getSession();
		RouteHelper result = RouteHelper.ERROR;
		if (subscriberList.size() == 1) {
			Subscriber subscriber = subscriberList.get(0);
			result = handleSubscriber(request, subscriber);
		} else if (subscriberList.size() > 1){
			long userId = subscriberList.get(0).getUserId();
			session.setAttribute(AttributeName.SUBSCRIBER_USER_ID, userId);
			result = new RouteHelper(PagePath.SUBSCRIBER_LIST_REDIRECT, RouteMethod.REDIRECT);
		}
		return result;
	}

	/**
	 * Blocks the logination access for the deactivated Subscriber 
	 * 
	 * @param session - http-session
	 * 
	 * @return - RouteHelper containing path to page and route method
	 */
	private static RouteHelper handleDeactivatedSubscriber (HttpSession session) {
			session.setAttribute(AttributeName.ERROR, AttributeValue.DEACTIVATED);
			session.removeAttribute(AttributeName.USER_ID);
			session.removeAttribute(AttributeName.FIRST_NAME_HEADER);
			session.removeAttribute(AttributeName.LAST_NAME_HEADER);
			session.removeAttribute(AttributeName.ROLE);
			return new RouteHelper(PagePath.LOGIN_REDIRECT, RouteMethod.REDIRECT);
		}

	
	/**
	 * Cleans session from Subscriber's attributes no longer needed. 
	 * 
	 * @param session - http-session
	 */
	public static void clearSessionFromSubscriberAttributes(HttpSession session) {
		session.removeAttribute(AttributeName.SUBSCRIBER);
		session.removeAttribute(AttributeName.SUBSCRIBER_USER_ID);
		session.removeAttribute(AttributeName.SUBSCRIBER_USER);
		session.removeAttribute(AttributeName.PHONE);
		session.removeAttribute(AttributeName.PHONE_FORMAT);
		session.removeAttribute(AttributeName.PLAN);
		session.removeAttribute(AttributeName.ALL_PLANS);
		session.removeAttribute(AttributeName.ACTIVATE_EDIT);
		session.removeAttribute(AttributeName.NEW_PHONE_FORMAT);
		session.removeAttribute(AttributeName.NEW_PHONE);
		session.removeAttribute(AttributeName.ALL_SERVICES);
		session.removeAttribute(AttributeName.BILL_LIST);
	}
	
}
