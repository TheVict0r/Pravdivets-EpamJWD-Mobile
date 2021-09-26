package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.TariffPlan;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.path.RoutingMethod;
import by.epamjwd.mobile.controller.path.PathRepository;
import by.epamjwd.mobile.controller.path.Routing;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.TariffPlanService;

public class FullPlanCommand implements Command {

	@Override
	public Routing execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		TariffPlanService tariffPlanService = provider.getTariffPlanService();
		
		int id = Integer.parseInt(request.getParameter("id"));
		TariffPlan plan = tariffPlanService.getTariffPlanByID(id);
		
		request.setAttribute("plan", plan);
		
		Routing result = new Routing(PathRepository.PLAN, RoutingMethod.FORWARD);
		return result;
	}

}
