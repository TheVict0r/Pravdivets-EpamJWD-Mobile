package by.epamjwd.mobile.controller.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.command.helpers.ArticleCommandHelper;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class AddArticleCommand implements Command {
	private final static Logger LOGGER = LogManager.getLogger(AddArticleCommand.class);
	private final static long ERROR_ID = -1L;
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		RouteHelper result = RouteHelper.ERROR;
		String title = request.getParameter(ParameterName.TITLE);
		String intro  = request.getParameter(ParameterName.INTRO);
		String text  = request.getParameter(ParameterName.TEXT);
		HttpSession session = request.getSession();
		ArticleService articleService = ServiceProvider.getInstance().getArticleService();
		long articleID = ERROR_ID;
		
		if(title == null || title.isBlank() || intro == null 
				|| intro.isBlank() || text == null || text.isBlank()) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.ADD_ARTICLE_REDIRECT, RouteMethod.REDIRECT);
		}
		
		try {
			if (articleService.isArticleExists(title)) {
				session.setAttribute(AttributeName.ERROR, AttributeValue.ARTICLE_EXISTS);
				session.setAttribute(AttributeName.TITLE, title);
				session.setAttribute(AttributeName.INTRO, intro);
				session.setAttribute(AttributeName.TEXT, text);
				return new RouteHelper(PagePath.ADD_ARTICLE_REDIRECT, RouteMethod.REDIRECT);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error while checking is service exist by title - " + title, e);
			return RouteHelper.ERROR_500;
		}

		try {
			articleID = articleService.addArticle(articleService.buildArticle(title, intro, text));
			result = ArticleCommandHelper.getInstance().handleArticleByID(session, articleID, PagePath.ARTICLE_ADMIN_REDIRECT, PagePath.ARTICLE_ADMIN, RouteMethod.REDIRECT, LOGGER);
		} catch (ServiceException e) {
			LOGGER.error("Error while adding a new news article, title - " + title, e);
			result = RouteHelper.ERROR_500;
		}
		
		return result;
	}

}
