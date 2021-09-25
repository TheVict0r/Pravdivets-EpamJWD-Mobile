package by.epamjwd.mobile.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.TariffPlan;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.TariffPlanService;

public class FullPlanCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ServiceProvider provider = ServiceProvider.getInstance();
		TariffPlanService tariffPlanService = provider.getTariffPlanService();
		
		int id = Integer.parseInt(request.getParameter("id"));
		TariffPlan plan = tariffPlanService.getTariffPlanByID(id);
		
		request.setAttribute("plan", plan);
		
	}

}
