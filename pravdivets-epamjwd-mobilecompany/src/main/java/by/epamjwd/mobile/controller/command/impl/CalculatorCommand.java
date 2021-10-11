package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;

public class CalculatorCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		int withinNetwork = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_WITHIN_NETWORK));
		int otherNetworks = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_OTHER_NETWORKS));
		int abroad = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_ABROAD));
		int videocall = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_VIDEOCALL));
		int sms = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_SMS));
		int mms = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_MMS));
		int internet = Integer.parseInt(request.getParameter(ParameterName.CALCULATOR_INTERNET));		
		
		request.setAttribute(AttributeName.CALCULATOR_WITHIN_NETWORK, withinNetwork);
		
		RouteHelper result = null;
		result = new RouteHelper(PagePath.CALCULATOR_RESULT, RouteMethod.FORWARD);
		return result;
	}

}
