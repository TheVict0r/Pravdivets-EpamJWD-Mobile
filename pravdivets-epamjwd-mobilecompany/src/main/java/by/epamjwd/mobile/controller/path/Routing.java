package by.epamjwd.mobile.controller.path;

public class Routing {

	
	
	/**
     * Path to resulting resource.
     */
    private final String path;
	
	
	/**
     * Type of action. Either forwarding or redirection.
     */
	private final Action action;

	public Routing(String path, Action action) {
		this.path = path;
		this.action = action;
	}

	public String getPath() {
		return path;
	}

	public Action getAction() {
		return action;
	}
	
	
	
	
}
