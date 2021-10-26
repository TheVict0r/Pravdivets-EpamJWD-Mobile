package by.epamjwd.mobile.controller.command;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneFormatter;

public class SubscriberCommandHelper {

	private final static Logger LOGGER = LogManager.getLogger(SubscriberCommandHelper.class);

	private SubscriberCommandHelper() {
	}

	public static SubscriberCommandHelper getInstance() {
		return Holder.INSTANCE;
	}

	public RouteHelper handleSubscriber(HttpServletRequest request, Subscriber subscriber) throws ServiceException {
		RouteHelper result = null;

		ServiceProvider provider = ServiceProvider.getInstance();
		PlanService planService = provider.getPlanService();
		UserService userService = provider.getUserService();

		request.setAttribute(AttributeName.SUBSCRIBER, subscriber);

		int phone = subscriber.getPhone();
		String phoneFormat = PhoneFormatter.formatPhone(phone);
		request.setAttribute(AttributeName.PHONE_FORMAT, phoneFormat);

		Optional<User> userOptional = userService.findUserByPhone(phone);
		User user = null;
		if (userOptional.isPresent()) {
			user = userOptional.get();
			request.setAttribute(AttributeName.USER, user);
		} else {
			LOGGER.error("Can't find user for phone number - " + phone);
			return new RouteHelper(PagePath.ERROR, RouteMethod.FORWARD);
		}
		Plan plan = planService.findPlanByID(subscriber.getPlanId());
		request.setAttribute(AttributeName.PLAN, plan);
		result = new RouteHelper(PagePath.SUBSCRIBER, RouteMethod.FORWARD);

		return result;
	}

	public RouteHelper handleSubscriberListForward(HttpServletRequest request, List<Subscriber> subscriberList)
			throws ServiceException {
		RouteHelper result = null;
		if (subscriberList.size() == 1) {
			Subscriber subscriber = subscriberList.get(0);
			result = handleSubscriber(request, subscriber);
		} else {
			request.setAttribute(AttributeName.SUBSCRIBER_LIST, subscriberList);
			result = new RouteHelper(PagePath.SUBSCRIBER_LIST, RouteMethod.FORWARD);
		}
		return result;
	}

	public RouteHelper handleSubscriberListRedirect(HttpServletRequest request, List<Subscriber> subscriberList)
			throws ServiceException {
		HttpSession session = request.getSession();
		RouteHelper result = null;
		if (subscriberList.size() == 1) {
			Subscriber subscriber = subscriberList.get(0);
			session.setAttribute(AttributeName.SUBSCRIBER, subscriber);
			result = new RouteHelper(PagePath.SUBSCRIBER_SESSION_REDIRECT, RouteMethod.REDIRECT);
		} else {
			long userId = subscriberList.get(0).getUserId();
			session.setAttribute(AttributeName.SUBSCRIBER_USER_ID, userId);
			result = new RouteHelper(PagePath.SUBSCRIBER_LIST_REDIRECT, RouteMethod.REDIRECT);
		}
		return result;
	}

	private static class Holder {
		static final SubscriberCommandHelper INSTANCE = new SubscriberCommandHelper();
	}

}
