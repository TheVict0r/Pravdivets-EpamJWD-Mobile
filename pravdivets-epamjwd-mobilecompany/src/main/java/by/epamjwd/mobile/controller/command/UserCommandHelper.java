package by.epamjwd.mobile.controller.command;

public class UserCommandHelper {

	private UserCommandHelper() {
	}
	
	private static class Holder{
		private final static UserCommandHelper INSTANCE = new UserCommandHelper();
	}
	
	public static UserCommandHelper getInstance() {
		return Holder.INSTANCE;
	}
	
	
	
	
	
	
}
