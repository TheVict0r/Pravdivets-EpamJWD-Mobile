package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.PagePath;

public class AddSubscriberCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		RouteHelper result = null;
		
		result = new RouteHelper(PagePath.SUBSCRIBER_BASE, RouteMethod.FORWARD);
		
		return result;
	}

}
