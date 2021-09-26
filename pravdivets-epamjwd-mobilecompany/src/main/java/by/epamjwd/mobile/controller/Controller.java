package by.epamjwd.mobile.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.CommandProvider;
import by.epamjwd.mobile.controller.path.PathRepository;
import by.epamjwd.mobile.controller.path.Routing;

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

		Routing routing = command.execute(request, response);

		String path = routing.getPath();
		String action = routing.getAction().toString();

		switch (action) {
		case "FORWARD":
			request.getRequestDispatcher(path).forward(request, response);
			break;
		case "REDIRECT":
			response.sendRedirect(request.getContextPath() + path);
			break;
        default:
            request.getRequestDispatcher(PathRepository.ERROR_404).forward(request, response);
		}
	}
}
