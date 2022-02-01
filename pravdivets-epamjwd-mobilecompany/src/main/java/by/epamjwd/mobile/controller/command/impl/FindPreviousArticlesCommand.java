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
import by.epamjwd.mobile.controller.command.NumericParser;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.AttributeValue;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.repository.IndexRepository;
import by.epamjwd.mobile.repository.ListDirection;
import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;


public class FindPreviousArticlesCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(FindAllNewsCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		RouteHelper result = RouteHelper.ERROR;
		ArticleService articleService = ServiceProvider.getInstance().getArticleService();
		HttpSession session = request.getSession();
		ListDirection previousDirection = (ListDirection) session.getAttribute(AttributeName.DIRECTION);
		
		int currentIdx = NumericParser.parseUnsignedIntValue(session.getAttribute(AttributeName.CURRENT_IDX));

		session.removeAttribute(AttributeName.NO_NEXT_NEWS);

		try {	
			int fromIndex = articleService.calculateFromIndex(currentIdx, IndexRepository.STEP, ListDirection.TO_END, previousDirection);
			int toIndex = articleService.calculateToIndex(fromIndex, IndexRepository.STEP, ListDirection.TO_END, previousDirection);

			List<Article> newsBatch = articleService.buildArticlesBatch(fromIndex, toIndex);
				session.setAttribute(AttributeName.NEWS, newsBatch);
				session.setAttribute(AttributeName.CURRENT_IDX, toIndex);
				session.setAttribute(AttributeName.DIRECTION, ListDirection.TO_END);
				if (articleService.isLastIndexExcluded(toIndex)) {
					session.setAttribute(AttributeName.NO_PREVIOUS_NEWS, AttributeValue.TRUE);
					session.setAttribute(AttributeName.CURRENT_IDX, fromIndex);
					session.setAttribute(AttributeName.DIRECTION, ListDirection.TO_BEGINNING);
				}
				result = new RouteHelper(PagePath.ALL_NEWS_REDIRECT, RouteMethod.REDIRECT);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain news list. ", e);
			result = RouteHelper.ERROR_500;
		}

		return result;

	}

}
