package by.epamjwd.mobile.controller.command.impl.transit;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.ConsultantCommandHelper;
import by.epamjwd.mobile.controller.command.SubscriberCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;

public class GoToAdminPageCommand implements Command {

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		SubscriberCommandHelper.getInstance().clearSessionFromSubscriberAttributes(session);
		ConsultantCommandHelper.getInstance().clearSessionFromConsultantAttributes(session);
		return new RouteHelper(PagePath.ADMIN, RouteMethod.FORWARD);
	}
}
