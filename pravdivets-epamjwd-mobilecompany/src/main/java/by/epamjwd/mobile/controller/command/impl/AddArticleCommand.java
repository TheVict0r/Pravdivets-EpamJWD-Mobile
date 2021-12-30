package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.ParameterName;

public class AddArticleCommand implements Command {

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		String title = request.getParameter(ParameterName.TITLE);
		String lead = request.getParameter(ParameterName.LEAD);
		String text = request.getParameter(ParameterName.TEXT);
		
		System.out.println(title);
		System.out.println(lead);
		System.out.println(text);
		
		
		return null;
	}

}
