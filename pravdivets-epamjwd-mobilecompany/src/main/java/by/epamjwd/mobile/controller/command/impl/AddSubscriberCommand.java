package by.epamjwd.mobile.controller.command.impl;

import java.util.Date;

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
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.DateProvider;

public class AddSubscriberCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(AddSubscriberCommand.class);
	private final static String EMPTY_STRING = "";
	private final static long EMPTY_ID = 0L;
	
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		SubscriberService subscriberService = serviceProvider.getSubscriberService();

		String passport = (String.valueOf(session.getAttribute(AttributeName.PASSPORT)));
		session.removeAttribute(AttributeName.PASSPORT);
		
		int phone = Integer.parseInt(String.valueOf(session.getAttribute(AttributeName.PHONE)));
		session.removeAttribute(AttributeName.PHONE);
		
		long planId = NumericParser.parseLongValue(request.getParameter(ParameterName.PLAN_ID));
		
		String subscriberUserFlag = (String.valueOf(session.getAttribute(AttributeName.SUBSCRIBER_USER_FLAG)));
		session.removeAttribute(AttributeName.SUBSCRIBER_USER_FLAG);
		
		RouteHelper result = null;
		
		if(subscriberUserFlag.equals(AttributeValue.NEW)) {
			String firstName = request.getParameter(ParameterName.SUBSCRIBER_USER_FIRST_NAME);
			String middleName = request.getParameter(ParameterName.SUBSCRIBER_USER_MIDDLE_NAME);
			String lastName = request.getParameter(ParameterName.SUBSCRIBER_USER_LAST_NAME);
			String email = request.getParameter(ParameterName.EMAIL);
			
			try {
				User user = this.buildUser(firstName, middleName, lastName, passport, email);
				Subscriber subscriber = this.buildSubscriber(phone, planId, EMPTY_ID);
				subscriberService.addNewSubscriber(user, subscriber);
			} catch (ServiceException e) {
				LOGGER.error("Error when adding a new subscriber with passport number - " + passport, e);
				result = RouteHelper.ERROR;
			}
		} else {
			User currentUser = (User)session.getAttribute(AttributeName.SUBSCRIBER_USER);
			session.removeAttribute(AttributeName.SUBSCRIBER_USER);
			long userId = currentUser.getId();
			
			try {
				Subscriber subscriber = this.buildSubscriber(phone, planId, userId);
				subscriberService.addNewSubscriberToExistingUser(subscriber);
			} catch (ServiceException e) {
				LOGGER.error("Error when adding a new subscriber to existing user with passport number - " + passport, e);
				result = RouteHelper.ERROR;
			}
			
				
		}
		
		result = new RouteHelper(PagePath.SUBSCRIBER_OPERATIONS, RouteMethod.FORWARD);
		
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
	
}
