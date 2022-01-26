package by.epamjwd.mobile.controller;

import by.epamjwd.mobile.controller.repository.PagePath;

public class RouteHelper {

	public static final RouteHelper ERROR     = new RouteHelper(PagePath.ERROR,     RouteMethod.FORWARD);
	public static final RouteHelper ERROR_404 = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
	public static final RouteHelper ERROR_500 = new RouteHelper(PagePath.ERROR_500, RouteMethod.FORWARD);
	
    private final String path;
	
	private final RouteMethod routeMethod;

	public RouteHelper(String path, RouteMethod routeMethod) {
		this.path = path;
		this.routeMethod = routeMethod;
	}

	public String getPath() {
		return path;
	}

	public RouteMethod getRouteMethod() {
		return routeMethod;
	}
	
}
