package by.epamjwd.mobile.controller.command.helpers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class UserCommandHelper {
	private final static Logger LOGGER = LogManager.getLogger(UserCommandHelper.class);

	private UserCommandHelper() {
	}

	/**
	 * Updates user's data (as a part of subscriber's data only) in the data storage
	 * and sets updated user to HttpSession.
	 * 
	 * @param user    - User entity
	 * 
	 * @param request - HttpServletRequest
	 * 
	 * @return - RouteHelper - the path to page where updated user will be shown
	 */
	public static RouteHelper handleUserUpdate(User user, HttpServletRequest request) {
		UserService userService = ServiceProvider.getInstance().getUserService();
		HttpSession session = request.getSession();
		long userID = user.getId();

		try {
			userService.updateUser(user);
		} catch (ServiceException e) {
			LOGGER.error("Error during updating user data", e);
			return RouteHelper.ERROR_500;
		}

		try {
			Optional<User> userOptional = userService.findUserById(userID);
			if (userOptional.isPresent()) {
				User updatedUser = userOptional.get();
				session.setAttribute(AttributeName.SUBSCRIBER_USER, updatedUser);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error retrieving updated user", e);
			return RouteHelper.ERROR_500;
		}

		return new RouteHelper(PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
	}

	public static RouteHelper provideErrorMessage(HttpSession session, String attributeValue) {
		session.setAttribute(AttributeName.ERROR, attributeValue);
		return new RouteHelper(PagePath.SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
	}

}
