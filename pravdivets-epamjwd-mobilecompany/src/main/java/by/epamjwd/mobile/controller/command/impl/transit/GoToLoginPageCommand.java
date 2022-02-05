package by.epamjwd.mobile.controller.command.impl.transit;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.SessionCleaner;
import by.epamjwd.mobile.controller.repository.PagePath;


public class GoToLoginPageCommand implements Command {

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		SessionCleaner.getInstance().removeUnusedAttributes(request);
		return new RouteHelper(PagePath.LOGIN, RouteMethod.FORWARD);
	}

}
