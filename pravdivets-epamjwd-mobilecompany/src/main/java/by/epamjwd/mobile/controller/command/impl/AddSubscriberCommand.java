package by.epamjwd.mobile.controller.command.impl;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.SubscriberStatus;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.CustomerService;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class AddSubscriberCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(AddSubscriberCommand.class);
	private final static String EMPTY_STRING = "";
	private final static long EMPTY_ID = 0L;
	private final static long ERROR_ID = -1L;
	
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		UserService userService = serviceProvider.getUserService();
		SubscriberService subscriberService = serviceProvider.getSubscriberService();
		CustomerService customerService = serviceProvider.getCustomerService();

		String passport = (String.valueOf(session.getAttribute(AttributeName.PASSPORT)));
		int phone = NumericParser.parseIntValue(session.getAttribute(AttributeName.PHONE));
		long planId = NumericParser.parseLongValue(request.getParameter(ParameterName.PLAN_ID));
		String subscriberUserFlag = String.valueOf(session.getAttribute(AttributeName.SUBSCRIBER_USER_FLAG));
		
		long subscriberId = ERROR_ID; 
		RouteHelper result = null;
		
		if (subscriberUserFlag.equals(AttributeValue.NEW)) {
				String firstName = request.getParameter(ParameterName.SUBSCRIBER_USER_FIRST_NAME);
				String middleName = request.getParameter(ParameterName.SUBSCRIBER_USER_MIDDLE_NAME);
				String lastName = request.getParameter(ParameterName.SUBSCRIBER_USER_LAST_NAME);
				String email = request.getParameter(ParameterName.EMAIL);

			try {
				if (userService.findUserByEmail(email).isPresent()) {
						session.setAttribute(AttributeName.ERROR, AttributeValue.BOOKED_EMAIL);
						session.setAttribute(AttributeName.SUBSCRIBER_USER_FIRST_NAME, firstName);
						session.setAttribute(AttributeName.SUBSCRIBER_USER_MIDDLE_NAME, middleName);
						session.setAttribute(AttributeName.SUBSCRIBER_USER_LAST_NAME, lastName);
						session.setAttribute(AttributeName.EMAIL, email);
					return new RouteHelper(PagePath.ADD_SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
				}
			} catch (ServiceException e1) {
				LOGGER.error("Error when verifying the existence of a user by e-mail " + email, e1);
				result = RouteHelper.ERROR;
			}

			removeUnusedAttributes(session);

			try {
				User user = this.buildUser(firstName, middleName, lastName, passport, email);
				Subscriber subscriber = this.buildSubscriber(phone, planId, EMPTY_ID);
				subscriberId = customerService.addNewCustomer(user, subscriber);
			} catch (ServiceException e) {
				LOGGER.error("Error when adding a new subscriber with passport number - " + passport, e);
				result = RouteHelper.ERROR;
			}

		} else  {
			User currentUser = (User)session.getAttribute(AttributeName.SUBSCRIBER_USER);
			
			session.removeAttribute(AttributeName.SUBSCRIBER_USER);
			removeUnusedAttributes(session);
			
			long userId = currentUser.getId();
			try {
				Subscriber subscriber = this.buildSubscriber(phone, planId, userId);
				subscriberId = subscriberService.addNewSubscriberToExistingUser(subscriber);
			} catch (ServiceException e) {
				LOGGER.error("Error when adding a new subscriber to existing user with passport number - " + passport, e);
				result = RouteHelper.ERROR;
			}
		}
		
		session.setAttribute(AttributeName.SUBSCRIBER_ID, subscriberId);
		result = new RouteHelper(PagePath.NEW_SUBSCRIBER_REDIRECT, RouteMethod.REDIRECT);
		
		return result;
	}

	private User buildUser(String firstName, String middleName, String lastName, 
			String passport, String email) {
		User user = null;
		user = new User(EMPTY_ID, EMPTY_STRING, firstName, middleName, lastName, 
						passport, email, Role.SUBSCRIBER);
		return user;
	}
	
	private Subscriber buildSubscriber(int phone, long planId, long userId) throws ServiceException {
		ServiceProvider serviceProvider = ServiceProvider.getInstance();	
		PlanService planService = serviceProvider.getPlanService();
		Plan plan = planService.findPlanByID(planId);
		int account = plan.getUpfrontPayment();
		
		Subscriber subscriber = new Subscriber(EMPTY_ID, new Date(), account, phone, 
				new Date(), SubscriberStatus.ACTIVE, planId, userId);
		
		return subscriber;
	}
	
	private void removeUnusedAttributes(HttpSession session) {
		session.removeAttribute(AttributeName.SUBSCRIBER_USER_FLAG);
		session.removeAttribute(AttributeName.PASSPORT);
		session.removeAttribute(AttributeName.PHONE);

	}
	
}
