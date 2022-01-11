package by.epamjwd.mobile.controller.command.helpers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.ServiceService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ServiceCommandHelper {
	private ServiceCommandHelper() {
	}

	public static ServiceCommandHelper getInstance() {
		return Holder.INSTANCE;
	}
	
	public RouteHelper handleServiceByID(HttpSession session, long serviceID, String pagePath,
			Logger logger) {
		ServiceService serviceService = ServiceProvider.getInstance().getServiceService();
		RouteHelper result = RouteHelper.ERROR;
		try {
			Optional<Service> serviceOptional = serviceService.findServiceByID(serviceID);
			if (serviceOptional.isPresent()) {
				Service service = serviceOptional.get();
				session.setAttribute(AttributeName.SERVICE, service);
				result = new RouteHelper(pagePath, RouteMethod.REDIRECT);
			} else {
				result = RouteHelper.ERROR_404;
			}
		} catch (ServiceException e) {
			logger.error("Unable to obtain full service data for ID " + serviceID, e);
			result = RouteHelper.ERROR_500;
		}
		return result;

	}


	
	
	
	private static class Holder{
		private final static ServiceCommandHelper INSTANCE = new ServiceCommandHelper();
	}
	
}
