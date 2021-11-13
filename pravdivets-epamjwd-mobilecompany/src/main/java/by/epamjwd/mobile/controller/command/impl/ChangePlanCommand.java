package by.epamjwd.mobile.controller.command.impl;

import java.util.Optional;

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
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneFormatter;

public class ChangePlanCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(ChangePlanCommand.class);

	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute(AttributeName.ACTIVATE_EDIT);
		session.removeAttribute(AttributeName.ALL_PLANS);

		long newPlanID = NumericParser.parseLongValue(request.getParameter(ParameterName.PLAN_ID));
		Subscriber subscriber = (Subscriber)session.getAttribute(AttributeName.SUBSCRIBER);
		long subscriberID = subscriber.getId();
		subscriber.setPlanId(newPlanID);
		
		SubscriberService subscriberService = ServiceProvider.getInstance().getSubscriberService();
		try {
			subscriberService.updateSubscriber(subscriber);
		} catch (ServiceException e) {
			LOGGER.error("Error during updating subscriber data", e);
			return RouteHelper.ERROR;
		}
		
		try {
			Optional <Subscriber> updatedSubscriberOptional = subscriberService.findSubscriberById(subscriberID);
			if(updatedSubscriberOptional.isPresent()) {
				Subscriber updatedSubscriber = updatedSubscriberOptional.get();
				session.setAttribute(AttributeName.SUBSCRIBER, updatedSubscriber);
				PlanService planService = ServiceProvider.getInstance().getPlanService();
				Plan newPlanFomDataBase = planService.findPlanByID(updatedSubscriber.getPlanId());
				session.setAttribute(AttributeName.PLAN, newPlanFomDataBase);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error retrieving updated subscriber", e);
			return  RouteHelper.ERROR;
		}		
		return new RouteHelper(PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
	}

}
