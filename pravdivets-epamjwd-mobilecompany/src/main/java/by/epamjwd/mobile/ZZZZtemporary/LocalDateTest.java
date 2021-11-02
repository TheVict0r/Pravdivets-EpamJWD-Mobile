package by.epamjwd.mobile.ZZZZtemporary;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class LocalDateTest {
	public static void main(String[] args) {
		
		LocalDate date = LocalDate.now();
		
		System.out.println(date);
		System.out.println(date.getYear());
		
		Calendar calendar = Calendar.getInstance();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(formatter.format(calendar.getTime()));		
		
	}
}
