package by.epamjwd.mobile.service.validation.mail;

import java.util.ResourceBundle;

public class MailResourceManager {

	private final static MailResourceManager instance = new MailResourceManager();

	public static MailResourceManager getInstance() {
		return instance;
	}
	
	private ResourceBundle bundle = ResourceBundle.getBundle("email");
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
}