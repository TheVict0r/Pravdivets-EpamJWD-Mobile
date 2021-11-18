package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.mail.MailCodeManager;

public class MailTest {
	public static void main(String[] args) throws ServiceException {

		MailCodeManager codeSender = MailCodeManager.getInstance();

		System.out.println(codeSender.sendGenereatedCodeByMail("vpravd1@gmail.com"));
		
	}
}
