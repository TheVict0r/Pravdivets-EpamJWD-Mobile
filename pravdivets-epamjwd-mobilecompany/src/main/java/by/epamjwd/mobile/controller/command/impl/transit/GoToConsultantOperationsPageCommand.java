package by.epamjwd.mobile.controller.command.impl.transit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.helpers.ConsultantCommandHelper;
import by.epamjwd.mobile.controller.repository.PagePath;

public class GoToConsultantOperationsPageCommand implements Command {

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ConsultantCommandHelper.clearSessionFromConsultantAttributes(session);
		return new RouteHelper(PagePath.CONSULTANT_OPERATIONS, RouteMethod.FORWARD);	}
}
