package by.epamjwd.mobile.controller.command.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.path.Action;
import by.epamjwd.mobile.controller.path.PathRepository;
import by.epamjwd.mobile.controller.path.Routing;
import by.epamjwd.mobile.service.ServiceProvider;

public class AuthenticationCommand implements Command {

	@Override
	public Routing execute(HttpServletRequest request, HttpServletResponse response) {
		int phoneNumber = Integer.parseInt(request.getParameter("phone"));
		char[] password = request.getParameter("password").toCharArray();
		
		ServiceProvider provider = ServiceProvider.getInstance();


		System.out.println(phoneNumber);
		System.out.println(password);

		//TEMPORARY ROUTING
		Routing result = new Routing(PathRepository.MAIN, Action.FORWARD);
		return result;

		
	}

	
	
}
