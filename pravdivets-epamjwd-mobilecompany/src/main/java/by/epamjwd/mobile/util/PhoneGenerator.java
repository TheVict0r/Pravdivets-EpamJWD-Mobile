package by.epamjwd.mobile.util;

import java.util.Random;

import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class PhoneGenerator {

	private final static int OPERATOR_CODE = 550000000; // Let's assume that this mobile operator has the 
														// code +375 55xxxxxxx
	private final static int MAX_EXCLUSIVE = 10000000;
	private final static int MIN_INCLUSIVE =  1000000;

	public static String generatePhone() throws ServiceException {

		String phone = "";

		Random random = new Random();
		String candidatePhone = String.valueOf(OPERATOR_CODE + (random.nextInt(MAX_EXCLUSIVE - MIN_INCLUSIVE) + MIN_INCLUSIVE));

		SubscriberService subscriberService = ServiceProvider.getInstance().getSubscriberService();

		if (subscriberService.isPhoneAvailable(candidatePhone)) {
			phone = candidatePhone;
		} else {
			generatePhone();
		}

		return phone;
	}

}
