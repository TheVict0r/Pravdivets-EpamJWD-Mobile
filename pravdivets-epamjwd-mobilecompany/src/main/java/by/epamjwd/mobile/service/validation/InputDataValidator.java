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
	
	public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
	public static final String PHONE_NUMBER_REGEX = "^(25|29|33|44|55)[0-9]{7}$";
	public static final String NAME_REGEX = "^([А-Я]{1}[а-яё-]+|[A-Z]{1}[a-z-]+)$";
	public static final String PASSPORT_REGEX = "^[A-Z]{2}[0-9]{7}$";
	public static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
	public static final    int DELAY_TIME = 1;

	
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
		result = 
				 id >= 0 &&
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
		int phone = subscriber.getPhone();
		Date statusDate = subscriber.getStatusDate();
		SubscriberStatus status = subscriber.getStatus();
		long planId = subscriber.getPlanId();
		long userId = subscriber.getUserId();

		Date currentDelayed = new Date((new Date()).getTime() + DELAY_TIME);
		
		boolean result = false;
		result = 
				id >= 0 &&
				contractDate != null &&
			    contractDate.before(currentDelayed) && 
				isPhone(String.valueOf(phone)) &&
				statusDate != null &&
			    statusDate.before(currentDelayed) && 
				status != null &&
				planId > 0 &&
				userId >= 0;
		return result;
	}

	public static boolean isEmail(String anyString) {
		return doesMatchePattern(anyString, EMAIL_REGEX);
	}

	public static boolean isPhone(String anyString) {
		return doesMatchePattern(anyString, PHONE_NUMBER_REGEX);
	}

	public static boolean isName(String anyString) {
		return doesMatchePattern(anyString, NAME_REGEX);
	}
	
	public static boolean isPassport(String passport) {
		return doesMatchePattern(passport, PASSPORT_REGEX);
		
	}

	public static boolean isPassword(String password) {
		return doesMatchePattern(password, PASSWORD_REGEX);
		
	}
	
	
	
	private static boolean doesMatchePattern(String anyString, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(anyString);
		return matcher.find();
	}
	
	
}
