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
import by.epamjwd.mobile.controller.repository.NewsIdx;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class FindPreviousNewsCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(FindAllNewsCommand.class);

	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		RouteHelper result = RouteHelper.ERROR;
		ArticleService newsService = ServiceProvider.getInstance().getArticleService();
		HttpSession session = request.getSession();

		int firstIdx = NumericParser.parseIntValue(session.getAttribute(AttributeName.CURRENT_IDX));
		int lastIdxExcluded;

		session.removeAttribute(AttributeName.NO_NEXT_NEWS);

		if (firstIdx == NumericParser.INVALID_VALUE) {
			firstIdx = NewsIdx.FIRST_IDX_GLOBAL;
			session.setAttribute(AttributeName.NO_NEXT_NEWS, AttributeValue.TRUE);
		}

		try {
			if (newsService.isNextIdxAvailable(firstIdx)) {
				lastIdxExcluded = newsService.getNextIdxExcluded(firstIdx, NewsIdx.STEP);
				List<Article> newsBatch = newsService.buildArticlesBatch(firstIdx, lastIdxExcluded);
				session.setAttribute(AttributeName.NEWS, newsBatch);
				session.setAttribute(AttributeName.CURRENT_IDX, lastIdxExcluded);
				if (!newsService.isNextIdxAvailable(lastIdxExcluded)) {
					session.setAttribute(AttributeName.NO_PREVIOUS_NEWS, AttributeValue.TRUE);
				}
			}
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain news list. ", e);
			result = RouteHelper.ERROR_500;
		}

		
		
		
		
		result = new RouteHelper(PagePath.ALL_NEWS_REDIRECT, RouteMethod.REDIRECT);
		return result;

	}

}
