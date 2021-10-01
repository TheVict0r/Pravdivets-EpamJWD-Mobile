package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.NewsService;
import by.epamjwd.mobile.service.ServiceProvider;


public class ProvideNewsCommand implements Command {

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response){
		
		ServiceProvider provider = ServiceProvider.getInstance();
		NewsService newsService = provider.getNewsService();
		
		List<NewsArticle> newsList = newsService.getAllNews();

		request.setAttribute(AttributeName.NEWS, newsList);
	
		RouteHelper result = new RouteHelper(PagePath.ALL_NEWS, RouteMethod.FORWARD);
		return result;

		
	}

}
