package by.epamjwd.mobile.dao.impl;

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
				user.getEmail(), 
			   (user.getRole().ordinal() + 1) }; //as role id in database begins from 1, not from 0
		
		return userParameters;
	}

	public static Object[] provideSubscriberParameters(Subscriber subscriber) {
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
}
