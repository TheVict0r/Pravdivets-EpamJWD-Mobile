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
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class EditConsultantCommand implements Command {
	private final static Logger LOGGER = LogManager.getLogger(AddSubscriberCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		RouteHelper result = RouteHelper.ERROR;
		HttpSession session = request.getSession();
		User consultant = (User)session.getAttribute(AttributeName.CONSULTANT);
		long consultantID = consultant.getId();
		
		String newFirstName  = request.getParameter(ParameterName.CONSULTANT_FIRST_NAME);
		String newMiddleName = request.getParameter(ParameterName.CONSULTANT_MIDDLE_NAME);
		String newLastName   = request.getParameter(ParameterName.CONSULTANT_LAST_NAME);
		String newPassport   = request.getParameter(ParameterName.PASSPORT);
		String newEmail      = request.getParameter(ParameterName.EMAIL);
		
		UserService userService = ServiceProvider.getInstance().getUserService();

		try {
			if((!newEmail.equals(consultant.getEmail())) && (userService.isEmailBooked(newEmail))) {
				return provideErrorMessage(session, AttributeValue.BOOKED_EMAIL);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error while verifying is email booked " + newEmail, e);
			return RouteHelper.ERROR_500;
		}
		
		try {
			if((!newPassport.equals(consultant.getPassport())) && (userService.isPassportBooked(newPassport))) {
				return provideErrorMessage(session, AttributeValue.BOOKED_PASSPORT);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error while verifying is passport booked " + newPassport, e);
			return RouteHelper.ERROR_500;
		}

		consultant.setFirstName(newFirstName);
		consultant.setMiddleName(newMiddleName);
		consultant.setLastName(newLastName);
		consultant.setPassport(newPassport);
		consultant.setEmail(newEmail);
		
		try {
			userService.updateUser(consultant);
		} catch (ServiceException e) {
			LOGGER.error("Error during updating consultant personal data", e);
			return RouteHelper.ERROR_500;
		}
		
		session.removeAttribute(AttributeName.MODE);
		session.setAttribute(AttributeName.CONSULTANT_ID, consultantID);
		return new RouteHelper(PagePath.CONSULTANT_REDIRECT, RouteMethod.REDIRECT);
	}

	private RouteHelper provideErrorMessage(HttpSession session, String attributeValue) {
		session.setAttribute(AttributeName.ERROR, attributeValue);
		return new RouteHelper(PagePath.EDIT_CONSULTANT_REDIRECT, RouteMethod.REDIRECT);
	}

	
	
	
	
}
