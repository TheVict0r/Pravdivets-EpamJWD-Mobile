package by.epamjwd.mobile.service.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.NewsDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.NewsService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class NewsServiceImpl implements NewsService {

	DAOProvider provider = DAOProvider.getInstance();
	NewsDAO newsDao = provider.getNewsDao();

	@Override
	public List<NewsArticle> findAllNews() throws ServiceException {
		try {
			return newsDao.getAllNews();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<NewsArticle> findArticleByID(int id) throws ServiceException {
		try {
			return newsDao.getArticleByID(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
