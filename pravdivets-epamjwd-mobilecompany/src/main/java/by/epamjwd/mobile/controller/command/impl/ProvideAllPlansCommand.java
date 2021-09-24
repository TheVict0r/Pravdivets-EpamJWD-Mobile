package by.epamjwd.mobile.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.TariffPlan;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.TariffPlanService;

public class ProvideAllPlansCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ServiceProvider provider = ServiceProvider.getInstance();
		TariffPlanService tariffPlanService = provider.getTariffPlanService();
		List<TariffPlan> allTariffPlans =  tariffPlanService.getAllTariffPlans();
		request.setAttribute("all_tariffs", allTariffPlans);
	}

}
