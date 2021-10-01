package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.ServiceService;

public class ProvideAllServicesCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		ServiceService serviceService = provider.getServiceService();
		
		List<Service> serviceList = serviceService.getAllServices();

		request.setAttribute(AttributeName.ALL_SERVICES, serviceList);
	
		RouteHelper result = new RouteHelper(PagePath.ALL_SERVICES, RouteMethod.FORWARD);
		return result;
	}

}
