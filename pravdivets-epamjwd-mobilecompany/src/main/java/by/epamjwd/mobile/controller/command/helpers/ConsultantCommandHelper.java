package by.epamjwd.mobile.controller.command.helpers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;

public class ConsultantCommandHelper {

	private ConsultantCommandHelper() {
	}
	
	/**
	 * Retrieves consultant user from Optional variable and sets it to session for further use.
	 * 
	 * @param consultantOptional - optional variable with presumably consultant user in it 
	 * 
	 * @param session - http-session
	 * 
	 * @param attributeName - the name of attribute that was used for retrieving 
	 * 		{@code consultantOptional} parameter. Used for repeated request for 
	 * 		data correction in the case if Optional variable is empty
	 * 
	 * @param attributeValue - the value of attribute that was used for retrieving 
	 * 		{@code consultantOptional} parameter. Used for repeated request for 
	 * 		data correction in the case if Optional variable is empty
	 * 
	 * @return - RouteHelper containing path to page and route method
	 */
	public static RouteHelper handleConsultantOptional(Optional<User> consultantOptional, HttpSession session, String attributeName, String attributeValue) {
		RouteHelper result = RouteHelper.ERROR;
		if(consultantOptional.isPresent() && (consultantOptional.get().getRole() == Role.CONSULTANT)) {
			User consultant = consultantOptional.get();
			session.setAttribute(AttributeName.CONSULTANT, consultant);
			result = new RouteHelper(PagePath.CONSULTANT_REDIRECT, RouteMethod.REDIRECT);
		} else {
			session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR);
			session.setAttribute(attributeName, attributeValue);
			result = new RouteHelper(PagePath.CONSULTANT_OPERATIONS_REDIRECT, RouteMethod.REDIRECT);
		}
		return result;
	}

	/**
	 * Cleans session from Consultant's attributes no longer needed. 
	 * 
	 * @param session - http-session
	 */
	public static void clearSessionFromConsultantAttributes(HttpSession session) {
		session.removeAttribute(AttributeName.CONSULTANT);
		session.removeAttribute(AttributeName.CONSULTANT_ID);
		session.removeAttribute(AttributeName.ERROR);
		session.removeAttribute(AttributeName.CHANGE_PASSWORD);
		session.removeAttribute(AttributeName.MODE);
		session.removeAttribute(AttributeName.EMAIL);
		session.removeAttribute(AttributeName.PASSPORT);
	}

}
