package by.epamjwd.mobile.controller.path;

public class Routing {

	
	
	/**
     * Path to resulting resource.
     */
    private final String path;
	
	
	/**
     * Type of action. Either forwarding or redirection.
     */
	private final RoutingMethod routingMethod;

	public Routing(String path, RoutingMethod routingMethod) {
		this.path = path;
		this.routingMethod = routingMethod;
	}

	public String getPath() {
		return path;
	}

	public RoutingMethod getRoutingMethod() {
		return routingMethod;
	}
	
	
	
	
}
