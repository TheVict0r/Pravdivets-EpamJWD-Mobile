package by.epamjwd.mobile.controller.command.helpers;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ArticleCommandHelper {

	private ArticleCommandHelper() {
	}
	
	public static ArticleCommandHelper getInstance() {
		return Holder.INSTANCE;
	}

	public RouteHelper handleArticleByID(HttpSession session, long articleID, String pagePath,
			Logger logger) {
	RouteHelper result = null;
	ArticleService articleService = ServiceProvider.getInstance().getArticleService();
	try {
		Optional<Article> articleOptional = articleService.findArticleByID(articleID);
		if(articleOptional.isPresent()) {
			Article article = articleOptional.get();
			session.setAttribute(AttributeName.ARTICLE, article);
			result = new RouteHelper(pagePath, RouteMethod.FORWARD);
		} else {
			result = RouteHelper.ERROR_404;
		}
	} catch (ServiceException e) {
		logger.error("Unable to obtain news article data for ID -  " + articleID, e);
		result = RouteHelper.ERROR_500;
	}
	return result;
}
	
	
	
	
	private static class Holder{
		private final static ArticleCommandHelper INSTANCE = new ArticleCommandHelper();
	}
	
	
	
}
