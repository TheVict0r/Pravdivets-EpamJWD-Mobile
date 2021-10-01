package by.epamjwd.mobile.controller.command.impl;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;

public class AuthenticationCommand implements Command {

	public static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	public static final String PHONE_NUMBER_REGEX = "\\d{9}";

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String login = request.getParameter(ParameterName.LOGIN);
		char[] password = request.getParameter(ParameterName.PASSWORD).toCharArray();
		
		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserService();
		User user = userService.getUserByEmail(login);

		System.out.println(login);
		System.out.println("e-mail - " + isEmail(login));
		System.out.println("PhoneNumber - " + isPhoneNumber(login));

		System.out.println("USER - " + user);
		//System.out.println("role - " + user.getRole());
		
		
		//TEMPORARY ROUTING
		RouteHelper result = new RouteHelper(PagePath.MAIN_REDIRECT, RouteMethod.REDIRECT);
		return result;
	}

	private boolean isEmail(String anyString) {
		Pattern validEmailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = validEmailPattern.matcher(anyString);
		return matcher.find();
	}
	
	private boolean isPhoneNumber(String anyString) {
		Pattern validEmailPattern = Pattern.compile(PHONE_NUMBER_REGEX);
		Matcher matcher = validEmailPattern.matcher(anyString);
		return matcher.find();
	}
	
	
}
