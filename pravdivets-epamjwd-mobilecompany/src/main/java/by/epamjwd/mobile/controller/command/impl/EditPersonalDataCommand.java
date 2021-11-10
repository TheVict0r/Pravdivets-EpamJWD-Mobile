package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;

public class EditPersonalDataCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.removeAttribute(AttributeName.ACTIVATE_EDIT);

		
		User user = (User)session.getAttribute(AttributeName.SUBSCRIBER_USER);
		
		
		return new RouteHelper(PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
		
		
	}

}
