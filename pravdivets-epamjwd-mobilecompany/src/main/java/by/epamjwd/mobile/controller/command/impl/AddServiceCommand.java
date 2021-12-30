package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.ParameterName;

public class AddServiceCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String name        = request.getParameter(ParameterName.NAME);
		String description = request.getParameter(ParameterName.DESCRIPTION);
		int tariff         = NumericParser.parseIntValue(request.getParameter(ParameterName.TARIFF));

		System.out.println(name);
		System.out.println(description);
		System.out.println(tariff);
		
		return null;
	}

}
