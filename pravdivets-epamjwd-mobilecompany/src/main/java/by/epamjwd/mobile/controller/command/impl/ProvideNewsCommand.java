package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.NewsService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ProvideNewsCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ProvideNewsCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		ServiceProvider provider = ServiceProvider.getInstance();
		NewsService newsService = provider.getNewsService();
		RouteHelper result = null;
		try {
			List<NewsArticle> newsList = newsService.getAllNews();
			request.setAttribute(AttributeName.NEWS, newsList);
			result = new RouteHelper(PagePath.ALL_NEWS, RouteMethod.FORWARD);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain news list. ", e);
			result = new RouteHelper(PagePath.ERROR_404, RouteMethod.FORWARD);
		}
		return result;
	}
}
