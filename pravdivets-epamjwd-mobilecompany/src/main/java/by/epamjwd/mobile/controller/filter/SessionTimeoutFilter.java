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

/**
 * Filter used to provide a session timeout function for operations that
 * requires authorization. All these operations (commands) are presented in the
 * Set.
 *
 */
public class SessionTimeoutFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		Role role = (Role) session.getAttribute(AttributeName.ROLE);
		String commandName = request.getParameter(ParameterName.COMMAND);

		Set<String> authorisationRequired = new HashSet<>();
		authorisationRequired.add(CommandName.ADD_ARTICLE);
		authorisationRequired.add(CommandName.ADD_CONSULTANT);
		authorisationRequired.add(CommandName.ADD_PLAN);
		authorisationRequired.add(CommandName.ADD_SERVICE);
		authorisationRequired.add(CommandName.ADD_SUBSCRIBER);
		authorisationRequired.add(CommandName.ADD_SUBSCRIBER_PREPARATION);
		authorisationRequired.add(CommandName.CANCEL_EDIT_CONSULTANT);
		authorisationRequired.add(CommandName.CANCEL_EDIT_SUBSCRIBER_DATA);
		authorisationRequired.add(CommandName.CHANGE_PASSWORD);
		authorisationRequired.add(CommandName.CHANGE_PHONE);
		authorisationRequired.add(CommandName.CHANGE_PHONE_PREPARATION);
		authorisationRequired.add(CommandName.CHANGE_PLAN);
		authorisationRequired.add(CommandName.CHANGE_PLAN_PREPARATION);
		authorisationRequired.add(CommandName.CHANGE_STATUS);
		authorisationRequired.add(CommandName.CHANGE_STATUS_PREPARATION);
		authorisationRequired.add(CommandName.EDIT_CONSULTANT);
		authorisationRequired.add(CommandName.EDIT_CONSULTANT_PREPARATION);
		authorisationRequired.add(CommandName.EDIT_PERSONAL_DATA);
		authorisationRequired.add(CommandName.EDIT_PERSONAL_DATA_PREPARATION);
		authorisationRequired.add(CommandName.FIND_ARTICLE_BY_ID);
		authorisationRequired.add(CommandName.FIND_ARTICLE_BY_TITLE);
		authorisationRequired.add(CommandName.FIND_CONSULTANT_BY_EMAIL);
		authorisationRequired.add(CommandName.FIND_CONSULTANT_BY_PASSPORT);
		authorisationRequired.add(CommandName.FIND_FULL_PLAN_ADMIN);
		authorisationRequired.add(CommandName.FIND_FULL_SERVICE_ADMIN);
		authorisationRequired.add(CommandName.FIND_SUBSCRIBER_BY_ID);
		authorisationRequired.add(CommandName.FIND_SUBSCRIBER_BY_PHONE);
		authorisationRequired.add(CommandName.FIND_SUBSCRIBER_LIST_BY_FULL_NAME);
		authorisationRequired.add(CommandName.FIND_SUBSCRIBER_LIST_BY_PASSPORT);
		authorisationRequired.add(CommandName.FIND_SUBSCRIBER_LIST_BY_USER_ID);
		authorisationRequired.add(CommandName.GO_TO_ADD_ARTICLE_PAGE);
		authorisationRequired.add(CommandName.GO_TO_ADD_CONSULTANT_PAGE);
		authorisationRequired.add(CommandName.GO_TO_ADD_PLAN_PAGE);
		authorisationRequired.add(CommandName.GO_TO_ADD_SERVICE_PAGE);
		authorisationRequired.add(CommandName.GO_TO_ADD_SUBSCRIBER_PAGE);
		authorisationRequired.add(CommandName.GO_TO_ADMIN_PAGE);
		authorisationRequired.add(CommandName.GO_TO_ARTICLE_ADMIN_PAGE);
		authorisationRequired.add(CommandName.GO_TO_ARTICLE_OPERATIONS_PAGE);
		authorisationRequired.add(CommandName.GO_TO_CONSULTANT_PAGE);
		authorisationRequired.add(CommandName.GO_TO_CONSULTANT_OPERATIONS_PAGE);
		authorisationRequired.add(CommandName.GO_TO_EDIT_CONSULTANT_PAGE);
		authorisationRequired.add(CommandName.GO_TO_PLAN_ADMIN_PAGE);
		authorisationRequired.add(CommandName.GO_TO_PLAN_OPERATIONS_PAGE);
		authorisationRequired.add(CommandName.GO_TO_PROFILE_PAGE);
		authorisationRequired.add(CommandName.GO_TO_SERVICE_ADMIN_PAGE);
		authorisationRequired.add(CommandName.GO_TO_SERVICE_OPERATIONS_PAGE);
		authorisationRequired.add(CommandName.GO_TO_SUBSCRIBER_BILLS_PAGE);
		authorisationRequired.add(CommandName.GO_TO_SUBSCRIBER_OPERATIONS_PAGE);
		authorisationRequired.add(CommandName.GO_TO_SUBSCRIBER_PAGE);
		authorisationRequired.add(CommandName.GO_TO_SUBSCRIBER_SERVICES_PAGE);

		if ((role == null) && (authorisationRequired.contains(commandName))) {
			session.setAttribute(AttributeName.SESSION_TIME_OUT, AttributeValue.SESSION_TIME_OUT);
			response.sendRedirect(request.getContextPath() + PagePath.LOGIN_REDIRECT);
			return;
		}

		chain.doFilter(servletRequest, servletResponse);
	}

}
