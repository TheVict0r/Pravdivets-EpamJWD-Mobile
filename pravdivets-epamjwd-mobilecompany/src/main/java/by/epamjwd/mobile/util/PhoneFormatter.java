package by.epamjwd.mobile.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.service.exception.ServiceException;

public class PhoneFormatter {

	public static String formatPhone(int phone) throws ServiceException {
		
		String phoneString = String.valueOf(phone);
		
		char [] phoneChar = phoneString.toCharArray();
		
		if(phoneChar.length !=9) {
			throw new ServiceException("the number provided must be 9 digits long only. Cuttent phone number is - " + phone);
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
	
	public Map<Integer, String> provideFormattedPhoneMap(List<Subscriber> userList) throws ServiceException{
		
		Map<Integer, String> phoneNumbersMap = new HashMap<>();
		
		for(Subscriber abonent : userList) {
			int key = abonent.getPhone();
			String value = formatPhone(key);
			phoneNumbersMap.put(key, value);
		}
		
		return phoneNumbersMap;
	}
	
	
}
