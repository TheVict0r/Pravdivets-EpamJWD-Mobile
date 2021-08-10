package by.epamjwd.mobile.controller.path;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.controller.path.command.Command;
import by.epamjwd.mobile.controller.path.command.CommandProvider;
import by.epamjwd.mobile.dao.impl.SQLNewsDAO;
import by.epamjwd.mobile.temporary.NewsProviderTest;

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

		String commandName = request.getParameter("command");
		CommandProvider commandProvider = new CommandProvider();
		Command command = commandProvider.getCommand(commandName);
		command.execute(request, response);
		
		String path = request.getParameter("path");
		PathProvider pathProvider = new PathProvider();
		String realPath = pathProvider.getPath(path);
		
		
		request.getRequestDispatcher(realPath).forward(request, response);
		
		

	}

}
