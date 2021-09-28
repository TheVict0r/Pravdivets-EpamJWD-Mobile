package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.TariffPlan;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.repository.AttributeName;
import by.epamjwd.mobile.controller.command.repository.PagePath;
import by.epamjwd.mobile.controller.command.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.PlanService;

public class FullPlanCommand implements Command {

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		PlanService tariffPlanService = provider.getPlanService();
		
		int id = Integer.parseInt(request.getParameter(ParameterName.ID));
		TariffPlan plan = tariffPlanService.getTariffPlanByID(id);
		
		request.setAttribute(AttributeName.PLAN, plan);
		
		RouteHelper result = new RouteHelper(PagePath.PLAN, RouteMethod.FORWARD);
		return result;
	}

}
