package by.epamjwd.mobile.controller.command.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;

public class ShowAbonentForStuffCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ShowAbonentForStuffCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		int phoneNumber = Integer.parseInt(request.getParameter(ParameterName.PHONE_NUMBER));
		request.getSession().setAttribute(AttributeName.PHONE_NUMBER, phoneNumber);
		RouteHelper result = new RouteHelper(PagePath.ABONENT_FOR_STUFF_REDIRECT, RouteMethod.REDIRECT);
		return result;
	}
}