package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.PhoneFormatter;

public class TestPhoneNumberFormatter {
public static void main(String[] args) {
	
	int goodPhoneNumber25 = 258901235;
	int goodPhoneNumber29 = 295678901;
	int goodPhoneNumber33 = 332345678;
	int goodPhoneNumber44 = 449012345;
	int badPhoneNumber55 = 1290123235;

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
