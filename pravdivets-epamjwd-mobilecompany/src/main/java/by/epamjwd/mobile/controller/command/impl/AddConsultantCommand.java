package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.ParameterName;

public class AddConsultantCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String firstName  = request.getParameter(ParameterName.FIRST_NAME);
		String middleName = request.getParameter(ParameterName.MIDDLE_NAME);
		String lastName   = request.getParameter(ParameterName.LAST_NAME);
		String passport   = request.getParameter(ParameterName.PASSPORT);
		String password1  = request.getParameter(ParameterName.PASSWORD1);
		String password2  = request.getParameter(ParameterName.PASSWORD2);
		String email      = request.getParameter(ParameterName.EMAIL);
		
		System.out.println(firstName);
		System.out.println(middleName);
		System.out.println(lastName);
		System.out.println(passport);
		System.out.println(password1);
		System.out.println(password2);
		System.out.println(email);
		
		return null;
	}

}
