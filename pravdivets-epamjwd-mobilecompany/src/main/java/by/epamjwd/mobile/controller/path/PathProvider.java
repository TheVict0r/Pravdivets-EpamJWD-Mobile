package by.epamjwd.mobile.controller.path;

import java.util.HashMap;
import java.util.Map;

public class PathProvider {

	Map<String, String> repository = new HashMap<>();

	public PathProvider() {
		
		repository.put("main", "WEB-INF/jsp/main.jsp");
		repository.put("article", "WEB-INF/jsp/article.jsp");
		repository.put("all_news", "WEB-INF/jsp/allnews.jsp");
		repository.put("login", "WEB-INF/jsp/login.jsp");
		repository.put("abonent", "WEB-INF/jsp/abonent.jsp");
		repository.put("consultant", "WEB-INF/jsp/consultant.jsp");
		repository.put("admin", "WEB-INF/jsp/admin.jsp");
		repository.put("all_plans", "WEB-INF/jsp/allplans.jsp");
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
