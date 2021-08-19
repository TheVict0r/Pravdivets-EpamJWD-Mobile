package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.service.validation.mail.MailCodeManager;

public class MailTest {
	public static void main(String[] args) {

		MailCodeManager codeSender = new MailCodeManager();
		String code = codeSender.generateAuthenticationCode();

		codeSender.sendAuthenticationCodeByMail("vpravd1@gmail.com", code);
		
	}
}
