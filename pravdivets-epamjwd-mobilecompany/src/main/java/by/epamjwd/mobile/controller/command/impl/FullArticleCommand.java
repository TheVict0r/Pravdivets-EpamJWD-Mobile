package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.repository.AttributeName;
import by.epamjwd.mobile.controller.command.repository.PagePath;
import by.epamjwd.mobile.controller.command.repository.ParameterName;
import by.epamjwd.mobile.service.NewsService;
import by.epamjwd.mobile.service.ServiceProvider;

public class FullArticleCommand implements Command {

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response){

		ServiceProvider provider = ServiceProvider.getInstance();
		NewsService newsService = provider.getNewsService();

		String idString = request.getParameter(ParameterName.ID);
		int id = Integer.parseInt(idString);

		NewsArticle article = newsService.getArticleByID(id);

		request.setAttribute(AttributeName.ARTICLE, article);

		RouteHelper result = new RouteHelper(PagePath.ARTICLE, RouteMethod.FORWARD);
		return result;
	}

}
