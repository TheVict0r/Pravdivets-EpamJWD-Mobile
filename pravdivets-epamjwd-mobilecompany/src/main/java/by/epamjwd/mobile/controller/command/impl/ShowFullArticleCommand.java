package by.epamjwd.mobile.controller.command.impl;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
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
		HttpSession session = request.getSession();

		long id = NumericParser.parseLongValue(request.getParameter(ParameterName.ID));
		if(id == NumericParser.INVALID_VALUE) {
			session.setAttribute(AttributeName.ERROR, AttributeValue.WRONG_DATA);
			return RouteHelper.ERROR_404;
		}

		RouteHelper result = null;

		try {
			Optional<NewsArticle> articleOptional = newsService.findArticleByID(id);
			if(articleOptional.isPresent()) {
				NewsArticle article = articleOptional.get();
				request.setAttribute(AttributeName.ARTICLE, article);
				result = new RouteHelper(PagePath.ARTICLE, RouteMethod.FORWARD);
			} else {
				result = RouteHelper.ERROR_404;
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain news article data for ID -  " + id, e);
			result = RouteHelper.ERROR_500;
		}
		return result;
	}

}
