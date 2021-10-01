package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.TariffPlan;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.PlanService;

public class ProvideAllPlansCommand implements Command {

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		PlanService planService = provider.getPlanService();
		List<TariffPlan> allPlans =  planService.getAllPlans();
		
		request.setAttribute(AttributeName.ALL_PLANS, allPlans);
		
		RouteHelper result = new RouteHelper(PagePath.ALL_PLANS, RouteMethod.FORWARD);
		return result;
	}

}
