package by.epamjwd.mobile.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.impl.ShowConsultantByEmailCommand;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowConsultantByPassportCommand implements Command {
	private final static Logger LOGGER = LogManager.getLogger(ShowConsultantByPassportCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		String passport = request.getParameter(ParameterName.PASSPORT);
		UserService userService = ServiceProvider.getInstance().getUserService();
		HttpSession session = request.getSession();
		
		RouteHelper result = RouteHelper.ERROR;
		
		try {
			Optional<User> consultantOptional = userService.findUserByPassport(passport);
//			if(consultantOptional.isPresent() && (consultantOptional.get().getRole() == Role.CONSULTANT)) {
//				long consultantID = consultantOptional.get().getId();
//				session.setAttribute(AttributeName.CONSULTANT_ID, consultantID);
//				result = new RouteHelper(PagePath.CONSULTANT_REDIRECT, RouteMethod.REDIRECT);
//			} else {
//				session.setAttribute(AttributeName.ERROR, AttributeValue.ERROR);
//				session.setAttribute(AttributeName.PASSPORT, passport);
//				result = new RouteHelper(PagePath.CONSULTANT_OPERATIONS_REDIRECT, RouteMethod.REDIRECT);
//			}
			
			result = ConsultantCommandHelper.getInstance().handleConsultantOptional(consultantOptional, session, AttributeName.PASSPORT, passport);
			
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain consultant data for passport number " + passport, e);
			result = RouteHelper.ERROR_500;
		}
		return result;
	}

}