package by.epamjwd.mobile.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.dao.impl.SQLNewsDAO;
import by.epamjwd.mobile.service.NewsService;
import by.epamjwd.mobile.service.ServiceProvider;

public class FullArticleCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		ServiceProvider provider = ServiceProvider.getInstance();
		NewsService newsService = provider.getNewsService();

		String idString = request.getParameter("id");
		int id = Integer.parseInt(idString);

		NewsArticle article = newsService.getArticleByID(id);

		request.setAttribute("article", article);

	}

}
