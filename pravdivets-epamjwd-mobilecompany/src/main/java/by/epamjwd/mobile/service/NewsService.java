package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface NewsService {

	public List<NewsArticle> findAllNews() throws ServiceException;
	
	public Optional<NewsArticle> findArticleByID(int id) throws ServiceException;
	
	
}
