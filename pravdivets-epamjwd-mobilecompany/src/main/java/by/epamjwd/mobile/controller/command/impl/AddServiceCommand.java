package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.command.helpers.ServiceCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.ServiceService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class AddServiceCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(AddServiceCommand.class);
	private final static long EMPTY_ID = 0L;
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ServiceService serviceService = ServiceProvider.getInstance().getServiceService();
		
		String name        = request.getParameter(ParameterName.NAME);
		String description = request.getParameter(ParameterName.DESCRIPTION);
		int tariff         = NumericParser.parseIntValue(request.getParameter(ParameterName.TARIFF));
		long newServiceID = EMPTY_ID;

		if(       name == null || name.isBlank()        || 
		   description == null || description.isBlank() ||
				tariff == NumericParser.INVALID_VALUE  ){
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
					return new RouteHelper(PagePath.ADD_SERVICE_REDIRECT, RouteMethod.REDIRECT);
				}

		try {
			if (serviceService.isServiceExists(name)) {
				session.setAttribute(AttributeName.ERROR, AttributeValue.SERVICE_EXISTS);
				session.setAttribute(AttributeName.NAME, name);
				session.setAttribute(AttributeName.DESCRIPTION, description);
				session.setAttribute(AttributeName.TARIFF, tariff);
				return new RouteHelper(PagePath.ADD_SERVICE_REDIRECT, RouteMethod.REDIRECT);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error while checking is service exist by name - " + name, e);
			return RouteHelper.ERROR_500;
		}
	
		try {
			newServiceID = serviceService.addService(serviceService.buildService(name, tariff, description));
		} catch (ServiceException e) {
			LOGGER.error("Error while adding new service", e);
			return RouteHelper.ERROR_500;
		}

		return ServiceCommandHelper.handleServiceByID(session, newServiceID, PagePath.SERVICE_ADMIN_REDIRECT, RouteMethod.REDIRECT, LOGGER);
	}

}
