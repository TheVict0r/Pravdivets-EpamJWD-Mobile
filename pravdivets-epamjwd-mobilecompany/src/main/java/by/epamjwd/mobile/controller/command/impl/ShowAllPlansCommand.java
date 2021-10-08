package by.epamjwd.mobile.controller.command.impl;

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
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.PlanService;

public class ShowAllPlansCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ShowAllPlansCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		PlanService planService = provider.getPlanService();
		RouteHelper result = null;
		try {
			List<Plan> allPlans = planService.findAllPlans();
			request.setAttribute(AttributeName.ALL_PLANS, allPlans);
			result = new RouteHelper(PagePath.ALL_PLANS, RouteMethod.FORWARD);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain all tariff plans list. ", e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}

}
