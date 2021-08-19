package by.epamjwd.mobile.dao.connectionpool;

import java.util.ResourceBundle;

public class DBResourceManager {
	
	private final static DBResourceManager instance = new DBResourceManager();
	private ResourceBundle bundle;	
	
	public static DBResourceManager getInstance() {
		return instance;
	}
	
	public void initBundle(String baseName) {
		bundle = ResourceBundle.getBundle(baseName);
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
}