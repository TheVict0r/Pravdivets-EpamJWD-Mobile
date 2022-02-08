package by.epamjwd.mobile.controller.command.impl.transit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.ServiceService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class GoToSubscriberServicesPageCommand implements Command {
	private final static Logger LOGGER = LogManager.getLogger(GoToSubscriberServicesPageCommand.class);

	/*
	 * Sorry, this method is not ready yet :(
	 */
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceService serviceService = ServiceProvider.getInstance().getServiceService();
		try {
			List<Service> serviceList = serviceService.findAllServices();
			
			Map<Service, Boolean> serviceMap = new HashMap<>();
			for(Service service : serviceList) {
				serviceMap.put(service, true); //"true" is just temporary
			}
			
			request.getSession().setAttribute(AttributeName.ALL_SERVICES, serviceMap);
			
			return new RouteHelper(PagePath.SUBSCRIBER_SERVICES, RouteMethod.FORWARD);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain service list. ", e);
			return RouteHelper.ERROR_500;
		}

	}

}
