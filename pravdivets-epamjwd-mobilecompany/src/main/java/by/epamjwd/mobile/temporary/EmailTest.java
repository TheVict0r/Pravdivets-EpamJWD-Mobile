package by.epamjwd.mobile.temporary;

import by.epamjwd.mobile.service.validation.email.AuthenticationCodeManager;

public class EmailTest {
	public static void main(String[] args) {

		AuthenticationCodeManager codeSender = new AuthenticationCodeManager();
		String code = codeSender.generateAuthenticationCode();

		codeSender.sendAuthenticationCodeByEmail("vpravd1@gmail.com", code);
		
	}
}
