package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
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
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneFormatter;
import by.epamjwd.mobile.util.PhoneGenerator;

public class PrepareNewSubscriberCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(PrepareNewSubscriberCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String passport = request.getParameter(ParameterName.PASSPORT);
		RouteHelper result = null;

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = serviceProvider.getUserService();
		SubscriberService subscriberService = serviceProvider.getSubscriberService();
		PlanService planService = serviceProvider.getPlanService();
		try {
			int phone = PhoneGenerator.generatePhone();
			String phoneFormat = PhoneFormatter.formatPhone(phone);
			List<Plan> allPlans = planService.findAllPlans();
			request.setAttribute(AttributeName.PASSPORT, passport);
			request.setAttribute(AttributeName.PHONE, phone);
			request.setAttribute(AttributeName.PHONE_FORMAT, phoneFormat);
			request.setAttribute(AttributeName.ALL_PLANS, allPlans);
			if (subscriberService.isNewUserSubscriber(passport)) {
				request.setAttribute(AttributeName.USER, AttributeValue.NEW);
				result = new RouteHelper(PagePath.ADD_SUBSCRIBER, RouteMethod.FORWARD);
			} else if (subscriberService.isDebtor(passport)) {
				List<Subscriber> debtSubscribers = subscriberService.findSubscriberListWithDebts(passport);
				request.setAttribute(AttributeName.SUBSCRIBER, AttributeValue.DEBTOR);
				request.setAttribute(AttributeName.SUBSCRIBER_LIST, debtSubscribers);
				request.setAttribute(AttributeName.PASSPORT, passport);
				result = new RouteHelper(PagePath.SUBSCRIBER_OPERATIONS, RouteMethod.FORWARD);
			} else {
				User currentUser = userService.findUserByPassport(passport).get();
				//we are pretty sure that currentUser != null because of check in the first "if"
				//subscriberService.isNewUserSubscriber(passport)
				request.setAttribute(AttributeName.USER, currentUser);
				result = new RouteHelper(PagePath.ADD_SUBSCRIBER, RouteMethod.FORWARD);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to check the passport" + passport, e);
			result = new RouteHelper(PagePath.ERROR, RouteMethod.FORWARD);
			// подумай - может ещё куда-то послать? Напр., на ту же страницу но с указанием
			// ошибки
		}
		return result;
	}

}
