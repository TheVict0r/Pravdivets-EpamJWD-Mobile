package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneFormatter;

public class TestPhoneNumberFormatter {
public static void main(String[] args) {
	
	String goodPhoneNumber25 = "258901235";
	String goodPhoneNumber29 = "295678901";
	String goodPhoneNumber33 = "332345678";
	String goodPhoneNumber44 = "449012345";
	String badPhoneNumber55 = "1290123235";

	try {
		System.out.println(PhoneFormatter.formatPhone(goodPhoneNumber25));
		System.out.println(PhoneFormatter.formatPhone(goodPhoneNumber29));
		System.out.println(PhoneFormatter.formatPhone(goodPhoneNumber33));
		System.out.println(PhoneFormatter.formatPhone(goodPhoneNumber44));
		System.out.println(PhoneFormatter.formatPhone(badPhoneNumber55));
	} catch (ServiceException e) {
		System.out.println("Но шо-та пошло не так!");
	}
	
}
}
