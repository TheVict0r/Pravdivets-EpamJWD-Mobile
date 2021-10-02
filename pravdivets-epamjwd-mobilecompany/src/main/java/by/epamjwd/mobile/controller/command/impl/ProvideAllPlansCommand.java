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

public class ProvideAllPlansCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ProvideAllPlansCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		PlanService planService = provider.getPlanService();
		try {
			List<Plan> allPlans = planService.getAllPlans();
			request.setAttribute(AttributeName.ALL_PLANS, allPlans);
			return new RouteHelper(PagePath.ALL_PLANS, RouteMethod.FORWARD);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain all tariff plans list. ", e);
			return new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
	}

}
