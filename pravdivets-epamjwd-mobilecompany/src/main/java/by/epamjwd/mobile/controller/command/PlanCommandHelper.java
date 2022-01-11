package by.epamjwd.mobile.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.ServiceService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class PlanCommandHelper {

	private PlanCommandHelper() {
	}

	public static PlanCommandHelper getInstance() {
		return Holder.INSTANCE;
	}

	public RouteHelper handlePlanByID(HttpSession session, long planID, String pagePath, Logger LOGGER) {
		RouteHelper result = RouteHelper.ERROR;
		PlanService tariffPlanService = ServiceProvider.getInstance().getPlanService();
		try {
			Optional<Plan> planOptional = tariffPlanService.findPlanByID(planID);
			if (planOptional.isPresent()) {
				Plan plan = planOptional.get();
				session.setAttribute(AttributeName.PLAN, plan);
				result = new RouteHelper(pagePath, RouteMethod.REDIRECT);
			} else {
				result = RouteHelper.ERROR_404;
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain full tariff plan data. ", e);
			result = RouteHelper.ERROR_500;
		}
		return result;
	}

	private static class Holder {
		static final PlanCommandHelper INSTANCE = new PlanCommandHelper();
	}

}
