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

public class SwitchLocaleCommand implements Command{

	private final static String SERVLET_NAME = "/controller";
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		
		String sessionLocale;
		String headerReferer;
		String lastCommand;
		String path;
		
		HttpSession session = request.getSession();
		
		sessionLocale = request.getParameter(ParameterName.SESSION_LOCALE);
		
		if(sessionLocale == null || sessionLocale.isBlank()) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return RouteHelper.ERROR;
		}
		
		session.setAttribute(AttributeName.SESSION_LOCALE, sessionLocale);
		
		headerReferer = request.getHeader(ParameterName.HEADER_REFER);
		lastCommand = headerReferer.substring(headerReferer.indexOf('?') );
		path = SERVLET_NAME + lastCommand;
		
		return new RouteHelper(path, RouteMethod.REDIRECT);
	}

}
