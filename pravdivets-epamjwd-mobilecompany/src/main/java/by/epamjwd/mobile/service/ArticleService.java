package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface ArticleService {

	public List<Article> findAllArticles() throws ServiceException;
	
	public Optional<Article> findArticleByID(long id) throws ServiceException;

	List<Article> buildArticlesBatch(int firstIdx, int lastIdx) throws ServiceException;

	public boolean isNextIdxAvailable(int lastIdxOld) throws ServiceException;

	public int getNextIdxExcluded(int lastIdxOld, int step) throws ServiceException;

	public int getFirstIdx(int lastIdx);

	int getLastIdx(int currentIdx);

	public Article buildArticle(String title, String lead, String text);

	public long addArticle(Article article) throws ServiceException;

	public boolean isArticleExists(String title) throws ServiceException;

	public Optional<Article> findArticleByTitle(String title) throws ServiceException;
	
	
}
