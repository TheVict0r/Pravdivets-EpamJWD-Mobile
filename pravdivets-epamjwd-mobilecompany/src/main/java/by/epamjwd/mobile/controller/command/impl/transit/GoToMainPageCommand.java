package by.epamjwd.mobile.controller.command.impl.transit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.controller.RouteHelper;
import by.epamjwd.mobile.controller.RouteMethod;
import by.epamjwd.mobile.controller.command.Command;
import by.epamjwd.mobile.controller.repository.AttributeName;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.exception.ServiceException;

public class GoToMainPageCommand implements Command{

	private final static Logger LOGGER = LogManager.getLogger(GoToMainPageCommand.class);
	
	@Override
	public RouteHelper execute(HttpServletRequest request, HttpServletResponse response) {
		PlanService planService = ServiceProvider.getInstance().getPlanService();
		ArticleService newsService = ServiceProvider.getInstance().getArticleService();		
		
		try {
			List<Plan> allPlans = planService.findAllPlans();
			request.setAttribute(AttributeName.ALL_PLANS, allPlans);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain all tariff plans list. ", e);
			return RouteHelper.ERROR_500;
		}
		
		try {
			List<Article> newsList = newsService.findAllArticles();
			request.setAttribute(AttributeName.ALL_ARTICLES, newsList);
		} catch (ServiceException e) {
			LOGGER.error("Unable to obtain news list. ", e);
			return RouteHelper.ERROR_500;
		}

		return new RouteHelper(PagePath.MAIN, RouteMethod.FORWARD);
	}

}
