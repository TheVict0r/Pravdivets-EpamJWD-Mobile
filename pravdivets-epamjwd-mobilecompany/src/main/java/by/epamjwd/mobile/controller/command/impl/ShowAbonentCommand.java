package by.epamjwd.mobile.controller.command.impl;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.AbonentService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowAbonentCommand implements Command {
	
	private final static Logger LOGGER = LogManager.getLogger(RouteHelper.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		ServiceProvider provider = ServiceProvider.getInstance();
		AbonentService abonentService = provider.getAbonentService();
		HttpSession session = request.getSession();

		Integer phoneNumber;
		
		
		phoneNumber = Integer.parseInt(request.getParameter(ParameterName.PHONE_NUMBER));
		System.out.println("request" + phoneNumber);
			
//		phoneNumber = Integer.parseInt((String.valueOf(session.getAttribute(AttributeName.PHONE_NUMBER))));
//		System.out.println("session" + phoneNumber);
			
		RouteHelper result = null;
		try {
			//Abonent abonent = abonentService.findAbonentListByUserId(id).get();
			Abonent abonent = abonentService.findAbonentByPhoneNumber(phoneNumber).get();
			request.setAttribute(AttributeName.ABONENT, abonent);
			session.setAttribute(AttributeName.PHONE_NUMBER, phoneNumber);
			result = new RouteHelper(PagePath.ABONENT, RouteMethod.FORWARD);
		} catch (ServiceException| NoSuchElementException e) {
			LOGGER.error("Unable to obtain data for phone number " + phoneNumber, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}

}
