package by.epamjwd.mobile.controller.command;


import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.controller.repository.AttributeName;

public class SessionCleaner {

	private final Set<String> eternalAttributes = new HashSet<>();
	
	private SessionCleaner() {
		eternalAttributes.add(AttributeName.USER_ID);
		eternalAttributes.add(AttributeName.FIRST_NAME_HEADER);
		eternalAttributes.add(AttributeName.LAST_NAME_HEADER);
		eternalAttributes.add(AttributeName.ROLE);
		eternalAttributes.add(AttributeName.SESSION_LOCALE);
	}
	
	public static SessionCleaner getInstance() {
		return Holder.INSTANCE;
	}
	
	public void removeUnusedAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> allAttributes = session.getAttributeNames();
		
		while (allAttributes.hasMoreElements()) {
			String attribute = allAttributes.nextElement().toString();
			if ( ! eternalAttributes.contains(attribute)) {
				session.removeAttribute(attribute);
			}
		}
	}


	public void addEthernalAttribute(String attribute) {
		eternalAttributes.add(attribute);
	}
	
	public void removeEthernalAttribute(String attribute) {
		eternalAttributes.remove(attribute);
	}
	
	private static final class Holder {
		final static SessionCleaner INSTANCE = new SessionCleaner();
	}
}

