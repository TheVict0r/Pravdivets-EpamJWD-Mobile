package by.epamjwd.mobile.controller.command.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.path.RoutingMethod;
import by.epamjwd.mobile.controller.path.PathRepository;
import by.epamjwd.mobile.controller.path.Routing;


public class LoginCommand implements Command {

	@Override
	public Routing execute(HttpServletRequest request, HttpServletResponse response) {

		Routing result = new Routing(PathRepository.LOGIN, RoutingMethod.FORWARD);
		return result;
	
		
	}

}
