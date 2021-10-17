package by.epamjwd.mobile.util;

import java.util.Random;

import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class PhoneNumberGenerator {

	private final static int OPERATOR_CODE = 440000000; // Let's assume that this mobile operator has the code +375
														// 44xxxxxxx
	private final static int MAX_EXCLUSIVE = 10000000;

	public static int generatePhoneNumber() throws ServiceException {

		int phoneNumber = 0;

		Random random = new Random();
		int firstNumber = OPERATOR_CODE + random.nextInt(MAX_EXCLUSIVE);

		ServiceProvider provider = ServiceProvider.getInstance();
		SubscriberService subscriberService = provider.getSubscriberService();

		if (subscriberService.isPhoneNumberAvailable(firstNumber)) {
			phoneNumber = firstNumber;
		} else {
			generatePhoneNumber();
		}

		return phoneNumber;
	}

}
