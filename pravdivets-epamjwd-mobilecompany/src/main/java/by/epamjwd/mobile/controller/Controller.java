package by.epamjwd.mobile.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.dao.impl.SQLNewsDAO;

@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
	}

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String command = request.getParameter("command");
		
		if ("main".equals(command)){
			NewsProvider newsProvider = new NewsProvider();
								
			newsProvider.provideNews(request, response, "main.jsp");
		}
		
		

	}

}
