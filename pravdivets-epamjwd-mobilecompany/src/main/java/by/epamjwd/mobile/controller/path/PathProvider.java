package by.epamjwd.mobile.controller.path;

import java.util.HashMap;
import java.util.Map;

public class PathProvider {

	Map<String, String> repository = new HashMap<>();

	public PathProvider() {
		
		repository.put("main", "main.jsp");
	}
	
	public String getPath(String page) {
		String path = "";
		
		path = repository.get(page);
		
		if(path==null) {
			path = "error.jsp";
		}
		
		return path;
	}
	
	
}
