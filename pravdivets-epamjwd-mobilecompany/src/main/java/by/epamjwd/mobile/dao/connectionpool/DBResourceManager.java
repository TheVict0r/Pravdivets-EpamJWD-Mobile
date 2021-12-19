package by.epamjwd.mobile.dao.connectionpool;

import java.util.ResourceBundle;

public class DBResourceManager {
	
	private ResourceBundle bundle;	
	
	
	private DBResourceManager() {
	}
	
	private static class Holder{
		static final DBResourceManager instance = new DBResourceManager();
	}
	
	
	public static DBResourceManager getInstance() {
		return Holder.instance;
	}
	
	public void initBundle(String baseName) {
		bundle = ResourceBundle.getBundle(baseName);
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
}