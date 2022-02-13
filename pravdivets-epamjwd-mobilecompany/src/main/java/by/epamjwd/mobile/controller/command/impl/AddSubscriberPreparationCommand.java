package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

public class AddSubscriberPreparationCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(AddSubscriberPreparationCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute(AttributeName.SUBSCRIBER_DEBTOR);
		session.removeAttribute(AttributeName.SUBSCRIBER_LIST);

		String passport = request.getParameter(ParameterName.PASSPORT);

		if (passport == null || passport.isBlank()) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.SUBSCRIBER_OPERATIONS_REDIRECT, RouteMethod.REDIRECT);
		}

		RouteHelper result = RouteHelper.ERROR;

		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = serviceProvider.getUserService();
		SubscriberService subscriberService = serviceProvider.getSubscriberService();
		PlanService planService = serviceProvider.getPlanService();
		try {
			List<Plan> allPlans = planService.findAllPlans();
			session.setAttribute(AttributeName.PASSPORT, passport);
			session.setAttribute(AttributeName.ALL_PLANS, allPlans);

			if (subscriberService.isDebtor(passport)) {
				List<Subscriber> subscribersDebtors = subscriberService.findSubscribersDebtors(passport);
				session.setAttribute(AttributeName.SUBSCRIBER_DEBTOR, AttributeValue.DEBTOR);
				session.setAttribute(AttributeName.SUBSCRIBER_LIST, subscribersDebtors);
				result = new RouteHelper(PagePath.SUBSCRIBER_OPERATIONS_WITH_ATTRIBUTES_REDIRECT, RouteMethod.REDIRECT);
			} else if (subscriberService.isNewSubscriberUser(passport)) {
				session.setAttribute(AttributeName.SUBSCRIBER_USER_FLAG, AttributeValue.NEW);
				result = new RouteHelper(PagePath.ADD_SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
			} else { // so this subscriber already has another phone number as well as User data and
						// is not debtor
				User currentUser = userService.findUserByPassport(passport).get();
				session.setAttribute(AttributeName.SUBSCRIBER_USER, currentUser);
				result = new RouteHelper(PagePath.ADD_SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to check the passport for new subscriber - " + passport, e);
			result = RouteHelper.ERROR_500;
		}
		return result;
	}

}