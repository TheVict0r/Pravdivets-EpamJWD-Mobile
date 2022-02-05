package by.epamjwd.mobile.controller.command;


import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.controller.repository.AttributeName;

public class SessionCleaner {

	private final Set<String> globalAttributes = new HashSet<>();
	
	private SessionCleaner() {
		globalAttributes.add(AttributeName.USER_ID);
		globalAttributes.add(AttributeName.FIRST_NAME_HEADER);
		globalAttributes.add(AttributeName.LAST_NAME_HEADER);
		globalAttributes.add(AttributeName.ROLE);
		globalAttributes.add(AttributeName.SESSION_LOCALE);
	}
	
	public static SessionCleaner getInstance() {
		return Holder.INSTANCE;
	}
	
	public void removeUnusedAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> allAttributes = session.getAttributeNames();
		
		while (allAttributes.hasMoreElements()) {
			String attribute = allAttributes.nextElement().toString();
			if ( ! globalAttributes.contains(attribute)) {
				session.removeAttribute(attribute);
			}
		}
	}


	public void addGlobalAttribute(String attribute) {
		globalAttributes.add(attribute);
	}
	
	public void removeGlobalAttribute(String attribute) {
		globalAttributes.remove(attribute);
	}
	
	private static final class Holder {
		final static SessionCleaner INSTANCE = new SessionCleaner();
	}
}

