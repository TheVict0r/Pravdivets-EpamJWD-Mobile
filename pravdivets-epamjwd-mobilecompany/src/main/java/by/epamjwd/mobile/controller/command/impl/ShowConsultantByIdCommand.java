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
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowConsultantByIdCommand implements Command {
	private final static Logger LOGGER = LogManager.getLogger(AddSubscriberCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		RouteHelper result = RouteHelper.ERROR;
		HttpSession session = request.getSession();
		long consultantId = NumericParser.parseLongValue(session.getAttribute(AttributeName.CONSULTANT_ID));

		if (consultantId == NumericParser.INVALID_VALUE) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.CONSULTANT_OPERATIONS_REDIRECT, RouteMethod.REDIRECT);
		}

		UserService userService = ServiceProvider.getInstance().getUserService();
		Optional<User> consultantOptional = Optional.empty();
		User consultant = null;
		
		try {
			consultantOptional = userService.findUserById(consultantId);
		} catch (ServiceException e) {
			LOGGER.error("Error when finding consultant by ID " + consultantId, e);
			return RouteHelper.ERROR_500;
		}
		
		if(consultantOptional.isPresent()) {
			consultant = consultantOptional.get();
			session.setAttribute(AttributeName.CONSULTANT, consultant);
			result = new RouteHelper(PagePath.CONSULTANT, RouteMethod.FORWARD);
		}else {
			LOGGER.error("Error when finding consultant by ID " + consultantId);
			return RouteHelper.ERROR_404;
		}
		
		
		return result;
	}

}
