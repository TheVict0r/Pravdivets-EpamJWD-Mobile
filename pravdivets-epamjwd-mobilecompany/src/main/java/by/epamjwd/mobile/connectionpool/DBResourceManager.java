package by.epamjwd.mobile.connectionpool;

import java.util.ResourceBundle;

public class DBResourceManager {
	
	private final static DBResourceManager instance = new DBResourceManager();

	public static DBResourceManager getInstance() {
		return instance;
	}
	
	private ResourceBundle bundle = ResourceBundle.getBundle("db");
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
}