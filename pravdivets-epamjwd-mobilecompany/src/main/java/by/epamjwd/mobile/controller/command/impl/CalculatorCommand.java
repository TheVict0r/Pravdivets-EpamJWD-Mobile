package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.PagePath;

public class CalculatorCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		int question1answer = Integer.parseInt(request.getParameter("question1"));
		
		request.setAttribute("question1", question1answer);
		
		RouteHelper result = null;
		result = new RouteHelper(PagePath.CALCULATOR_RESULT, RouteMethod.FORWARD);
		return result;
	}

}
