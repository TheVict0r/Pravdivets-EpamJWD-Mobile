package by.epamjwd.mobile.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;

public class SQLParametersHelper {

	private SQLParametersHelper() {
		
	}
	
	public static Object[] provideUserParameters(User user) {
		Object[] userParameters = {
				user.getFirstName(), 
				user.getMiddleName(), 
				user.getLastName(), 
				user.getPassport(), 
				user.getEmail()}; 
		return userParameters;
	}

	public static Object[] provideNewUserParameters(User user) {
		List<Object> paramList = new ArrayList<>(Arrays.asList(provideUserParameters(user)));
		paramList.add(user.getRole().ordinal() + 1); //as role id in database begins from 1, not from 0
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
				  (subscriber.getStatus().ordinal() + 1), //as status id in database begins from 1, not from 0
				   subscriber.getPlanId(), 
				   subscriber.getUserId()};

		return subscriberParameters;
	}

	
//	"UPDATE subscribers SET phone=?, status_date=?, status_id=?, plan_id=? WHERE id=?";
	
	public static Object[] provideUpdateSubscriberParameters(Subscriber subscriber) {
		Object[] subscriberParameters = {
				   subscriber.getPhone(), 
				   subscriber.getStatusDate(), 
				  (subscriber.getStatus().ordinal() + 1), //as status id in database begins from 1, not from 0
				   subscriber.getPlanId(),
				   subscriber.getId()}; 
		return subscriberParameters;
	}


	
	
}
