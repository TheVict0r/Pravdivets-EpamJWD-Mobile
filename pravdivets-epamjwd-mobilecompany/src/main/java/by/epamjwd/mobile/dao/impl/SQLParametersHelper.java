package by.epamjwd.mobile.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;

public class SQLParametersHelper {

	public final static int DATABASE_INDEX_SHIFT = 1; //as indexes in database begins from 1, not from 0
	
	private SQLParametersHelper() {
		
	}
	
	public static Object[] provideUserParameters(User user) {
		Object[] userParameters = {
				user.getPassword(),
				user.getFirstName(), 
				user.getMiddleName(), 
				user.getLastName(), 
				user.getPassport(), 
				user.getEmail()}; 
		return userParameters;
	}

	public static Object[] provideNewUserParameters(User user) {
		List<Object> paramList = new ArrayList<>(Arrays.asList(provideUserParameters(user)));
		paramList.add(user.getRole().ordinal() + DATABASE_INDEX_SHIFT);
		return paramList.toArray();
	}
	
	public static Object[] provideUpdateUserParameters(User user) {
		List<Object> paramList = new ArrayList<>(Arrays.asList(provideUserParameters(user)));
		paramList.add(user.getId());
		return paramList.toArray();
	}

	public static Object[] provideNewSubscriberParameters(Subscriber subscriber) {
		Object[] subscriberParameters = {
				   subscriber.getContractDate(), 
				   subscriber.getAccount(), 
				   subscriber.getPhone(), 
				   subscriber.getStatusDate(), 
				  (subscriber.getStatus().ordinal() + DATABASE_INDEX_SHIFT), 
				   subscriber.getPlanId(), 
				   subscriber.getUserId()};

		return subscriberParameters;
	}

	public static Object[] provideNewPlanParameters(Plan plan) {
		Object[] planParameters = {
				plan.getName(),
				plan.getRegularPayment(),
				plan.getPriceWithinNetwork(),
				plan.getPriceOtherNetworks(),
				plan.getPriceAbroad(),
				plan.getPriceVideocall(),
				plan.getPriceSMS(),
				plan.getPriceMMS(),
				plan.getPriceInternet(),
				plan.getDescription(),
				plan.getUpfrontPayment()
				};
		
		return planParameters;
	}
	
	
	public static Object[] provideUpdateSubscriberParameters(Subscriber subscriber) {
		Object[] subscriberParameters = {
				   subscriber.getPhone(), 
				   subscriber.getStatusDate(), 
				  (subscriber.getStatus().ordinal() + DATABASE_INDEX_SHIFT), 
				   subscriber.getPlanId(),
				   subscriber.getId()}; 
		return subscriberParameters;
	}


	
	
}
