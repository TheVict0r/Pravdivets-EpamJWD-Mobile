package by.epamjwd.mobile.controller.command.impl.transit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.PagePath;

public class GoToCodeReturnPageCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		return new RouteHelper(PagePath.CODE_RETURN, RouteMethod.FORWARD);
	}

}
