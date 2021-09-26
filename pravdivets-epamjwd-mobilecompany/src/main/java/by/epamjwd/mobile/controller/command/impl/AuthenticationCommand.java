package by.epamjwd.mobile.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.service.ServiceProvider;

public class AuthenticationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int phoneNumber = Integer.parseInt(request.getParameter("phone"));
		char[] password = request.getParameter("password").toCharArray();
		
		ServiceProvider provider = ServiceProvider.getInstance();


		System.out.println(phoneNumber);
		System.out.println(password);
		
	}

	
	
}
