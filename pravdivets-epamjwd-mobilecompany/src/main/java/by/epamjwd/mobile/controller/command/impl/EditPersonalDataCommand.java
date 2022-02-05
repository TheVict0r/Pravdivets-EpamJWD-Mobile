package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.helpers.UserCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class EditPersonalDataCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(EditPersonalDataCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		UserService userService = ServiceProvider.getInstance().getUserService();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(AttributeName.SUBSCRIBER_USER);
		
		String newFirstName = request.getParameter(ParameterName.SUBSCRIBER_FIRST_NAME);
		String newMiddleName = request.getParameter(ParameterName.SUBSCRIBER_MIDDLE_NAME);
		String newLastName = request.getParameter(ParameterName.SUBSCRIBER_LAST_NAME);
		String newPassport = request.getParameter(ParameterName.PASSPORT);

		if (newFirstName == null || newFirstName.isBlank() || 
		     newLastName == null || newLastName.isBlank()  ||
			 newPassport == null || newPassport.isBlank()  || user == null ){
					session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
					return new RouteHelper(PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
				}

		try {
			if (( ! newPassport.equals(user.getPassport())) && (userService.isPassportBooked(newPassport))) {
				return UserCommandHelper.provideErrorMessage(session, AttributeValue.BOOKED_PASSPORT);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error while verifying is subscriber's new passport booked " + newPassport, e);
			return RouteHelper.ERROR_500;
		}
	
		session.removeAttribute(AttributeName.ACTIVATE_EDIT);
		
		user.setFirstName(newFirstName);
		user.setMiddleName(newMiddleName);
		user.setLastName(newLastName);
		user.setPassport(newPassport);
		
		return UserCommandHelper.handleUserUpdate(user, request);
	}
	
}
