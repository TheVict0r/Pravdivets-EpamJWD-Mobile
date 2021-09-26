package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.path.Action;
import by.epamjwd.mobile.controller.path.PathRepository;
import by.epamjwd.mobile.controller.path.Routing;
import by.epamjwd.mobile.service.NewsService;
import by.epamjwd.mobile.service.ServiceProvider;


public class ProvideNewsCommand implements Command {

	@Override
	public Routing execute(HttpServletRequest request, HttpServletResponse response){
		
		ServiceProvider provider = ServiceProvider.getInstance();
		NewsService newsService = provider.getNewsService();
		
		List<NewsArticle> list = newsService.getAllNews();

		request.setAttribute("news", list);
	
		Routing result = new Routing(PathRepository.ALL_NEWS, Action.FORWARD);
		return result;

		
	}

}
