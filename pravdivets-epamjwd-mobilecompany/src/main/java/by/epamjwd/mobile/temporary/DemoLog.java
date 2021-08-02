package by.epamjwd.mobile.temporary;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DemoLog {

	private final static Logger LOGGER = LogManager.getLogger(DemoLog.class);

	public static void main(final String... args) {
		try {
			factorial(9);
			factorial(-3);
		} catch (IllegalArgumentException e) {
			LOGGER.error("negative argument: ", e);
		}
	}

	public static int factorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("argument " + n + " less than zero");
		}
		LOGGER.debug("Argument n is " + n);
		int result = 1;
		for (int i = n; i >= 1; i--) {
			result *= i;
		}
		LOGGER.info("Result is " + result);
		return result;
	}
}
