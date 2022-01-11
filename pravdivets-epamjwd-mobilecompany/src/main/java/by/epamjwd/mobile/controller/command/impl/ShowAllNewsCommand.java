package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.NewsIdx;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowAllNewsCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ShowAllNewsCommand.class);
	
	
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ArticleService newsService = ServiceProvider.getInstance().getArticleService();
		RouteHelper result = RouteHelper.ERROR;
		HttpSession session = request.getSession();
		session.setAttribute(AttributeName.NO_NEXT_NEWS, AttributeValue.TRUE);
		session.removeAttribute(AttributeName.NO_PREVIOUS_NEWS);

		int lastIdx = NewsIdx.FIRST_IDX_GLOBAL + NewsIdx.STEP;
		
		try {
		List<Article> newsBatch = newsService.buildArticlesBatch(NewsIdx.FIRST_IDX_GLOBAL, lastIdx );
		session.setAttribute(AttributeName.NEWS, newsBatch);
		session.setAttribute(AttributeName.CURRENT_IDX, lastIdx);
		result = new RouteHelper(PagePath.ALL_NEWS_REDIRECT, RouteMethod.REDIRECT);
	} catch (ServiceException e) {
		LOGGER.error("Unable to obtain news list. ", e);
		result = RouteHelper.ERROR_500;
	}
	return result;

		
	}
}
