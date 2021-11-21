package by.epamjwd.mobile.controller.listener;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import by.epamjwd.mobile.controller.command.CommandProvider;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.CommandName;
import by.epamjwd.mobile.controller.repository.PagePath;

public class SessionTimeoutListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		session.setAttribute(AttributeName.ROLE, AttributeValue.GUEST);
	}

//	public void sessionDestroyed(HttpSessionEvent event) {
//
//		HttpSession session = event.getSession();
//		String role = (String) session.getAttribute(AttributeName.ROLE);
//      System.out.println(role);
//		if (!AttributeValue.GUEST.equalsIgnoreCase(role)) {
//			session.setAttribute(AttributeName.SESSION_TIME_OUT, AttributeValue.SESSION_TIME_OUT);
//			try {
//				session.getServletContext().getRequestDispatcher(PagePath.LOGIN).forward(null, null);
//			} catch (ServletException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			CommandProvider.getInstance().getCommand(CommandName.GO_TO_LOGIN_PAGE);
//		}
//
//	}

}
