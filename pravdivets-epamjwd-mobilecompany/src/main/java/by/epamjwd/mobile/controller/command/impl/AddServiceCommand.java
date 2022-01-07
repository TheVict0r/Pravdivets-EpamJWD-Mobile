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
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.controller.repository.PagePath;

public class AddServiceCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String name        = request.getParameter(ParameterName.NAME);
		String description = request.getParameter(ParameterName.DESCRIPTION);
		int tariff         = NumericParser.parseIntValue(request.getParameter(ParameterName.TARIFF));

		HttpSession session = request.getSession();
		
		if(       name == null || name.isBlank()        || 
		   description == null || description.isBlank() ||
				tariff == NumericParser.INVALID_VALUE  ){
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
					return new RouteHelper(PagePath.ADD_SERVICE_REDIRECT, RouteMethod.REDIRECT);
				}

		
		
		
		System.out.println(name);
		System.out.println(description);
		System.out.println(tariff);
		
		return null;
	}

}
