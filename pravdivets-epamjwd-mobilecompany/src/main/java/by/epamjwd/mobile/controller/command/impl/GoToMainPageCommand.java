package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.CommandProvider;
import by.epamjwd.mobile.controller.repository.CommandName;
import by.epamjwd.mobile.controller.repository.PagePath;

public class GoToMainPageCommand implements Command{

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		CommandProvider commandProvider = new CommandProvider();
		Command plans = commandProvider.getCommand(CommandName.SHOW_ALL_PLANS);
		Command news = commandProvider.getCommand(CommandName.SHOW_ALL_NEWS);
		plans.execute(request, response);
		news.execute(request, response);
		
		RouteHelper result = new RouteHelper(PagePath.MAIN, RouteMethod.FORWARD);
		return result;
	}

}
