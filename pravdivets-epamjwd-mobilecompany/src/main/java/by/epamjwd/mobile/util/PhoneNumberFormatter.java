package by.epamjwd.mobile.util;

import by.epamjwd.mobile.service.exception.ServiceException;

public class PhoneNumberFormatter {

	public String formatPhomeNumber(int phoneNumber) throws ServiceException {
		
		char [] phoneChar = String.valueOf(phoneNumber).toCharArray();
		
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
	
}
