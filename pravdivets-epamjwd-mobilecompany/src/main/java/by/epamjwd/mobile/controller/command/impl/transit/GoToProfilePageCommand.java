package by.epamjwd.mobile.controller.command.impl.transit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.SessionCleaner;
import by.epamjwd.mobile.controller.command.UserRolePathProvider;
import by.epamjwd.mobile.controller.repository.AttributeName;

public class GoToProfilePageCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(GoToProfilePageCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		SessionCleaner.getInstance().removeUnusedAttributes(request);
		HttpSession session = request.getSession();
		Role role = (Role) session.getAttribute(AttributeName.ROLE);
		
		if(role == null) {
			LOGGER.error("Error while getting Role data ");
			return RouteHelper.ERROR_500;
		}
		
		return UserRolePathProvider.getInstance().providePath(role);
		
	}

}
