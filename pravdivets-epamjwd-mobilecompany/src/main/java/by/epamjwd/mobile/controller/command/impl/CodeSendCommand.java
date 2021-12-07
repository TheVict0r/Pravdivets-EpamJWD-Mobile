package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class CodeSendCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(CodeSendCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String phone = (String)session.getAttribute(AttributeName.PHONE);
		
		UserService userService = ServiceProvider.getInstance().getUserService();

		try {
			int code = userService.sendCodeByMail(phone);
			session.setAttribute(AttributeName.CODE, code);
		} catch (ServiceException e) {
			LOGGER.error("Error while sending an authentication code to " + phone + e);
			return RouteHelper.ERROR_500;
		}
		
		return new RouteHelper(PagePath.CODE_RETURN_REDIRECT, RouteMethod.REDIRECT);
	}

}
