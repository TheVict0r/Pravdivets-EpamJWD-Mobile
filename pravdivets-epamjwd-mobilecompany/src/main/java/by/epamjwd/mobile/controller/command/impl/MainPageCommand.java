package by.epamjwd.mobile.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.CommandProvider;

public class MainPageCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		CommandProvider commandProvider = new CommandProvider();
		Command plans = commandProvider.getCommand("provide_all_plans");
		Command news = commandProvider.getCommand("provide_news");
		plans.execute(request, response);
		news.execute(request, response);
	}

}
