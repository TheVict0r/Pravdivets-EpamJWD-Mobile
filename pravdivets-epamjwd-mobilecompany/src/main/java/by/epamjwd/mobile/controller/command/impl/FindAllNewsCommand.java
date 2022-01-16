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
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.repository.IndexRepository;
import by.epamjwd.mobile.repository.ListDirection;
import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class FindAllNewsCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(FindAllNewsCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		ArticleService newsService = ServiceProvider.getInstance().getArticleService();
		RouteHelper result = RouteHelper.ERROR;
		HttpSession session = request.getSession();
		session.removeAttribute(AttributeName.DIRECTION);
		session.setAttribute(AttributeName.NO_NEXT_NEWS, AttributeValue.TRUE);
		session.removeAttribute(AttributeName.NO_PREVIOUS_NEWS);
		session.setAttribute(AttributeName.DIRECTION, ListDirection.TO_END);

		try {
			int toIndex = newsService.calculateToIndex(IndexRepository.ZERO_INDEX, IndexRepository.STEP, ListDirection.TO_END, ListDirection.TO_END);
	
		List<Article> newsBatch = newsService.buildArticlesBatch(IndexRepository.ZERO_INDEX, toIndex );
		session.setAttribute(AttributeName.NEWS, newsBatch);
		session.setAttribute(AttributeName.CURRENT_IDX, toIndex);
		result = new RouteHelper(PagePath.ALL_NEWS_REDIRECT, RouteMethod.REDIRECT);
	} catch (ServiceException e) {
		LOGGER.error("Unable to obtain news list. ", e);
		result = RouteHelper.ERROR_500;
	}
	return result;

	}
}
