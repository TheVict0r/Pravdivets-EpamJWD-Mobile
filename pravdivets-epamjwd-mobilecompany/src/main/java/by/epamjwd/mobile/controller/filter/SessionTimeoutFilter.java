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
		
		Set<String> guestCommands = new HashSet<>();
		guestCommands.add(CommandName.GO_TO_MAIN_PAGE);
		guestCommands.add(CommandName.GO_TO_LOGIN_PAGE);
		guestCommands.add(CommandName.GO_TO_SIGNUP_PAGE);
		guestCommands.add(CommandName.LOGIN);
		guestCommands.add(CommandName.SHOW_ALL_NEWS);
		guestCommands.add(CommandName.SHOW_FULL_ARTICLE);
		guestCommands.add(CommandName.SHOW_ALL_PLANS);
		guestCommands.add(CommandName.SHOW_FULL_PLAN);
		guestCommands.add(CommandName.SHOW_ALL_SERVICES);
		guestCommands.add(CommandName.SHOW_FULL_SERVICE);
		guestCommands.add(CommandName.GO_TO_ERROR_PAGE);
		guestCommands.add(CommandName.GO_TO_CALCULATOR_PAGE);
		guestCommands.add(CommandName.CALCULATOR);
		guestCommands.add(CommandName.SHOW_BEST_PLAN);
		guestCommands.add(CommandName.SWITCH_LOCALE);
		guestCommands.add(CommandName.CHECK_PHONE);
		guestCommands.add(CommandName.GO_TO_CODE_SEND_PAGE);
		guestCommands.add(CommandName.GO_TO_PHONE_REQUEST_PAGE);
		guestCommands.add(CommandName.GO_TO_CODE_RETURN_PAGE);
		guestCommands.add(CommandName.CODE_SEND);
		guestCommands.add(CommandName.CODE_RETURN);
		guestCommands.add(CommandName.NEW_PASSWORD);
		guestCommands.add(CommandName.GO_TO_NEW_PASSWORD_PAGE);
		guestCommands.add(CommandName.GO_TO_PASSWORD_REPAIR_PAGE);
		

		
		if(role == null && !commandName.contains(commandName)) {
			session.setAttribute(AttributeName.SESSION_TIME_OUT, AttributeValue.SESSION_TIME_OUT);
			//response.sendRedirect(request.getContextPath() + PagePath.LOGIN_REDIRECT);
			request.getRequestDispatcher(PagePath.LOGIN).forward(request, response);
		}
		
		//servletResponse = response;
        chain.doFilter(servletRequest, servletResponse);
	}

	
	
	
}
