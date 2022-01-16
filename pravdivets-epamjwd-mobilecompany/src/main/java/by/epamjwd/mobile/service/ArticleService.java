package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.repository.ListDirection;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface ArticleService {

	List<Article> findAllArticles() throws ServiceException;
	
	Optional<Article> findArticleByID(long id) throws ServiceException;

	List<Article> buildArticlesBatch(int firstIdx, int lastIdx) throws ServiceException;

	boolean isLastIndexAvailable(int lastIdxOld) throws ServiceException;

	int getLastIndexMovingBackward(int lastIdxOld, int step) throws ServiceException;

	Article buildArticle(String title, String lead, String text);

	long addArticle(Article article) throws ServiceException;

	boolean doesArticleExists(String title) throws ServiceException;

	Optional<Article> findArticleByTitle(String title) throws ServiceException;

	int getLastIndexMovingForward(int currentIndex, int step);

	int getFirstIndexMovingForward(int lastIndex, int step);

	int calculateNextIndex(int previousIndex, int step) throws ServiceException;

	int calculatePreviousIndex(int nextIndex, int step) throws ServiceException;

	int calculateToIndex(int currentIndex, int step, ListDirection current, ListDirection previous) throws ServiceException;

	int calculateFromIndex(int currentIndex, int step, ListDirection current, ListDirection previous) throws ServiceException;
	
	
}
