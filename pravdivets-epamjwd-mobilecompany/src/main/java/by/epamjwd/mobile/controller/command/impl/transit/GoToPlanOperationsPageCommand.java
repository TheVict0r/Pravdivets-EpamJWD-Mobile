package by.epamjwd.mobile.controller.command.impl.transit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class GoToPlanOperationsPageCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(GoToPlanOperationsPageCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		RouteHelper result = RouteHelper.ERROR;

		PlanService planService = ServiceProvider.getInstance().getPlanService();
		try {
			List<Plan> allPlans = planService.findAllPlans();
			request.setAttribute(AttributeName.ALL_PLANS, allPlans);
			result = new RouteHelper(PagePath.PLAN_OPERATIONS, RouteMethod.FORWARD);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain all tariff plans list. ", e);
			result = RouteHelper.ERROR_500;
		}

		return result;
	}
}
