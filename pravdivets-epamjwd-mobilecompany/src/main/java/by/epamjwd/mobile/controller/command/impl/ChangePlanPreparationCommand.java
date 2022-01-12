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
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ChangePlanPreparationCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ChangePlanPreparationCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute(AttributeName.ACTIVATE_EDIT, AttributeValue.PLAN);

		Subscriber subscriber = (Subscriber) session.getAttribute(AttributeName.SUBSCRIBER);
		
		if( subscriber == null ) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return RouteHelper.ERROR_500;
		}
		
		long currentPlanId = subscriber.getPlanId();

		PlanService planService = ServiceProvider.getInstance().getPlanService();

		Plan currentPlan;
		try {
			currentPlan = planService.findPlanByID(currentPlanId).get();
		} catch (ServiceException e1) {
			LOGGER.error("Unable to get current tariff plan by ID" + currentPlanId, e1);
			return RouteHelper.ERROR_500;
		}

		try {
			List<Plan> allPlans = planService.findAllPlans();
			allPlans.remove(currentPlan);
			session.setAttribute(AttributeName.ALL_PLANS, allPlans);
		} catch (ServiceException e) {
			LOGGER.error("Unable to get data for tariff plans", e);
			return RouteHelper.ERROR_500;
		}

		return new RouteHelper(PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
	}

}
