package by.epamjwd.mobile.controller.path;

import java.util.HashMap;
import java.util.Map;

public class PathProvider {

	Map<String, String> repository = new HashMap<>();

	public PathProvider() {
		
		repository.put("main", "WEB-INF/jsp/main.jsp");
		repository.put("article", "WEB-INF/jsp/article.jsp");
	}
	
	public String getPath(String page) {
		String path = "";
		
		path = repository.get(page);
		
		if(path==null) {
			path = "jsp/error.jsp";
		}
		
		return path;
	}
	
	
}
