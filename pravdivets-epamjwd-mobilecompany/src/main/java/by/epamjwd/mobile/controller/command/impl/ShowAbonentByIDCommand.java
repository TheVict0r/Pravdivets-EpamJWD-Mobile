package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.AbonentService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneNumberFormatter;

public class ShowAbonentByIDCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(ShowAbonentByIDCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		ServiceProvider provider = ServiceProvider.getInstance();
		AbonentService abonentService = provider.getAbonentService();
		HttpSession session = request.getSession();
		
		String id = String.valueOf(session.getAttribute(AttributeName.ABONENT_ID));
		
		RouteHelper result = null;
		try {
			Abonent abonent = abonentService.findAbonentById(id);
			request.setAttribute(AttributeName.ABONENT, abonent);
			String phoneNumberFormat = PhoneNumberFormatter.formatPhomeNumber(String.valueOf(abonent.getPhoneNumber()));
			request.setAttribute(AttributeName.PHONE_NUMBER, phoneNumberFormat);
			session.setAttribute(AttributeName.ROLE, Role.CUSTOMER);
			result = new RouteHelper(PagePath.ABONENT, RouteMethod.FORWARD);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain data for abonent with ID " + id, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}

}
