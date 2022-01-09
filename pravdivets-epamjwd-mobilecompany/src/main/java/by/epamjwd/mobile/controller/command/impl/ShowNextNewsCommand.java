package by.epamjwd.mobile.controller.command.impl;

import java.util.List;

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
import by.epamjwd.mobile.controller.repository.NewsIdx;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.NewsService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ShowNextNewsCommand implements Command {

	private final static Logger LOGGER = LogManager.getLogger(ShowAllNewsCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		RouteHelper result = RouteHelper.ERROR;
		NewsService newsService = ServiceProvider.getInstance().getNewsService();
		HttpSession session = request.getSession();
		
		int lastIdx = NumericParser.parseIntValue(session.getAttribute(AttributeName.CURRENT_IDX));
		int firstIdx = newsService.getFirstIdx(lastIdx);
		

		session.removeAttribute(AttributeName.NO_PREVIOUS_NEWS);
		
		if(lastIdx == NumericParser.INVALID_VALUE) {
			firstIdx = NewsIdx.FIRST_IDX_GLOBAL;
			lastIdx = firstIdx + NewsIdx.STEP;
			session.setAttribute(AttributeName.NO_PREVIOUS_NEWS, AttributeValue.TRUE);
		}

		try {
			List<NewsArticle> newsBatch = newsService.buildNewsBatch(firstIdx, lastIdx);
			session.setAttribute(AttributeName.NEWS, newsBatch);
			session.setAttribute(AttributeName.CURRENT_IDX, lastIdx);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain news list. ", e);
			result = RouteHelper.ERROR_500;
		}

		result = new RouteHelper(PagePath.ALL_NEWS_REDIRECT, RouteMethod.REDIRECT);
	return result;
	}

}
