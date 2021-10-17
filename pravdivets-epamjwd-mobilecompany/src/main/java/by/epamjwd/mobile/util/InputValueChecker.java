package by.epamjwd.mobile.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValueChecker {

	public static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	public static final String PHONE_NUMBER_REGEX = "^(25|29|33|44|55)[0-9]{7}$";
	public static final String NAME_REGEX = "^[A-ZА-ЯЁ][a-zа-яё\\-]+$";
	public static final String PASSPORT_REGEX = "^[A-Z]{2}[0-9]{7}$";

	public static boolean isEmail(String anyString) {
		Pattern validEmailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = validEmailPattern.matcher(anyString);
		return matcher.find();
	}

	public static boolean isPhoneNumber(String anyString) {
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
