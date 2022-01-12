package by.epamjwd.mobile.controller.command;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.impl.AddArticleCommand;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.controller.repository.ParameterName;
import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class FindArticleByTitleCommand implements Command {
	private final static Logger LOGGER = LogManager.getLogger(AddArticleCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		RouteHelper result = RouteHelper.ERROR;
		HttpSession session = request.getSession();
		ArticleService articleService = ServiceProvider.getInstance().getArticleService();
		String title = request.getParameter(ParameterName.TITLE);

		if(title == null || title.isBlank()) {
			session.setAttribute(AttributeName.WRONG_DATA, AttributeValue.WRONG_DATA);
			return new RouteHelper(PagePath.ARTICLE_OPERATIONS_REDIRECT, RouteMethod.REDIRECT);
		}
		
		try {
			Optional<Article> articleOptional = articleService.findArticleByTitle(title);
			if(articleOptional.isPresent()) {
				Article article = articleOptional.get();
				session.setAttribute(AttributeName.ARTICLE, article);
				result = new RouteHelper(PagePath.ARTICLE_ADMIN_REDIRECT, RouteMethod.REDIRECT);
			} else {
				session.setAttribute(AttributeName.ERROR, AttributeValue.NO_ARTICLE);
				session.setAttribute(AttributeName.TITLE, title);
				result = new RouteHelper(PagePath.ARTICLE_OPERATIONS_REDIRECT, RouteMethod.REDIRECT);
			}
		} catch (ServiceException e) {
			LOGGER.error("Error while trying to find article by title - " + title, e);
			result = RouteHelper.ERROR_500;
		}
		
		
		return result;
	}

}
