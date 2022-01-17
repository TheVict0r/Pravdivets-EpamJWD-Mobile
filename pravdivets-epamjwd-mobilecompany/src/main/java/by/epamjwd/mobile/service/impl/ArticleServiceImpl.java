package by.epamjwd.mobile.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.dao.ArticleDAO;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.repository.IDRepository;
import by.epamjwd.mobile.repository.IndexRepository;
import by.epamjwd.mobile.repository.ListDirection;
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
	 * 
	 * @throws ServiceException in the case when DaoException 
	 * 			occurs while getting all news articles from the data storage
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
	 * 
	 * @return news article as an Optional value
	 * 
	 * @throws ServiceException in the case when DaoException 
	 * 			occurs while getting news article from the data storage
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
	 * 
	 * @return news article as an Optional value
	 * 
	 * @throws ServiceException in the case when DaoException 
	 * 			occurs while getting news article from the data storage
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
	 * 
	 * @param intro - the short introduction to article
	 * 
	 * @param text - the main text of the article
	 * 
	 * @return news article
	 */
	@Override
	public Article buildArticle(String title, String intro, String text) {
		Article article = new Article(IDRepository.EMPTY_ID, new Date(), title, intro, text);
		return article;
	}

	/**
	 * Adds news article to data storage.
	 * 
	 * @param article - news article to add
	 * 
	 * @return the ID of news article in data storage
	 * 
	 * @throws ServiceException in the case when DaoException occurs while saving  
	 * 			news article to the data storage
	 */
	@Override
	public long addArticle(Article article) throws ServiceException {
		long articleId = IDRepository.ERROR_ID;
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
	 * Checks for the presence of news article by it's {@code name} in the data storage.
	 * 
	 * @param name  the name of tariff plan
	 * 
	 * @throws ServiceException in the case when DaoException occurs while 
	 * 			getting the tariff plan from the data storage
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
	 * @param fromIndex - index of first news article included to batch
	 * 
	 * @param toIndex - index of the article following after the last news article 
	 * 							included to batch
	 * 
	 * @return - sublist of news articles
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting 
	 * 			all news articles from the data storage
	 */
	@Override
	public List<Article> buildArticlesBatch(int fromIndex, int toIndex) throws	ServiceException {
		List<Article> articlesBatch = findAllArticles().subList(fromIndex, toIndex);
		return articlesBatch;
	}

	
	/**
	 * Checks if the provided index is one step beyond the last index in all news articles list.
	 * Used when moving from current to older news articles.
	 * 
	 * @param index - index need to be checked
	 * 
	 * @return - {@code true} if {@code index} is is one step beyond the last index in all news articles list.
	 * 
	 * @throws ServiceException in the case when DaoException 
	 * 			occurs while getting all news articles from the data storage 
	 */
	@Override
	public boolean isLastIndexExcluded(int index) throws ServiceException {
		return index == findAllArticles().size();
	}

	/**
	 * Checks if the previous index is equals to zero.
	 * 
	 * @param index - index which presumably have previous index equals to zero
	 * 
	 * @param step - step to move back
	 * 
	 * @return - {@code true} if {@code index} is is one step beyond the zero index in all news articles list.
	 */
	@Override
	public boolean isPreviousIndexZero(int index, int step) {
		return (index - step) == IndexRepository.ZERO_INDEX;
	}
	
	/**
	 * Calculates the next index by step. 
	 * 
	 * <p>The method is safe from IndexOutOfBoundsException because it takes 
	 * into account the last valid index excluded. 
	 * 
	 * @param previousIndex - previous index
	 * 
	 * @param step - step to define the size of sub-bunch of news articles
	 * 
	 * @return - next index
	 */
	@Override
	public int calculateNextIndex(int previousIndex, int step) throws ServiceException {
		int nextIndex = previousIndex + step;
		int maxIndex = findAllArticles().size();
		if (nextIndex > maxIndex) {
			nextIndex = maxIndex;
		}
		return nextIndex;
	}

	
	/**
	 * Calculates the previous index by step. 
	 * 
	 * <p>The method is safe from IndexOutOfBoundsException because it takes 
	 * into account the first valid index included (zero index). 
	 * 
	 * @param nextIndex - next index
	 * 
	 * @param step - step to define the size of sub-bunch of news articles
	 * 
	 * @return - previous index
	 */
	@Override
	public int calculatePreviousIndex(int nextIndex, int step)  throws ServiceException {
		int maxIndex = findAllArticles().size();
		int previousIndex;
		if(nextIndex == maxIndex) {
			previousIndex = nextIndex - (maxIndex % step); //brackets just for clarity
		} else {
			previousIndex = nextIndex - step;
		}
		if (previousIndex < IndexRepository.ZERO_INDEX) {
			previousIndex = IndexRepository.ZERO_INDEX;
		}
		return previousIndex;
	}	
	
	/**
	 * Calculates the last index excluded for bunch (sublist) of news articles.
	 * 
	 * @param fromIndex - the first index included for bunch (sublist) of news articles
	 * 
	 * @param currentDirection - direction need to move the bunch of news articles
	 * 
	 * @param previousDirection - direction from the previous step
	 * 
	 * @return last index excluded for bunch (sublist) of news articles.
	 */
	@Override
	public int calculateToIndex(int fromIndex, int step, ListDirection currentDirection,
			ListDirection previousDirection) throws ServiceException {
		int toIndex;
		switch (currentDirection) {
		case TO_END:
			toIndex = calculateNextIndex(fromIndex, step);
			break;
		case TO_BEGINNING:
			if (currentDirection == previousDirection) {
				toIndex = fromIndex;
			} else {
				toIndex = calculatePreviousIndex(fromIndex, step);
			}
			break;
		default:
			toIndex = IndexRepository.INVALID_INDEX;
		}
		return toIndex;
	}

	
	/**
	 * Calculates the first index included for bunch (sublist) of news articles.
	 * 
	 * @param toIndex - the last index excluded for bunch (sublist) of news articles
	 * 
	 * @param currentDirection - direction need to move the bunch of news articles
	 * 
	 * @param previousDirection - direction from the previous step
	 * 
	 * @return first index included for bunch (sublist) of news articles.
	 */
	@Override
	public int calculateFromIndex(int toIndex, int step, ListDirection currentDirection,
			ListDirection previousDirection) throws ServiceException {
		int fromIndex = IndexRepository.ZERO_INDEX;

		switch (currentDirection) {
		case TO_END:
			if (currentDirection == previousDirection) {
				fromIndex = toIndex;
			} else {
				fromIndex = calculateNextIndex(toIndex, step);
			}
			break;
		case TO_BEGINNING:
			fromIndex = calculatePreviousIndex(toIndex, step);
			break;
		default:
			fromIndex = IndexRepository.INVALID_INDEX;
		}
		
		return fromIndex;

	}

}
