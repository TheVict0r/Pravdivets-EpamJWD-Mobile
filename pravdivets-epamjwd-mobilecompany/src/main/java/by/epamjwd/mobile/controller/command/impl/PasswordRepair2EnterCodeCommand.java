package by.epamjwd.mobile.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class PasswordRepair2EnterCodeCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(PasswordRepair2EnterCodeCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Integer repairCode = (Integer)session.getAttribute(AttributeName.REPAIR_CODE);
		session.removeAttribute(AttributeName.REPAIR_CODE);
		int enteredCode = NumericParser.parseIntValue(request.getParameter(ParameterName.ENTERED_CODE));
		
		if (repairCode == enteredCode) {
			String phone = (String)session.getAttribute(AttributeName.PHONE);
			UserService userService = ServiceProvider.getInstance().getUserService();
			try {
				Optional<User> userOptional = userService.findUserByPhone(phone);
				if(userOptional.isPresent()) {
					User user = userOptional.get();
					user.setPassword(null);
					userService.updateUser(user);
				} else {
					LOGGER.error("Error while searching user by phone - " + phone);
					return RouteHelper.ERROR;
				}
			} catch (ServiceException e) {
				LOGGER.error("Error while updating user's password. Users phone - " + phone + e);
				return RouteHelper.ERROR;
			}
		} else {
			session.setAttribute(AttributeName.ERROR, AttributeValue.MISSMATCHED_CODES);
			return new RouteHelper(PagePath.PASSWORD_REPAIR_REDIRECT, RouteMethod.REDIRECT);
		}
		
		session.setAttribute(AttributeName.REPAIR_PASSWORD, AttributeValue.TRUE);
		return new RouteHelper(PagePath.SIGNUP_REDIRECT, RouteMethod.REDIRECT);
	}

}
