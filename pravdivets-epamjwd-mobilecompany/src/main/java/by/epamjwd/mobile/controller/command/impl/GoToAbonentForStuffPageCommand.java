package by.epamjwd.mobile.controller.command.impl;

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
import by.epamjwd.mobile.service.AbonentService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneNumberFormatter;

public class GoToAbonentForStuffPageCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ShowAbonentForStuffCommand.class);

	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider provider = ServiceProvider.getInstance();
		AbonentService abonentService = provider.getAbonentService();
		
		HttpSession session = request.getSession();
		int phoneNumber = Integer.parseInt(String.valueOf(session.getAttribute(AttributeName.PHONE_NUMBER)));
		PhoneNumberFormatter numberFormatter = new PhoneNumberFormatter();
		
		//session.removeAttribute(AttributeName.PHONE_NUMBER);
		RouteHelper result = null;
		try {
			Abonent abonent = abonentService.findAbonentByPhoneNumber(phoneNumber);
			request.setAttribute(AttributeName.ABONENT, abonent);
			String phoneNumberFormat = numberFormatter.formatPhomeNumber(abonent.getPhoneNumber());
			request.setAttribute(AttributeName.PHONE_NUMBER, phoneNumberFormat);
			result = new RouteHelper(PagePath.ABONENT_FOR_STUFF, RouteMethod.FORWARD);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain data for phone number " + phoneNumber, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}

}
