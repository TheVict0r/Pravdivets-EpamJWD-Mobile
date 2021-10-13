package by.epamjwd.mobile.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.service.exception.ServiceException;

public class PhoneNumberFormatter {

	public static String formatPhomeNumber(String phoneNumber) throws ServiceException {
		
		char [] phoneChar = phoneNumber.toCharArray();
		
		if(phoneChar.length !=9) {
			throw new ServiceException("the number provided must be 9 digits long only. Cuttent phone number is - " + phoneNumber);
		}
		
		return new StringBuilder()
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
	
	public Map<String, String> provideFormattedPhoneNumbersMap(List<Abonent> userList) throws ServiceException{
		
		Map<String, String> phoneNumbersMap = new HashMap<>();
		
		for(Abonent abonent : userList) {
			String key = String.valueOf(abonent.getPhoneNumber());
			String value = formatPhomeNumber(key);
			phoneNumbersMap.put(key, value);
		}
		
		return phoneNumbersMap;
	}
	
	
}
