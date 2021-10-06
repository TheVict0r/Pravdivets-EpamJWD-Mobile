package by.epamjwd.mobile.controller.command.impl;

import java.util.NoSuchElementException;

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

public class ConsultantCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(ConsultantCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String email = String.valueOf(request.getSession().getAttribute(AttributeName.EMAIL));
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		RouteHelper result = null;
		try {
			User consultant = userService.getUserByEmail(email).get();
			request.setAttribute(AttributeName.CONSULTANT, consultant);
			result = new RouteHelper(PagePath.CONSULTANT, RouteMethod.FORWARD);
		} catch (ServiceException | NoSuchElementException e) {
			LOGGER.error("Unable to obtain consultant user data. ", e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}

}
