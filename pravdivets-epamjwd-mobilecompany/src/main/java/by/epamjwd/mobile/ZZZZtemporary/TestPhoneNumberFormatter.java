package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneNumberFormatter;

public class TestPhoneNumberFormatter {
public static void main(String[] args) {
	
	String goodPhoneNumber25 = "258901235";
	String goodPhoneNumber29 = "295678901";
	String goodPhoneNumber33 = "332345678";
	String goodPhoneNumber44 = "449012345";
	String goodPhoneNumber55 = "1290123235";

	
	PhoneNumberFormatter formatter = new PhoneNumberFormatter();
	try {
	System.out.println(formatter.formatPhomeNumber(goodPhoneNumber25));
	System.out.println(formatter.formatPhomeNumber(goodPhoneNumber29));
	System.out.println(formatter.formatPhomeNumber(goodPhoneNumber33));
	System.out.println(formatter.formatPhomeNumber(goodPhoneNumber44));
	System.out.println(formatter.formatPhomeNumber(goodPhoneNumber55));
	} catch (ServiceException e) {
		System.out.println("Но шо-та пошло не так!");
	}
	
}
}
