package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface NewsDAO {

	public List<NewsArticle> getAllNews() throws DaoException;
	
	public Optional<NewsArticle> getArticleByID(long id) throws DaoException;

	
}
