package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;

public class AddPlanCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String name        = request.getParameter(ParameterName.NAME);
		String description = request.getParameter(ParameterName.DESCRIPTION);
		int regularPayment = NumericParser.parseIntValue(request.getParameter(ParameterName.REGULAR_PAYMENT));
		int upfrontPayment = NumericParser.parseIntValue(request.getParameter(ParameterName.UPFRONT_PAYMENT));
		int withinNetwork  = NumericParser.parseIntValue(request.getParameter(ParameterName.WITHIN_NETWORK));
		int otherNetworks  = NumericParser.parseIntValue(request.getParameter(ParameterName.OTHER_NETWORKS));
		int abroad         = NumericParser.parseIntValue(request.getParameter(ParameterName.ABROAD));
		int videocall      = NumericParser.parseIntValue(request.getParameter(ParameterName.VIDEOCALL));
		int sms            = NumericParser.parseIntValue(request.getParameter(ParameterName.SMS));
		int mms            = NumericParser.parseIntValue(request.getParameter(ParameterName.MMS));
		int internet       = NumericParser.parseIntValue(request.getParameter(ParameterName.INTERNET));
		
		HttpSession session = request.getSession();
		
		if(        name == null || name.isBlank()         || 
			description == null || description.isBlank()  ||
			regularPayment == NumericParser.INVALID_VALUE ||
			upfrontPayment == NumericParser.INVALID_VALUE ||
			 withinNetwork == NumericParser.INVALID_VALUE ||
			 otherNetworks == NumericParser.INVALID_VALUE ||
					abroad == NumericParser.INVALID_VALUE ||
				 videocall == NumericParser.INVALID_VALUE ||
					   sms == NumericParser.INVALID_VALUE ||
					   mms == NumericParser.INVALID_VALUE ||
		    	  internet == NumericParser.INVALID_VALUE ){
				session.setAttribute(AttributeName.ERROR, AttributeValue.WRONG_DATA);
				return new RouteHelper(PagePath.ADD_PLAN_REDIRECT, RouteMethod.REDIRECT);
			}

		
		
		
		return null;
	}

}
