package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface ArticleService {

	List<Article> findAllArticles() throws ServiceException;
	
	Optional<Article> findArticleByID(long id) throws ServiceException;

	List<Article> buildArticlesBatch(int firstIdx, int lastIdx) throws ServiceException;

	boolean isNextIndexAvailableMovingBack(int lastIdxOld) throws ServiceException;

	int getNextIndexExcludedMovingBack(int lastIdxOld, int step) throws ServiceException;

	Article buildArticle(String title, String lead, String text);

	long addArticle(Article article) throws ServiceException;

	boolean doesArticleExists(String title) throws ServiceException;

	Optional<Article> findArticleByTitle(String title) throws ServiceException;

	int getLastIndexMovingForward(int currentIndex, int step);

	int getFirstIndexMovingForward(int lastIndex, int step);
	
	
}
