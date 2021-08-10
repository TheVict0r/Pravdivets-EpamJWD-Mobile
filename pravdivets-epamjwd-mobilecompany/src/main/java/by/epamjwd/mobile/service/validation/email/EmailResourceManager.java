package by.epamjwd.mobile.service.validation.email;

import java.util.ResourceBundle;

public class EmailResourceManager {

	private final static EmailResourceManager instance = new EmailResourceManager();

	public static EmailResourceManager getInstance() {
		return instance;
	}
	
	private ResourceBundle bundle = ResourceBundle.getBundle("email");
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
}