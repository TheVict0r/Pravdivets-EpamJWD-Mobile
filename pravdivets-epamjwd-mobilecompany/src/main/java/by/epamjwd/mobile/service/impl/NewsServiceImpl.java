package by.epamjwd.mobile.service.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.controller.repository.NewsIdx;
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
	public List<NewsArticle> buildNewsBatch(int firstIdx, int lastIdxExcluded) throws ServiceException {
		List<NewsArticle> newsBatch = findAllNews().subList(firstIdx, lastIdxExcluded);
		return newsBatch;
	}
	
	@Override
	public Optional<NewsArticle> findArticleByID(long id) throws ServiceException {
		try {
			return newsDao.getArticleByID(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isNextIdxAvailable(int previousIdx) throws ServiceException {
		return findAllNews().size() > previousIdx;
	}

	@Override
	public int getNextIdxExcluded(int lastIdxOld, int step) throws ServiceException {
		int result;
		int maxIdxAvailable = findAllNews().size();

		if (maxIdxAvailable >= lastIdxOld + step) {
				result = lastIdxOld + step;
		} else {
			result = maxIdxAvailable;
		}
		return result;
	}

	
	@Override
	public int getLastIdx(int currentIdx) {
		int lastIdx = currentIdx - (currentIdx % NewsIdx.STEP);
		return lastIdx;
	}

	
	
	@Override
	public int getFirstIdx(int lastIdx) {
		int firstIdx = lastIdx - NewsIdx.STEP;
		if(firstIdx < NewsIdx.FIRST_IDX_GLOBAL) {
			firstIdx = NewsIdx.FIRST_IDX_GLOBAL;
		}
		return firstIdx;
	}

}
