package by.epamjwd.mobile.util;

import by.epamjwd.mobile.service.validation.InputDataValidator;

public class PhoneFormatter {

	/**
	 * Transforms input String value of a raw 9-digit phone number
	 * into more visually readable phone number with parentheses and hyphens.
	 * 
	 * 
	 * <p>For example input number <b>291234267</b> will be transformed into <b>(29) 123-42-67</b>. 
	 * <p>
	 * Before the transformation the {@code phone} parameter will be checked by 
	 * {@link InputDataValidator#isPhone(String)}
	 * is whether it meets the necessary criteria to be a phone number 
	 * 
	 * @param   phone   raw 9-digit phone number
	 * @return        formatted hone number with parentheses and hyphens 
	 * if input string meets the necessary criteria to be a phone number,
	 * otherwise returns an empty string ("")
	 */
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
