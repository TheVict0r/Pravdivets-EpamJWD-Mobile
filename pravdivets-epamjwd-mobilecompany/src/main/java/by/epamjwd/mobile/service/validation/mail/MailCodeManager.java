package by.epamjwd.mobile.service.validation.mail;

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

import java.util.Properties;

public class MailCodeManager {

	final static int CODE_MIN_VALUE = 1000;
	final static int CODE_MAX_VALUE = 9999;

	private final static Logger LOGGER = LogManager.getLogger();

	public void sendAuthenticationCodeByMail(String usersMail, String authenticationCode) {

		MailResourceManager emailResourceManager = MailResourceManager.getInstance();

		String host = emailResourceManager.getValue(MailParameter.MAIL_HOST);
		String port = emailResourceManager.getValue(MailParameter.MAIL_PORT);
		String auth = emailResourceManager.getValue(MailParameter.MAIL_AUTH);
		String tls = emailResourceManager.getValue(MailParameter.MAIL_TLS);

		Properties prop = new Properties();
		prop.put("mail.smtp.host", host);
		prop.put("mail.smtp.port", port);
		prop.put("mail.smtp.auth", auth);
		prop.put("mail.smtp.starttls.enable", tls);

		String emailFrom = emailResourceManager.getValue(MailParameter.MAIL_FROM);
		String password = emailResourceManager.getValue(MailParameter.MAIL_PASSWORD);

		Session session = Session.getInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailFrom, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(emailFrom));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usersMail));
			message.setSubject("mobile - access code");
			message.setText(authenticationCode);

			Transport.send(message);

		} catch (MessagingException e) {
			LOGGER.error("Error while sending an authentication code to " + usersMail + e);
		}
	}

	public String generateAuthenticationCode() {
		String authenticationCode = "";

		Integer codeInt = (int) (Math.random() * (CODE_MAX_VALUE - CODE_MIN_VALUE + 1) + CODE_MIN_VALUE);

		authenticationCode = codeInt.toString();
		return authenticationCode;
	}

}
