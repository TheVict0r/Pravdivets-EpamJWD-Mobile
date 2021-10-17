package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;

public class AddSubscriberCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		
		String passport = String.valueOf(session.getAttribute(AttributeName.PASSPORT));
		String phoneNumber = String.valueOf(session.getAttribute(AttributeName.PHONE_NUMBER));
		session.removeAttribute(AttributeName.PASSPORT);
		session.removeAttribute(AttributeName.PHONE_NUMBER);
		session.removeAttribute(AttributeName.PHONE_NUMBER_FORMAT);

		
		
		
		
		RouteHelper result = null;
		
		result = new RouteHelper(PagePath.SUBSCRIBER_BASE, RouteMethod.FORWARD);
		
		return result;
	}

}
