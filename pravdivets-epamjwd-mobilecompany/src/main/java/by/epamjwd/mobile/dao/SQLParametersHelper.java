package by.epamjwd.mobile.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;

/**
 * Provide parameters for prepared statements as an array of Objects
 *
 */
public class SQLParametersHelper {

	public final static int DATABASE_INDEX_SHIFT = 1; // as indexes in database starts from 1, not from 0

	private SQLParametersHelper() {

	}


	/**
	 * Provide parameters for prepared statement to add new User entity
	 * 
	 * @param user - User entity
	 * 
	 * @return array of Objects, each Object contains one parameter
	 */
	public static Object[] provideNewUserParameters(User user) {
		List<Object> paramList = new ArrayList<>(Arrays.asList(provideCoreUserParameters(user)));
		paramList.add(user.getRole().ordinal() + DATABASE_INDEX_SHIFT);
		
		return paramList.toArray();
	}

	/**
	 * Provide parameters for prepared statement to update User entity
	 * 
	 * @param user - User entity
	 * 
	 * @return array of Objects, each Object contains one parameter
	 */
	public static Object[] provideUpdateUserParameters(User user) {
		List<Object> paramList = new ArrayList<>(Arrays.asList(provideCoreUserParameters(user)));
		paramList.add(user.getId());
		
		return paramList.toArray();
	}

	private static Object[] provideCoreUserParameters(User user) {
		Object[] userParameters = { user.getPassword(), user.getFirstName(), user.getMiddleName(), user.getLastName(),
				user.getPassport(), user.getEmail() };
		
		return userParameters;
	}

	
	/**
	 * Provide parameters for prepared statement to add new Subscriber entity
	 * 
	 * @param subscriber - Subscriber entity
	 * 
	 * @return array of Objects, each Object contains one parameter
	 */
	public static Object[] provideNewSubscriberParameters(Subscriber subscriber) {
		Object[] subscriberParameters = { subscriber.getContractDate(), subscriber.getAccount(), subscriber.getPhone(),
				subscriber.getStatusDate(), (subscriber.getStatus().ordinal() + DATABASE_INDEX_SHIFT),
				subscriber.getPlanId(), subscriber.getUserId() };

		return subscriberParameters;
	}

	/**
	 * Provide parameters for prepared statement to update Subscriber entity
	 * 
	 * @param subscriber - Subscriber entity
	 * 
	 * @return array of Objects, each Object contains one parameter
	 */
	public static Object[] provideUpdateSubscriberParameters(Subscriber subscriber) {
		Object[] subscriberParameters = { subscriber.getPhone(), subscriber.getStatusDate(),
				(subscriber.getStatus().ordinal() + DATABASE_INDEX_SHIFT), subscriber.getPlanId(), subscriber.getId() };
		
		return subscriberParameters;
	}

	/**
	 * Provide parameters for prepared statement to add new Plan entity
	 * 
	 * @param plan - Plan entity
	 * 
	 * @return array of Objects, each Object contains one parameter
	 */
	public static Object[] provideNewPlanParameters(Plan plan) {
		Object[] planParameters = { plan.getName(), plan.getRegularPayment(), plan.getPriceWithinNetwork(),
				plan.getPriceOtherNetworks(), plan.getPriceAbroad(), plan.getPriceVideocall(), plan.getPriceSMS(),
				plan.getPriceMMS(), plan.getPriceInternet(), plan.getDescription(), plan.getUpfrontPayment() };

		return planParameters;
	}

	/**
	 * Provide parameters for prepared statement to add new Service entity
	 * 
	 * @param service - Service entity
	 * 
	 * @return array of Objects, each Object contains one parameter
	 */
	public static Object[] provideNewServiceParameters(Service service) {
		Object[] serviceParameters = { service.getName(), service.getTariff(), service.getDescription() };

		return serviceParameters;
	}

	/**
	 * Provide parameters for prepared statement to add new Article entity
	 * 
	 * @param article - Article entity
	 * 
	 * @return array of Objects, each Object contains one parameter
	 */
	public static Object[] provideNewArticleParameters(Article article) {
		Object[] articleParameters = { article.getDateAsString(), article.getTitle(), article.getIntro(),
				article.getText() };

		return articleParameters;
	}

}
