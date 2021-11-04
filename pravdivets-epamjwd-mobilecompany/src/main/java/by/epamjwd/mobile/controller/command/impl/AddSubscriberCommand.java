package by.epamjwd.mobile.controller.command.impl;

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
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class AddSubscriberCommand implements Command{
	private final static Logger LOGGER = LogManager.getLogger(AddSubscriberCommand.class);

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
				subscriberService.addNewSubscriber(firstName, 
						middleName, lastName, passport, email, phone, planId);
			} catch (ServiceException e) {
				LOGGER.error("Error when adding a new subscriber with passport number - " + passport, e);
				result = RouteHelper.ERROR;
			}
		} else {
			User currentUser = (User)session.getAttribute(AttributeName.SUBSCRIBER_USER);
			session.removeAttribute(AttributeName.SUBSCRIBER_USER);
			long userId = currentUser.getId();
			try {
				subscriberService.addNewSubscriberToExistingUser(phone, planId, userId);
			} catch (ServiceException e) {
				LOGGER.error("Error when adding a new subscriber to existing user with passport number - " + passport, e);
				result = RouteHelper.ERROR;
			}
			
//			try {
//				Subscriber currentCustomer = subscriberService.findSubscriberListByPassport(passport).get(0);
//				
//				currentCustomer.setContractDate(currentDate);
//				currentCustomer.setCheckingAccountAmount(plan.getUpfrontPayment());
//				currentCustomer.setPhoneNumber(phoneNumber);
//				currentCustomer.setPlanId(plan_id);
//				currentCustomer.setStatus(SubscriberStatus.ACTIVE);
//				currentCustomer.setStatusDate(currentDate);			
				//дублирование кода
				
				
				//обновить абонента currentCustomer и отправить его для добавления только 
				//в таблицу Subscribers
				//в Service делаем метод aaddNewSubscriberToExistingCustomer
				//в таблицу Subscribers вносим 
				//contract_date (текущая дата)
				//account (сумма при подключении, из ТП берем upfrontPayment)
				//phone_number (берем из этого класса)
				//status (1 активный)
				//status_date (текущая дата)
				//tariff_plan_id (берем из этого класса)
				//customer_id (получаем по паспорту из этого класса Customer извлекаем customer_id)
				
				
		}
		
		result = new RouteHelper(PagePath.SUBSCRIBER_OPERATIONS, RouteMethod.FORWARD);
		
		return result;
	}

}
