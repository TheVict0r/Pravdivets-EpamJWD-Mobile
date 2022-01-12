package by.epamjwd.mobile.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.controller.repository.NewsIdx;
import by.epamjwd.mobile.dao.ArticleDAO;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class ArticleServiceImpl implements ArticleService {
	private final static long ERROR_ID = -1L;
	private final static long EMPTY_ID = 0L;
	DAOProvider provider = DAOProvider.getInstance();
	ArticleDAO articleDao = provider.getNewsDao();

	@Override
	public List<Article> findAllArticles() throws ServiceException {
		try {
			return articleDao.getAllArticles();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Article> buildArticlesBatch(int firstIdx, int lastIdxExcluded) throws ServiceException {
		List<Article> articlesBatch = findAllArticles().subList(firstIdx, lastIdxExcluded);
		return articlesBatch;
	}
	
	@Override
	public Optional<Article> findArticleByID(long id) throws ServiceException {
		try {
			return articleDao.getArticleByID(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isNextIdxAvailable(int previousIdx) throws ServiceException {
		return findAllArticles().size() > previousIdx;
	}

	@Override
	public int getNextIdxExcluded(int lastIdxOld, int step) throws ServiceException {
		int result;
		int maxIdxAvailable = findAllArticles().size();

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

	@Override
	public Article buildArticle(String title, String lead, String text) {
		Article article = new Article(EMPTY_ID, new Date(), title, lead, text);
		return article;
	}

	@Override
	public long addArticle(Article article) throws ServiceException {
		long articleId = ERROR_ID;
		if (InputDataValidator.isArticleValid(article)) {
			try {
				articleId = articleDao.addArticle(article);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return articleId;
	}

	@Override
	public boolean isArticleExists(String title) throws ServiceException {
		Optional<Article> articleOptional = Optional.empty();
		try {
			articleOptional = articleDao.getArticleByTitle(title);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return articleOptional.isPresent();
	}

	@Override
	public Optional<Article> findArticleByTitle(String title) throws ServiceException{
		Optional<Article> articleOptional = Optional.empty();
		try {
			articleOptional = articleDao.getArticleByTitle(title);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return articleOptional;
	}

}
