package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.AbonentCommandHelper;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.AbonentService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowAbonentListByFullNameCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(ShowAbonentListByFullNameCommand.class);


	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		AbonentService abonentService = provider.getAbonentService();

		String firstName = request.getParameter(ParameterName.FIRST_NAME);
		String middleName = request.getParameter(ParameterName.MIDDLE_NAME);
		String lastName = request.getParameter(ParameterName.LAST_NAME);
		List<Abonent> abonentList = null;
		RouteHelper result = null;
		
		try {
			abonentList = abonentService.findAbonentListByFullName(firstName, middleName, lastName);
			result = AbonentCommandHelper.getInstance().handleAbonentsList(request, abonentList);
		
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain abonent user data abonent " + firstName + " " + middleName + " " + lastName, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}

}
