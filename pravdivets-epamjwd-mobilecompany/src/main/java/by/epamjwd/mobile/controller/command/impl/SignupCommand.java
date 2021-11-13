package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;

public class SignupCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		int phone = NumericParser.parseIntValue(request.getParameter(ParameterName.PHONE));
		String passport = request.getParameter(ParameterName.PASSPORT);
		String password1 = request.getParameter(ParameterName.PASSWORD1);
		String password2 = request.getParameter(ParameterName.PASSWORD2);
		
		
			System.out.println(phone);
			System.out.println(passport);
			System.out.println(password1);
			System.out.println(password2);
			
		return new RouteHelper(PagePath.SIGNUP, RouteMethod.FORWARD);

	}

}
