package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.path.RoutingMethod;
import by.epamjwd.mobile.controller.path.PathRepository;
import by.epamjwd.mobile.controller.path.Routing;
import by.epamjwd.mobile.service.NewsService;
import by.epamjwd.mobile.service.ServiceProvider;

public class FullArticleCommand implements Command {

	@Override
	public Routing execute(HttpServletRequest request, HttpServletResponse response){

		ServiceProvider provider = ServiceProvider.getInstance();
		NewsService newsService = provider.getNewsService();

		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);

		NewsArticle article = newsService.getArticleByID(id);

		request.setAttribute("article", article);

		Routing result = new Routing(PathRepository.ARTICLE, RoutingMethod.FORWARD);
		return result;
	}

}
