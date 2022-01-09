package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface NewsService {

	public List<NewsArticle> findAllNews() throws ServiceException;
	
	public Optional<NewsArticle> findArticleByID(long id) throws ServiceException;

	List<NewsArticle> buildNewsBatch(int firstIdx, int lastIdx) throws ServiceException;

	public boolean isNextIdxAvailable(int lastIdxOld) throws ServiceException;

	public int getNextIdxExcluded(int lastIdxOld, int step) throws ServiceException;

	public int getFirstIdx(int lastIdx);
	
	
}
