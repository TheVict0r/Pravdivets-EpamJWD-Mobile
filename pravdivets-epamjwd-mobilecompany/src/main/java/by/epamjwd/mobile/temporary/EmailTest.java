package by.epamjwd.mobile.temporary;

import by.epamjwd.mobile.service.validation.mail.MailCodeManager;

public class EmailTest {
	public static void main(String[] args) {

		MailCodeManager codeSender = new MailCodeManager();
		String code = codeSender.generateAuthenticationCode();

		codeSender.sendAuthenticationCodeByEmail("vpravd1@gmail.com", code);
		
	}
}
