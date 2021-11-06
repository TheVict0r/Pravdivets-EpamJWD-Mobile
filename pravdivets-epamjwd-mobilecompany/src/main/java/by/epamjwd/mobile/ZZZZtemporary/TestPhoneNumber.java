package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.service.validation.InputDataValidator;

public class TestPhoneNumber {
	public static void main(String[] args) {

		String number = "951234567";
		
		System.out.println(InputDataValidator.isPhone(number));
	}

}
