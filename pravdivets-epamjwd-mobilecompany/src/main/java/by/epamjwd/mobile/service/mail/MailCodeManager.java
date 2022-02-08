package by.epamjwd.mobile.service.mail;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class MailCodeManager {

	final static int ERROR_CODE = -1;
	final static int CODE_MIN_VALUE =  1000;
	final static int CODE_MAX_VALUE =  9999;
	
	final static String HOST_KEY = "mail.smtp.host";
	final static String PORT_KEY = "mail.smtp.port";
	final static String AUTHORIZATION_KEY = "mail.smtp.auth";
	final static String TLS_KEY = "mail.smtp.starttls.enable";
	
	final static String MESSAGE_SUBJECT = "mobile - access code";
	
	private MailCodeManager() {
	}

	private static class Holder{
		static final MailCodeManager INSTANCE = new MailCodeManager();
	}
	
	public static MailCodeManager getInstance() {
		return Holder.INSTANCE;
	}
	
	
	/**
	 * Sends generated code to user's e-mail.
	 * 
	 * @param email - e-mail address
	 * 
	 * @return - sent code, returns ERROR_CODE (-1) if the code wasn't sent
	 * 
	 * @throws ServiceException if MessagingException occurs
	 */
	public int sendGenereatedCodeByMail(String email) throws ServiceException {
		int code = ERROR_CODE;

		if (InputDataValidator.isEmail(email)) {
			code = generateCode();
			sendTextByMail(email, String.valueOf(code));
		}
		return code;
	}

	
	/**
	 * Sends any text to user's e-mail.
	 * 
	 * @param email - e-mail address
	 * 
	 * @param text - any text need to be sent
	 * 
	 * @throws ServiceException if MessagingException occurs
	 */
	private void sendTextByMail(String email, String text) throws ServiceException {

		MailResourceManager mailResourceManager = MailResourceManager.getInstance();

		String host = mailResourceManager.getValue(MailParameter.MAIL_HOST);
		String port = mailResourceManager.getValue(MailParameter.MAIL_PORT);
		String auth = mailResourceManager.getValue(MailParameter.MAIL_AUTH);
		String tls  = mailResourceManager.getValue(MailParameter.MAIL_TLS);

		Properties properties = new Properties();
		properties.put(HOST_KEY, host);
		properties.put(PORT_KEY, port);
		properties.put(AUTHORIZATION_KEY, auth);
		properties.put(TLS_KEY, tls);

		String mailFrom = mailResourceManager.getValue(MailParameter.MAIL_FROM);
		String password = mailResourceManager.getValue(MailParameter.MAIL_PASSWORD);

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailFrom, password);
			}
		});
		
		if (InputDataValidator.isEmail(email)) {
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(mailFrom));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
				message.setSubject(MESSAGE_SUBJECT);
				message.setText(text);

				Transport.send(message);

			} catch (MessagingException e) {
				throw new ServiceException(e);
			}
		}
		
	}

	
	/**
	 * Generates pseudirandom code number
	 * 
	 * @return code number
	 */
	private int generateCode() {
		return new Random().nextInt(CODE_MAX_VALUE - CODE_MIN_VALUE) + CODE_MIN_VALUE;
	}

}
