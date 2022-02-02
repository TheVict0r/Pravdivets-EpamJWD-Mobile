package by.epamjwd.mobile.controller.command;

/**
 * Helper class, that allows to safely parse numeric data from request or session 
 *
 */
public class NumericParser {
	
	public static final int INVALID_VALUE = -1;
	
	private NumericParser() {
		
	}


	/**
	 * Safely parses positive integer values from the provided object
	 * 
	 * @param objectValue - provided object
	 * 
	 * @return positive integer value, contained in the provided {@code Object}. 
	 * If the Object does not contain real positive value provides numeric error code (INVALID_VALUE)
	 */
	public static int parseUnsignedIntValue(Object objectValue) {
		String stringValue = String.valueOf(objectValue);
		int intValue;
		try {
			intValue = Integer.parseInt(stringValue);
			if (intValue < 0) {
				intValue = INVALID_VALUE;
			}
		} catch (NumberFormatException e) { //hopefully this catch of unhandled RuntimeExeption is 
			intValue = INVALID_VALUE;
		}
		return intValue;
	}

	/**
	 * Safely parses positive long values from the provided object
	 * 
	 * @param objectValue - provided object
	 * 
	 * @return positive long value, contained in the provided {@code Object}. 
	 * If the Object does not contain real positive value provides numeric error code (INVALID_VALUE)
	 */
	public static long parseUnsignedLongValue(Object objectValue) {
		String stringValue = String.valueOf(objectValue);
		long longValue;
		try {
			longValue = Long.parseLong(stringValue);
			if (longValue < 0) {
				longValue = INVALID_VALUE;
			}
		} catch (NumberFormatException e) {
			longValue = INVALID_VALUE;
		}
		return longValue;
	}	
	
	
}
