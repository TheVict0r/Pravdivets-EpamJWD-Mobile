package by.epamjwd.mobile.temporary;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.dao.impl.SQLNewsDAO;

public class NewsProviderTest {

	public void provideNews(HttpServletRequest request, HttpServletResponse response, String page)
			throws ServletException, IOException {

		SQLNewsDAO newsMaker = new SQLNewsDAO();
		List<NewsArticle> list = newsMaker.getAllNews();

		request.setAttribute("news", list);
		request.getRequestDispatcher(page).forward(request, response);

	}
}