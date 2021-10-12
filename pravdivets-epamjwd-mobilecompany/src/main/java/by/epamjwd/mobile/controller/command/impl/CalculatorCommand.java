package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class CalculatorCommand implements Command{
	
	private final static Logger LOGGER = LogManager.getLogger(CalculatorCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		int withinNetwork = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_WITHIN_NETWORK));
		int otherNetworks = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_OTHER_NETWORKS));
		int abroad        = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_ABROAD));
		int videocall     = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_VIDEOCALL));
		int sms           = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_SMS));
		int mms           = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_MMS));
		int internet      = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_INTERNET));		
		
		ServiceProvider provider = ServiceProvider.getInstance();
		PlanService planService = provider.getPlanService();
		Plan bestPlan = null;
		HttpSession session = request.getSession();
		RouteHelper result = null;

		try {
			bestPlan = planService.suggestPlan(withinNetwork, otherNetworks, abroad, videocall, sms, mms, internet);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain best tariff plan", e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		session.setAttribute(AttributeName.CALCULATOR_BEST_PLAN, bestPlan);
		result = new RouteHelper(PagePath.CALCULATOR_RESULT_REDIRECT, RouteMethod.REDIRECT);
		return result;
	}

}
