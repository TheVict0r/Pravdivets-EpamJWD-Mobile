package by.epamjwd.mobile.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class PhoneFormatter {

	public static String formatPhone(String phone) {
		String result = "";
		
		if(InputDataValidator.isPhone(phone)) {
			char [] phoneChar = phone.toCharArray();
			result = new StringBuilder()
				.append("(")
				.append(phoneChar[0]).append(phoneChar[1])
				.append(") ")
				.append(phoneChar[2]).append(phoneChar[3]).append(phoneChar[4])
				.append("-")
				.append(phoneChar[5]).append(phoneChar[6])
				.append("-")
				.append(phoneChar[7]).append(phoneChar[8])
				.toString();
		}
		return result; 
	}
	
	
	
}
