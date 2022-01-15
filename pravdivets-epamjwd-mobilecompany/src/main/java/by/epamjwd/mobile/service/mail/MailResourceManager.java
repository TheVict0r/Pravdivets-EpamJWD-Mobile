package by.epamjwd.mobile.service.mail;

import java.util.ResourceBundle;


/**
 * ResourceBundl for MailCodeManager
 */
public class MailResourceManager {

	private static final String MAIL_BUNDLE_NAME = "mail";
	private ResourceBundle bundle = ResourceBundle.getBundle(MAIL_BUNDLE_NAME);
	
	private MailResourceManager() {
	}
	
	private static class Holder{
		private final static MailResourceManager INSTANCE = new MailResourceManager();
		
	}
	
	public static MailResourceManager getInstance() {
		return Holder.INSTANCE;
	}
	
	
	/**
	 * Provides values from ResourceBundle by their keys.
	 * 
	 * @param key - key to ResourceBundle
	 * @return value extracted by key
	 */
	public String getValue(String key) {
		return bundle.getString(key);
	}
}