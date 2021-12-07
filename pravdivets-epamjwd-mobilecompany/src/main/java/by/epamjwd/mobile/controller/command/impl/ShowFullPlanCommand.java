package by.epamjwd.mobile.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.PlanService;

public class ShowFullPlanCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ShowFullPlanCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		PlanService tariffPlanService = provider.getPlanService();
		RouteHelper result = null;
		long id = NumericParser.parseLongValue(request.getParameter(ParameterName.ID));
		if(id == NumericParser.INVALID_VALUE) {
			return RouteHelper.ERROR_404;
		}
		
		try {
			Optional<Plan> planOptional = tariffPlanService.findPlanByID(id);
			if(planOptional.isPresent()) {
				Plan plan = planOptional.get();
				request.setAttribute(AttributeName.PLAN, plan);
				result = new RouteHelper(PagePath.PLAN, RouteMethod.FORWARD);
			} else {
				result = RouteHelper.ERROR_404;
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain full tariff plan data. ", e);
			result = RouteHelper.ERROR_500;
		}
		return result;
	}

}
