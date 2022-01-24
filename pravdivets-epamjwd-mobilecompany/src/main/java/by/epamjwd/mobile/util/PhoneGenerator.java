package by.epamjwd.mobile.util;

import java.util.Random;

import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;

/*
 * Generates new phone numbers for subscribers
 */
public class PhoneGenerator {

	private final static int OPERATOR_CODE = 550000000; // Let's assume that this mobile operator has the 
														// code +375 55xxxxxxx
	private final static int MAX_EXCLUSIVE = 10000000;
	private final static int MIN_INCLUSIVE =  1000000;

	private PhoneGenerator() {
	}
	
	public static PhoneGenerator getInstance() {
		return Holder.INSTANCE;
	}
	
	private static class Holder{
		private final static PhoneGenerator INSTANCE = new PhoneGenerator();
	}
	
	/**
	 * Provides a new phone number available in mobile operator's database. 
	 * 
	 * <p>Checks, if this phone number is already presented in mobile operator's database. 
	 * If the number is occupied tries another number through recursion 
	 * until the free number is generated.
	 * 
	 * @return phone number
	 * @throws ServiceException  if a database access error occurs during checking 
	 * is phone number is available in the database
	 */
	public String provideFreePhone() throws ServiceException {
		String phoneResult = "";
		String candidatePhone = generateRandomPhoneNumber();

		SubscriberService subscriberService = ServiceProvider.getInstance().getSubscriberService();

		if (subscriberService.isPhoneExist(candidatePhone)) {
			provideFreePhone();
		} else {
			phoneResult = candidatePhone;
		}

		return phoneResult;
	}

	
	/**
	 * Generates pseudorandom phone number with first two digits as {@code 55} as mobile operator prefix code.
	 * 
	 * @return random phone number 
	 */
	private static String generateRandomPhoneNumber() {
		Random random = new Random();
		return String.valueOf(OPERATOR_CODE + (random.nextInt(MAX_EXCLUSIVE - MIN_INCLUSIVE) + MIN_INCLUSIVE));
	}
	
	
}
