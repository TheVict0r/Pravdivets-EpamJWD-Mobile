package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.CommandProvider;
import by.epamjwd.mobile.controller.path.RoutingMethod;
import by.epamjwd.mobile.controller.path.PathRepository;
import by.epamjwd.mobile.controller.path.Routing;

public class MainPageCommand implements Command{

	@Override
	public Routing execute(HttpServletRequest request, HttpServletResponse response) {
		CommandProvider commandProvider = new CommandProvider();
		Command plans = commandProvider.getCommand("provide_all_plans");
		Command news = commandProvider.getCommand("provide_news");
		plans.execute(request, response);
		news.execute(request, response);
		
		Routing result = new Routing(PathRepository.MAIN, RoutingMethod.FORWARD);
		return result;
	}

}
