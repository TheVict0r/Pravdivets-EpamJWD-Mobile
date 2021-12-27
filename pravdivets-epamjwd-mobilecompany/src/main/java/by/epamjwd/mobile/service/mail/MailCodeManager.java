package by.epamjwd.mobile.service.mail;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.service.exception.ServiceException;

import java.util.Properties;

public class MailCodeManager {

	final static int CODE_MIN_VALUE =  1000;
	final static int FOUR_DECIMALS = 10_000;
	
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
	
	
	public int sendGenereatedCodeByMail(String userMail) throws ServiceException {
		int code = generateCode(); 
		sendTextByMail(userMail, String.valueOf(code));
		return code;
	}
	
	
	private void sendTextByMail(String usersMail, String text) throws ServiceException {

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
		
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailFrom));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usersMail));
			message.setSubject(MESSAGE_SUBJECT);
			message.setText(text);

			Transport.send(message);

		} catch (MessagingException e) {
			throw new ServiceException(e);
		}
	}

	private int generateCode() {
		int code = (int) (Math.random() * FOUR_DECIMALS);
		if (code < CODE_MIN_VALUE) {
			code += CODE_MIN_VALUE;
		}
		return code;
	}

}
