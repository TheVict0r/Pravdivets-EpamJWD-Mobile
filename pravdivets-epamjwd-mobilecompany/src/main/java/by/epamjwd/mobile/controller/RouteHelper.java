package by.epamjwd.mobile.controller;

public class RouteHelper {

	
	
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
