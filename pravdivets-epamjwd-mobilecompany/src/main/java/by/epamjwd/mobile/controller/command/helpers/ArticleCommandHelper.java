package by.epamjwd.mobile.controller.command.helpers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ArticleCommandHelper {

	private ArticleCommandHelper() {
	}

	public static ArticleCommandHelper getInstance() {
		return Holder.INSTANCE;
	}

	/**
	 * Method for handling the news article by it's ID.
	 * 
	 * Retrieves by ID news article from the data storage and sets it to the session 
	 * 
	 * @param session - http-session
	 * 
	 * @param articleID - the ID of the article
	 * 
	 * @param pagePath - path to page for presenting the result to the client
	 * 
	 * @param routeMethod - method for further processing (forward() or sendRedirect())
	 * 
	 * @param logger - logger to log an errors in the case they occur 
	 * 
	 * @return - RouteHelper containing path to page and route method
	 */
	public RouteHelper handleArticleByID(HttpSession session, long articleID, String pagePath, RouteMethod routeMethod, Logger logger) {
		RouteHelper result = RouteHelper.ERROR;
		ArticleService articleService = ServiceProvider.getInstance().getArticleService();
		try {
			Optional<Article> articleOptional = articleService.findArticleByID(articleID);
			if (articleOptional.isPresent()) {
				Article article = articleOptional.get();
				session.setAttribute(AttributeName.ARTICLE, article);
				result = new RouteHelper(pagePath, routeMethod);
			} else {
				result = RouteHelper.ERROR_404;
			}
		} catch (ServiceException e) {
			logger.error("Unable to obtain news article data for ID -  " + articleID, e);
			result = RouteHelper.ERROR_500;
		}
		return result;
	}

	private static class Holder {
		private final static ArticleCommandHelper INSTANCE = new ArticleCommandHelper();
	}

}
