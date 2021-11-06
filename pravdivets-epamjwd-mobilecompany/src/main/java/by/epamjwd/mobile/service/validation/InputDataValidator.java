package by.epamjwd.mobile.service.validation;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.SubscriberStatus;
import by.epamjwd.mobile.bean.User;

public class InputDataValidator {

	private InputDataValidator() {
	}
	
	public static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	public static final String PHONE_NUMBER_REGEX = "^(25|29|33|44|55)[0-9]{7}$";
	public static final String NAME_REGEX = "^[A-ZА-ЯЁ][a-zа-яё\\-]+$";
	public static final String PASSPORT_REGEX = "^[A-Z]{2}[0-9]{7}$";

	
	public static boolean isUserValid(User user) {
		if (user == null) {
            return false;
        }
		
		long id = user.getId();
		String firstName = user.getFirstName();
		String middleName = user.getMiddleName();
		String lastName = user.getLastName();
		String passport = user.getPassport();
		String email = user.getEmail();
		Role role = user.getRole();
		
		boolean result = false;
		
		result = id >=0 &&
				 isName(firstName) &&
				 (isName(middleName) || middleName.isBlank()) &&
				 isName(lastName) &&
				 isPassport(passport) &&
				 isEmail(email) &&
				 role != null;
		
		return result;
	}
	
	public static boolean isSubscriberValid(Subscriber subscriber) {
		if (subscriber == null) {
            return false;
        }
		
		long id = subscriber.getId();
		Date contractDate = subscriber.getContractDate(); 
		int account = subscriber.getAccount(); 
		int phone = subscriber.getPhone();
		Date statusDate = subscriber.getStatusDate();
		SubscriberStatus status = subscriber.getStatus();
		long planId = subscriber.getPlanId();
		long userId = subscriber.getUserId();

		boolean result = false;

		
	}
	
	
	public static boolean isEmail(String anyString) {
		Pattern validEmailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = validEmailPattern.matcher(anyString);
		return matcher.find();
	}

	public static boolean isPhone(String anyString) {
		Pattern validPhoneNumberPattern = Pattern.compile(PHONE_NUMBER_REGEX);
		Matcher matcher = validPhoneNumberPattern.matcher(anyString);
		return matcher.find();
	}

	public static boolean isName(String anyString) {
		Pattern validNamePattern = Pattern.compile(NAME_REGEX);
		Matcher matcher = validNamePattern.matcher(anyString);
		return matcher.find();
	}
	
	public static boolean isPassport(String passportNumber) {
		Pattern validPassportPattern = Pattern.compile(PASSPORT_REGEX);
		Matcher matcher = validPassportPattern.matcher(passportNumber);
		return matcher.find();
	}
	
}
