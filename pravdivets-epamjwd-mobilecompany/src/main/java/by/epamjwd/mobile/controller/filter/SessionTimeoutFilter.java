package by.epamjwd.mobile.controller.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.CommandName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;

public class SessionTimeoutFilter implements Filter{


	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Role role = (Role) session.getAttribute(AttributeName.ROLE);
		String commandName = request.getParameter(ParameterName.COMMAND);
		
		Set<String> authorisedCommands = new HashSet<>();
		authorisedCommands.add(CommandName.EDIT_PROFILE);
		authorisedCommands.add(CommandName.LOGOUT);
		authorisedCommands.add(CommandName.SHOW_SUBSCRIBER_LIST_BY_USER_ID);
		authorisedCommands.add(CommandName.SHOW_SUBSCRIBER_LIST_BY_FULL_NAME);
		authorisedCommands.add(CommandName.SHOW_SUBSCRIBER_LIST_BY_PASSPORT);
		authorisedCommands.add(CommandName.SHOW_ADMIN);
		authorisedCommands.add(CommandName.GO_TO_SUBSCRIBER_OPERATIONS_PAGE);
		authorisedCommands.add(CommandName.GO_TO_SUBSCRIBER_PAGE);
		authorisedCommands.add(CommandName.GO_TO_SUBSCRIBER_SERVICES_PAGE);
		authorisedCommands.add(CommandName.GO_TO_SUBSCRIBER_BILLS_PAGE);
		authorisedCommands.add(CommandName.SHOW_SUBSCRIBER_BY_PHONE);
		authorisedCommands.add(CommandName.SHOW_SUBSCRIBER_BY_ID);
		authorisedCommands.add(CommandName.GO_TO_ADD_SUBSCRIBER_PAGE);
		authorisedCommands.add(CommandName.GO_TO_PROFILE_PAGE);
		authorisedCommands.add(CommandName.ADD_SUBSCRIBER);
		authorisedCommands.add(CommandName.ADD_SUBSCRIBER_PREPARATION);
		authorisedCommands.add(CommandName.EDIT_PERSONAL_DATA);
		authorisedCommands.add(CommandName.EDIT_PERSONAL_DATA_PREPARATION);
		authorisedCommands.add(CommandName.CHANGE_PHONE);
		authorisedCommands.add(CommandName.CHANGE_PHONE_PREPARATION);
		authorisedCommands.add(CommandName.CHANGE_PLAN);
		authorisedCommands.add(CommandName.CHANGE_PLAN_PREPARATION);
		authorisedCommands.add(CommandName.CHANGE_STATUS);
		authorisedCommands.add(CommandName.CHANGE_STATUS_PREPARATION);
		authorisedCommands.add(CommandName.CANCEL_EDIT_SUBSCRIBER_DATA);

		
		if( (role == null) &&  (authorisedCommands.contains(commandName)) ) {
			session.setAttribute(AttributeName.SESSION_TIME_OUT, AttributeValue.SESSION_TIME_OUT);
			response.sendRedirect(request.getContextPath() + PagePath.LOGIN_REDIRECT);
			return;
		}
		
        chain.doFilter(servletRequest, servletResponse);
	}

	
	
	
}
