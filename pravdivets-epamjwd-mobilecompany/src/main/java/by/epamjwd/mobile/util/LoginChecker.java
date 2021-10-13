package by.epamjwd.mobile.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginChecker {

	public static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	public static final String PHONE_NUMBER_REGEX = "^(25|29|33|44)[0-9]{7}$";
	public static final String NAME = "^[A-ZА-ЯЁ][a-zа-яё\\-]+$";

	public static boolean isEmail(String anyString) {
		Pattern validEmailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = validEmailPattern.matcher(anyString);
		return matcher.find();
	}

	public static boolean isPhoneNumber(String anyString) {
		Pattern validEmailPattern = Pattern.compile(PHONE_NUMBER_REGEX);
		Matcher matcher = validEmailPattern.matcher(anyString);
		return matcher.find();
	}

	public static boolean isName(String anyString) {
		Pattern validEmailPattern = Pattern.compile(NAME);
		Matcher matcher = validEmailPattern.matcher(anyString);
		return matcher.find();
	}
	
}
