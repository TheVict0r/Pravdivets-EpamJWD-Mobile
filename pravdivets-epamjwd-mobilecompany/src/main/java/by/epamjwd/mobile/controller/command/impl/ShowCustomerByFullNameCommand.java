package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.AbonentService;
import by.epamjwd.mobile.service.ServiceProvider;

public class ShowCustomerByFullNameCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(ShowCustomerByFullNameCommand.class);


	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		AbonentService abonentService = provider.getAbonentService();

		String firstName = request.getParameter(ParameterName.FIRST_NAME);
		String middleName = request.getParameter(ParameterName.MIDDLE_NAME);
		String lastName = request.getParameter(ParameterName.LAST_NAME);
		
		//abonentService.findAbonentByFullName(lastName)
		
		System.out.println(firstName + " " + middleName + " " + lastName);
		RouteHelper result = null;
		
		result = new RouteHelper(PagePath.CONSULTANT, RouteMethod.FORWARD);
		
		return result;
	}

}
