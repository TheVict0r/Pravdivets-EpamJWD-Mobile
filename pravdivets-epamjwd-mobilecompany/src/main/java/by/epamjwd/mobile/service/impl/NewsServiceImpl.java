package by.epamjwd.mobile.service.impl;

import java.util.List;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.NewsDAO;
import by.epamjwd.mobile.service.NewsService;

public class NewsServiceImpl implements NewsService {

	DAOProvider provider = DAOProvider.getInstance();
	NewsDAO newsDao = provider.getNewsDao();
	
	@Override
	public List<NewsArticle> getAllNews() {
		List<NewsArticle> result = newsDao.getAllNews();
		return result;
	}

	@Override
	public NewsArticle getArticleByID(int id) {
		NewsArticle result = newsDao.getArticleByID(id);
		return result;
	}

}
