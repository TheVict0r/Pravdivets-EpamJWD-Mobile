package by.epamjwd.mobile.controller.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper class, that allows to safely parse numeric data from request or session 
 *
 */
public class NumericParser {

	public static final String NUMERIC_REGEX = "\\d+";
	public static final int INVALID_VALUE = -1;
	
	
	private NumericParser() {
		
	}


	/**
	 * Safely parses positive integer values from the provided object
	 * 
	 * @param objectValue - provided object
	 * 
	 * @return positive integer value, contained in the provided {@code Object}. 
	 * If the Object does not contain real value provides numeric error code (INVALID_VALUE)
	 */
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

	/**
	 * Safely parses positive long values from the provided object
	 * 
	 * @param objectValue - provided object
	 * 
	 * @return positive long value, contained in the provided {@code Object}. 
	 * If the Object does not contain real value provides numeric error code (INVALID_VALUE)
	 */
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
	
	
	/**
	 * Checks if the provided string contains only numeric characters
	 * 
	 * @param parameter - presumably string representation of numeric data
	 * 
	 * @return true if the {@code parameter} can be used for parsing the numeric data
	 */
	private static boolean isNumericParameter(String parameter) {
		Pattern pattern = Pattern.compile(NUMERIC_REGEX);
		Matcher matcher = pattern.matcher(parameter);
		return matcher.matches();
		}
	
}
