package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;

public class ShowBestPlanCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Plan bestPlan = (Plan)session.getAttribute(AttributeName.CALCULATOR_BEST_PLAN);
		session.removeAttribute(AttributeName.CALCULATOR_BEST_PLAN);
		request.setAttribute(AttributeName.CALCULATOR_BEST_PLAN, bestPlan);
		
		RouteHelper result = null;
		result = new RouteHelper(PagePath.CALCULATOR, RouteMethod.FORWARD);
		
		return result;
	}

}
