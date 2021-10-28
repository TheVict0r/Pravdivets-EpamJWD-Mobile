package by.epamjwd.mobile.controller.command.impl;

import java.util.NoSuchElementException;
import java.util.Optional;

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
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.NewsService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowFullArticleCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ShowFullArticleCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {

		ServiceProvider provider = ServiceProvider.getInstance();
		NewsService newsService = provider.getNewsService();

		int id = Integer.parseInt(request.getParameter(ParameterName.ID));
		RouteHelper result = null;

		try {
			NewsArticle article = newsService.findArticleByID(id).get();
			request.setAttribute(AttributeName.ARTICLE, article);
			result = new RouteHelper(PagePath.ARTICLE, RouteMethod.FORWARD);
		} catch (ServiceException | NoSuchElementException e) {
			LOGGER.error("Unable to obtain news article data for ID -  " + id, e);
			result = RouteHelper.ERROR;
		}
		return result;
	}

}
