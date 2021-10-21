package by.epamjwd.mobile.controller.command;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneNumberFormatter;

public class SubscriberCommandHelper {

	private SubscriberCommandHelper() {

	}

	public static SubscriberCommandHelper getInstance() {
		return Holder.INSTANCE;
	}

	
	public RouteHelper handleSubscribersList(HttpServletRequest request, List<Subscriber> subscribersList) throws ServiceException {
		RouteHelper result = null;
		
			if (subscribersList.size() == 1) {
				Subscriber subscriber = subscribersList.get(0);
				request.setAttribute(AttributeName.SUBSCRIBER, subscriber);
				String phoneNumberFormat = PhoneNumberFormatter.formatPhomeNumber(String.valueOf(subscriber.getPhoneNumber()));
				request.setAttribute(AttributeName.PHONE_NUMBER_FORMAT, phoneNumberFormat);
				result = new RouteHelper(PagePath.SUBSCRIBER, RouteMethod.FORWARD);
			} else {
				request.setAttribute(AttributeName.SUBSCRIBER_LIST, subscribersList);
				result = new RouteHelper(PagePath.CUSTOMER, RouteMethod.FORWARD);
			}
		return result;
		
	}
	
	public RouteHelper handleSubscriber(HttpServletRequest request, Subscriber subscriber) throws ServiceException {
		RouteHelper result = null;
		
		ServiceProvider provider = ServiceProvider.getInstance();
		PlanService planService = provider.getPlanService();

		request.setAttribute(AttributeName.SUBSCRIBER, subscriber);
		String phoneNumberFormat = PhoneNumberFormatter.formatPhomeNumber(String.valueOf(subscriber.getPhoneNumber()));
		request.setAttribute(AttributeName.PHONE_NUMBER_FORMAT, phoneNumberFormat);
		Plan plan = planService.findPlanByID(subscriber.getPlanId());
		request.setAttribute(AttributeName.PLAN, plan);
		result = new RouteHelper(PagePath.SUBSCRIBER, RouteMethod.FORWARD);

		return result;
	}
	
	
	private static class Holder {
		static final SubscriberCommandHelper INSTANCE = new SubscriberCommandHelper();
	}

}
