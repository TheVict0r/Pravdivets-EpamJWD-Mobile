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
	
	public static ConsultantCommandHelper getInstance() {
		return Holder.INSTANCE;
	}

	public RouteHelper handleConsultantOptional(Optional<User> consultantOptional, HttpSession session, String attributeName, String attributeValue) {
		RouteHelper result = RouteHelper.ERROR;
		if(consultantOptional.isPresent() && (consultantOptional.get().getRole() == Role.CONSULTANT)) {
			long consultantID = consultantOptional.get().getId();
			session.setAttribute(AttributeName.CONSULTANT_ID, consultantID);
			result = new RouteHelper(PagePath.CONSULTANT_REDIRECT, RouteMethod.REDIRECT);
		} else {
			session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR);
			session.setAttribute(attributeName, attributeValue);
			result = new RouteHelper(PagePath.CONSULTANT_OPERATIONS_REDIRECT, RouteMethod.REDIRECT);
		}
		return result;
	}

	public void clearSessionFromConsultantAttributes(HttpSession session) {
		session.removeAttribute(AttributeName.CONSULTANT);
		session.removeAttribute(AttributeName.CONSULTANT_ID);
		session.removeAttribute(AttributeName.ERROR);
		session.removeAttribute(AttributeName.CHANGE_PASSWORD);
		session.removeAttribute(AttributeName.MODE);
		session.removeAttribute(AttributeName.EMAIL);
		session.removeAttribute(AttributeName.PASSPORT);
	}

	private static class Holder {
		static final ConsultantCommandHelper INSTANCE = new ConsultantCommandHelper();
	}
	
}
