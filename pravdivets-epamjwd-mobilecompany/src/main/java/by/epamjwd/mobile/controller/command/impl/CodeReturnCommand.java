package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;

public class CodeReturnCommand implements Command{
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int sentCode = NumericParser.parseUnsignedIntValue(session.getAttribute(AttributeName.CODE));
		
		if (sentCode == NumericParser.INVALID_VALUE) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.CODE_RETURN_REDIRECT, RouteMethod.REDIRECT);
		}
		
		session.removeAttribute(AttributeName.CODE);
		int enteredCode = NumericParser.parseUnsignedIntValue(request.getParameter(ParameterName.ENTERED_CODE));
		
		RouteHelper result;
		
		if (sentCode == enteredCode) {
			result = new RouteHelper(PagePath.NEW_PASSWORD_REDIRECT, RouteMethod.REDIRECT);
		} else {
			session.setAttribute(AttributeName.ERROR, AttributeValue.MISSMATCHED_CODES);
			result = new RouteHelper(PagePath.CODE_RETURN_REDIRECT, RouteMethod.REDIRECT);
		}
		
		return result;
	}

}
