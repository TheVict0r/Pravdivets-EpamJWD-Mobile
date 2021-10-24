package by.epamjwd.mobile.controller.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericParser {

	public static final String NUMERIC_REGEX = "\\d+";
	public static final int INVALID_VALUE = -1;
	
	
	private NumericParser() {
		
	}

	public static int parseIntValue(Object objectValue) {
		String stringValue = String.valueOf(objectValue);
		int result;
		if (isNumericParameter(stringValue)) {
			result = Integer.parseInt(stringValue);
		} else {
			result = INVALID_VALUE;
		}
		return result;
	}	
	
	public static long parseLongValue(Object objectValue) {
		String stringValue = String.valueOf(objectValue);
		long result;
		if (isNumericParameter(stringValue)) {
			result = Long.parseLong(stringValue);
		} else {
			result = INVALID_VALUE;
		}
		return result;
	}	
	
	
	private static boolean isNumericParameter(String parameter) {
		Pattern pattern = Pattern.compile(NUMERIC_REGEX);
		Matcher matcher = pattern.matcher(parameter);
		return matcher.matches();
		}
	
}
