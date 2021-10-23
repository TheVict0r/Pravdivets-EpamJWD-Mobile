package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneFormatter;

public class TestPhoneNumberFormatter {
public static void main(String[] args) {
	
	String goodPhoneNumber25 = "258901235";
	String goodPhoneNumber29 = "295678901";
	String goodPhoneNumber33 = "332345678";
	String goodPhoneNumber44 = "449012345";
	String goodPhoneNumber55 = "1290123235";

	
	PhoneFormatter formatter = new PhoneFormatter();
	try {
	System.out.println(formatter.formatPhone(goodPhoneNumber25));
	System.out.println(formatter.formatPhone(goodPhoneNumber29));
	System.out.println(formatter.formatPhone(goodPhoneNumber33));
	System.out.println(formatter.formatPhone(goodPhoneNumber44));
	System.out.println(formatter.formatPhone(goodPhoneNumber55));
	} catch (ServiceException e) {
		System.out.println("Но шо-та пошло не так!");
	}
	
}
}
