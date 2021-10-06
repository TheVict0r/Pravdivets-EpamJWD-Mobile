package by.epamjwd.mobile.controller.command.impl;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.AbonentService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class CustomerCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(CustomerCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String email = String.valueOf(request.getSession().getAttribute(AttributeName.EMAIL));
		ServiceProvider provider = ServiceProvider.getInstance();
		AbonentService abonentService = provider.getAbonentService();
		RouteHelper result = null;
		try {
			List<Abonent> abonentsList = abonentService.getAbonentsByEmail(email);
			if (abonentsList.size() == 1) {
				Abonent abonent = abonentsList.get(0);
				request.setAttribute(AttributeName.ABONENT, abonent);
				result = new RouteHelper(PagePath.ABONENT, RouteMethod.FORWARD);
			} else {
				request.setAttribute(AttributeName.ABONENT_LIST, abonentsList);
				result = new RouteHelper(PagePath.CUSTOMER, RouteMethod.FORWARD);
			}
		} catch (ServiceException | NoSuchElementException e) {
			LOGGER.error("Unable to obtain abonent user data for e-mail " + email, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}

}
