package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.controller.repository.PagePath;

public class AddArticleCommand implements Command {

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		String title = request.getParameter(ParameterName.TITLE);
		String lead  = request.getParameter(ParameterName.LEAD);
		String text  = request.getParameter(ParameterName.TEXT);
		HttpSession session = request.getSession();
		
		if(title == null || title.isBlank() || lead == null 
				|| lead.isBlank() || text == null || text.isBlank()) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.ADD_ARTICLE_REDIRECT, RouteMethod.REDIRECT);
		}
		
		
		System.out.println(title);
		System.out.println(lead);
		System.out.println(text);
		
		
		return null;
	}

}
