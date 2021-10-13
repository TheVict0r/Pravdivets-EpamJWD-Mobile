package by.epamjwd.mobile.controller.command;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneNumberFormatter;

public class AbonentCommandHelper {

	private final static Logger LOGGER = LogManager.getLogger(AbonentCommandHelper.class);

	
	private AbonentCommandHelper() {

	}

	public static AbonentCommandHelper getInstance() {
		return Holder.INSTANCE;
	}

	
	public RouteHelper handleAbonentsList(HttpServletRequest request, List<Abonent> abonentsList) throws ServiceException {
		RouteHelper result = null;
		
			if (abonentsList.size() == 1) {
				Abonent abonent = abonentsList.get(0);
				request.setAttribute(AttributeName.ABONENT, abonent);
				String phoneNumber = PhoneNumberFormatter.formatPhomeNumber(String.valueOf(abonent.getPhoneNumber()));
				request.setAttribute(AttributeName.PHONE_NUMBER, phoneNumber);
				result = new RouteHelper(PagePath.ABONENT, RouteMethod.FORWARD);
			} else {
				request.setAttribute(AttributeName.ABONENT_LIST, abonentsList);
				result = new RouteHelper(PagePath.CUSTOMER, RouteMethod.FORWARD);
			}
		
		
		return result;
		
	}
	
	
	
	private static class Holder {
		static final AbonentCommandHelper INSTANCE = new AbonentCommandHelper();
	}

}
