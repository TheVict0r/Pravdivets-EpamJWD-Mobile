package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.controller.repository.ParameterValue;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class AddSubscriberCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ServiceProvider serviceProvider = ServiceProvider.getInstance();
		SubscriberService subscriberService = serviceProvider.getSubscriberService();

		String passport = request.getParameter(ParameterName.PASSPORT);
		String phoneNumber = request.getParameter(ParameterName.PHONE_NUMBER);
		String plan_id = request.getParameter(ParameterName.PLAN_ID);
		String subscriberType = request.getParameter(ParameterName.SUBSCRIBER_TYPE);
		
		String firstName = null;
		String middleName = null;
		String lastName = null;
		String homeAddress = null;
		String email = null;
		
		if(subscriberType.equals(ParameterValue.NEW_SUBSCRIBER)) {
			firstName = request.getParameter(ParameterName.FIRST_NAME);
			middleName = request.getParameter(ParameterName.MIDDLE_NAME);
			lastName = request.getParameter(ParameterName.LAST_NAME);
			homeAddress = request.getParameter(ParameterName.HOME_ADDRESS);
			email = request.getParameter(ParameterName.EMAIL);
			
			Subscriber newSubscriber = new Subscriber();
			
			//subscriberService.addNewCustomer
			
			//создать нового Subscriber и добавить его в таблицы Users, Customers и Subscribers
		} else {
			try {
				Subscriber currentCustomer = subscriberService.findSubscriberListByPassport(passport).get(0);
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
				
				//subscriberService.addNewSubscriberToExistingCustomer(currentCustomer, phoneNumber, plan_id)
				
				System.out.println("СТАРЫЙ - " + currentCustomer);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println(firstName);
		System.out.println(middleName);
		System.out.println(lastName);
		System.out.println(homeAddress);
		System.out.println(email);
		System.out.println(passport);
		System.out.println(phoneNumber);
		System.out.println(plan_id);
		
		RouteHelper result = null;
		
		result = new RouteHelper(PagePath.SUBSCRIBER_BASE, RouteMethod.FORWARD);
		
		return result;
	}

}
