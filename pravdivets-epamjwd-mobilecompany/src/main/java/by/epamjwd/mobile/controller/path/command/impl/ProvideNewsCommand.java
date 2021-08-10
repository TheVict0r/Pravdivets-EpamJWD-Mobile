package by.epamjwd.mobile.controller.path.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.controller.path.command.Command;
import by.epamjwd.mobile.dao.impl.SQLNewsDAO;

public class ProvideNewsCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		SQLNewsDAO newsMaker = new SQLNewsDAO();
		//ПЕРЕДЕЛАТЬ, ЧТОБЫ НЕ БЫЛО dao
		
		
		List<NewsArticle> list = newsMaker.getAllNews();

		request.setAttribute("news", list);
		
	}

}
