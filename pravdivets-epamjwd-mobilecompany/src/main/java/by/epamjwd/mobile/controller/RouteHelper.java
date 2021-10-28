package by.epamjwd.mobile.controller;

import by.epamjwd.mobile.controller.repository.PagePath;

public class RouteHelper {

	
	public static final RouteHelper ERROR = new RouteHelper(PagePath.ERROR_REDIRECT, RouteMethod.REDIRECT);
	
	/**
     * Path to resulting resource.
     */
    private final String path;
	
	/**
     * Type of action. Either forwarding or redirection.
     */
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
