package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowAdminCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ShowAdminCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String id = String.valueOf(request.getSession().getAttribute(AttributeName.USER_ID));
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		RouteHelper result = null;
		try {
			User admin = userService.findUserById(id);
			request.setAttribute(AttributeName.ADMIN, admin);
			result = new RouteHelper(PagePath.ADMIN, RouteMethod.FORWARD);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain admin user data for ID " + id, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}
}
