package by.epamjwd.mobile.controller.command.impl;

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
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.PlanService;

public class FullPlanCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(FullPlanCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		PlanService tariffPlanService = provider.getPlanService();

		int id = Integer.parseInt(request.getParameter(ParameterName.ID));
		try {
			Plan plan = tariffPlanService.getTariffPlanByID(id).orElse(new Plan());
			//CHECK OPTIONAL
			request.setAttribute(AttributeName.PLAN, plan);
			return new RouteHelper(PagePath.PLAN, RouteMethod.FORWARD);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain full tariff plan data. ", e);
			return new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}

	}

}
