package by.epamjwd.mobile.ZZZZtemporary;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.impl.SQLArticleDAOImpl;

public class NewsProviderTest {

	public void provideNews(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException, DaoException {

		SQLArticleDAOImpl newsMaker = new SQLArticleDAOImpl();
		List<Article> list = newsMaker.getAllArticles();

		request.setAttribute("news", list);
		request.getRequestDispatcher(page).forward(request, response);

	}
}