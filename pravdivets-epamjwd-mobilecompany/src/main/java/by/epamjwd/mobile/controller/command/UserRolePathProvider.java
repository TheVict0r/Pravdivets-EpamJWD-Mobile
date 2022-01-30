package by.epamjwd.mobile.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.PagePath;

public class UserRolePathProvider {

	private final Map<Role, RouteHelper> allPaths = new HashMap<>();
	
	private UserRolePathProvider(){
		allPaths.put(Role.ADMIN, new RouteHelper(PagePath.ADMIN_REDIRECT, RouteMethod.REDIRECT));
		allPaths.put(Role.CONSULTANT, new RouteHelper(PagePath.SUBSCRIBER_OPERATIONS_REDIRECT, RouteMethod.REDIRECT));
		allPaths.put(Role.SUBSCRIBER, new RouteHelper(PagePath.SUBSCRIBER_LIST_REDIRECT, RouteMethod.REDIRECT));
	}
	
	public static UserRolePathProvider getInstance() {
		return Holder.INSTANCE;
	}
	

	public RouteHelper providePath(Role role) {
		return allPaths.get(role);
	}
	
	public void addPath(Role role, RouteHelper path) {
		allPaths.put(role, path);
	}
	
	
	private static class Holder{
		private final static UserRolePathProvider INSTANCE = new UserRolePathProvider();
	}
}
