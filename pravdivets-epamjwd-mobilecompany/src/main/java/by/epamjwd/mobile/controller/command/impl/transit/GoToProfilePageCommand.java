package by.epamjwd.mobile.controller.command.impl.transit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.helpers.SubscriberCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;

public class GoToProfilePageCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(GoToProfilePageCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		SubscriberCommandHelper.getInstance().clearSessionFromSubscriberAttributes(session);
		
		Role role = (Role) session.getAttribute(AttributeName.ROLE);
		
		if(role == null) {
			return RouteHelper.ERROR_500;
		}
		
		RouteHelper result;
		switch (role) {
		case ADMIN:
			result = new RouteHelper(PagePath.ADMIN, RouteMethod.FORWARD);
			break;
		case CONSULTANT:
			result = new RouteHelper(PagePath.SUBSCRIBER_OPERATIONS, RouteMethod.FORWARD);
			break;
		case SUBSCRIBER:
			result = new RouteHelper(PagePath.SUBSCRIBER_LIST_REDIRECT, RouteMethod.REDIRECT);
			break;
		default:
			LOGGER.error("Error while getting RouteHelper by Role - " + role);
			result = RouteHelper.ERROR_404;
		}
		return result;
	}

}
