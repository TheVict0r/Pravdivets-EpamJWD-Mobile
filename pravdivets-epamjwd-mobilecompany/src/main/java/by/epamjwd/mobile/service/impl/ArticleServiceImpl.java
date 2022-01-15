package by.epamjwd.mobile.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.controller.repository.IndexRepository;
import by.epamjwd.mobile.dao.ArticleDAO;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.ArticleService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

/**
 * Class provides the operations with news articles
 */
public class ArticleServiceImpl implements ArticleService {

	DAOProvider provider = DAOProvider.getInstance();
	ArticleDAO articleDao = provider.getNewsDao();

	/**
	 * Provides all actual news articles currently exists.
	 * 
	 * @return Array List containing all news articles from the data storage
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting all news articles from the data storage
	 */
	@Override
	public List<Article> findAllArticles() throws ServiceException {
		try {
			return articleDao.getAllArticles();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	
	/**
	 * Provides news article retrieved by it's ID.
	 * 
	 * @param id - ID of news article
	 * @return news article as an Optional value
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting news article from the data storage
	 */
	@Override
	public Optional<Article> findArticleByID(long id) throws ServiceException {
		try {
			return articleDao.getArticleByID(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Provides news article retrieved by it's title.
	 * 
	 * @param id - title of news article
	 * @return news article as an Optional value
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting news article from the data storage
	 */
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
	
	/**
	 * Builds news article with empty ID.
	 * 
	 * @param title - the title of news article
	 * @param intro - the short introduction to article
	 * @param text - the main text of the article
	 * @return news article
	 */
	@Override
	public Article buildArticle(String title, String intro, String text) {
		Article article = new Article(IndexRepository.EMPTY_ID, new Date(), title, intro, text);
		return article;
	}

	/**
	 * Adds news article to data storage.
	 * 
	 * @param article - news article to add
	 * @return the ID of news article in data storage
	 * @throws ServiceException in the case when DaoException occurs while saving  
	 * news article to the data storage
	 */
	@Override
	public long addArticle(Article article) throws ServiceException {
		long articleId = IndexRepository.ERROR_ID;
		if (InputDataValidator.isArticleValid(article)) {
			try {
				articleId = articleDao.addArticle(article);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return articleId;
	}

	/**
	 * Checks the existence of news article by it's {@code name}.
	 * 
	 * @param name  the name of tariff plan
	 * @throws ServiceException in the case when DaoException occurs while 
	 * getting the tariff plan from the data storage
	 */
	@Override
	public boolean doesArticleExists(String title) throws ServiceException {
		Optional<Article> articleOptional = findArticleByTitle(title);
		return articleOptional.isPresent();
	}



	/**
	 * Extracts sublist of news articles from all articles in the data storage.
	 * Can be used for pagination.
	 * 
	 * @param firstIndex - index of first news article in whole articles list in data storage
	 * @param lastIndexExcluded - index of the article following after the last news article 
	 * 							in whole articles list in data storage
	 * @return - sublist of news articles
	 * @throws ServiceException in the case when DaoException occurs while getting 
	 * all news articles from the data storage
	 */
	@Override
	public List<Article> buildArticlesBatch(int firstIndex, int lastIndexExcluded) throws ServiceException {
		List<Article> articlesBatch = findAllArticles().subList(firstIndex, lastIndexExcluded);
		return articlesBatch;
	}

	
	/**
	 * Checks if the next index available in all articles list.
	 * Used when moving from current to older news articles .
	 * 
	 * @param previousIndex - previous index
	 * @return - {@code true} if there is next index in the list with all news articles
	 * 			 <@code false} if there is no next index in the list with all news articles
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting all news articles from the data storage 
	 */
	@Override
	public boolean isNextIndexAvailableMovingBack(int previousIndex) throws ServiceException {
		return findAllArticles().size() > previousIndex;
	}

	
	/**
	 * Provides the value used as excluded "to-index" for extracting sub-list 
	 * when moving from current to older news articles.
	 * 
	 * @param currentIndex - starting index
	 * @param step - step to define the size of sub-bunch of news articles
	 * @return  "to-index" 
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting all news articles from the data storage  
	 */
	@Override
	public int getNextIndexExcludedMovingBack(int currentIndex, int step) throws ServiceException {
		int result;
		int maxIdxAvailable = findAllArticles().size();

		if (maxIdxAvailable >= currentIndex + step) {
				result = currentIndex + step;
		} else {
			result = maxIdxAvailable;
		}
		return result;
	}

	/**
	 * Provides the value used as excluded "to-index" for extracting sub-list 
	 * when moving from older to current news articles.
	 * 
	 * @param fromIndex - starting index
	 * @param step - step to define the size of sub-bunch of news articles
	 * @return  "to-index" 
	 */
	@Override
	public int getLastIndexMovingForward(int currentIndex, int step) {
		int lastIdx = currentIndex - (currentIndex % step);
		return lastIdx;
	}

	
	/**
	 * Provides the value used as excluded "from-index" for extracting sub-list 
	 * when moving from older to current news articles.
	 * 
	 * @param lastIndex - ending index
	 * @param step - step to define the size of sub-bunch of news articles
	 * @return  "from-index" 
	 */
	@Override
	public int getFirstIndexMovingForward(int lastIndex, int step) {
		int firstIdx = lastIndex - step;
		if(firstIdx < IndexRepository.NULL_INDEX) {
			firstIdx = IndexRepository.NULL_INDEX;
		}
		return firstIdx;
	}

}
