package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.TariffPlan;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.path.Action;
import by.epamjwd.mobile.controller.path.PathRepository;
import by.epamjwd.mobile.controller.path.Routing;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.TariffPlanService;

public class ProvideAllPlansCommand implements Command {

	@Override
	public Routing execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		TariffPlanService tariffPlanService = provider.getTariffPlanService();
		List<TariffPlan> allTariffPlans =  tariffPlanService.getAllTariffPlans();
		
		request.setAttribute("all_plans", allTariffPlans);
		
		Routing result = new Routing(PathRepository.ALL_PLANS, Action.FORWARD);
		return result;
	}

}
