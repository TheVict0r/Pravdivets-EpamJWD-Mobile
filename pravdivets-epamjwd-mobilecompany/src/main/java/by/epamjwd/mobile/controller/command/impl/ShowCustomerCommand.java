package by.epamjwd.mobile.controller.command.impl;

import java.util.List;
import java.util.Map;
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
import by.epamjwd.mobile.service.AbonentService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneNumberFormatter;

public class ShowCustomerCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ShowCustomerCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String id = String.valueOf(request.getSession().getAttribute(AttributeName.USER_ID));
		ServiceProvider provider = ServiceProvider.getInstance();
		AbonentService abonentService = provider.getAbonentService();
		PhoneNumberFormatter numberFormatter = new PhoneNumberFormatter();
		HttpSession session = request.getSession();

		RouteHelper result = null;
		try {
			List<Abonent> abonentsList = abonentService.findAbonentListByUserId(id);
			if (abonentsList.size() == 1) {
				Abonent abonent = abonentsList.get(0);
				request.setAttribute(AttributeName.ABONENT, abonent);
				String phoneNumber = numberFormatter.formatPhomeNumber(abonent.getPhoneNumber());
				request.setAttribute(AttributeName.PHONE_NUMBER, phoneNumber);
				result = new RouteHelper(PagePath.ABONENT, RouteMethod.FORWARD);
			} else {
				request.setAttribute(AttributeName.ABONENT_LIST, abonentsList);
//				Map<String, String> phoneNumbersMap = numberFormatter.provideFormattedPhoneNumbersMap(abonentsList);
//				request.setAttribute(AttributeName.PHONE_NUMBERS_MAP, phoneNumbersMap);
				result = new RouteHelper(PagePath.CUSTOMER, RouteMethod.FORWARD);
			}
		} catch (ServiceException | NoSuchElementException e) {
			LOGGER.error("Unable to obtain abonent user data for ID " + id, e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}

}
