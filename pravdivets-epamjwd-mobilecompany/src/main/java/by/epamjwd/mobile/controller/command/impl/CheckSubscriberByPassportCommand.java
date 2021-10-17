package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneNumberFormatter;
import by.epamjwd.mobile.util.PhoneNumberGenerator;

public class CheckSubscriberByPassportCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(CheckSubscriberByPassportCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String passport = request.getParameter(ParameterName.PASSPORT);
		RouteHelper result = null;

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		SubscriberService subscriberService = serviceProvider.getSubscriberService();
		PlanService planService = serviceProvider.getPlanService();
		HttpSession session = request.getSession();

		try {
			int phoneNumber = PhoneNumberGenerator.generatePhoneNumber();
			String phoneNumberFormat = PhoneNumberFormatter.formatPhomeNumber(String.valueOf(phoneNumber));
			List<Plan> allPlans = planService.findAllPlans();
			session.setAttribute(AttributeName.PASSPORT, passport);
			session.setAttribute(AttributeName.PHONE_NUMBER, phoneNumber);
			session.setAttribute(AttributeName.PHONE_NUMBER_FORMAT, phoneNumberFormat);
			request.setAttribute(AttributeName.ALL_PLANS, allPlans);
			if (subscriberService.isNewSubscriber(passport)) {
				request.setAttribute(AttributeName.SUBSCRIBER, AttributeValue.NEW);
				result = new RouteHelper(PagePath.ADD_SUBSCRIBER, RouteMethod.FORWARD);
			} else if (subscriberService.isDebtor(passport)) {
				session.removeAttribute(AttributeName.PASSPORT);
				session.removeAttribute(AttributeName.PHONE_NUMBER);
				session.removeAttribute(AttributeName.PHONE_NUMBER_FORMAT);
				List<Subscriber> debtSubscribers = subscriberService.findSubscribersListWithDebts(passport);
				request.setAttribute(AttributeName.SUBSCRIBER, AttributeValue.DEBTOR);
				request.setAttribute(AttributeName.SUBSCRIBER_LIST, debtSubscribers);
				result = new RouteHelper(PagePath.SUBSCRIBER_BASE, RouteMethod.FORWARD);
			} else {
				Subscriber currentSubscriber = subscriberService.findSubscriberListByPassport(passport).get(0);
				request.setAttribute(AttributeName.SUBSCRIBER, currentSubscriber);
				result = new RouteHelper(PagePath.ADD_SUBSCRIBER, RouteMethod.FORWARD);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to check the passport" + passport, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
			// подумай - может ещё куда-то послать? Напр., на ту же страницу но с указанием
			// ошибки
		}
		return result;
	}

}
