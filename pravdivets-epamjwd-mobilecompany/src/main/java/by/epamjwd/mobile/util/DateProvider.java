package by.epamjwd.mobile.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateProvider {

	private DateProvider() {
	}

	public static String provideCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = formatter.format(calendar.getTime());	
		return currentDate;
	}

	
}
