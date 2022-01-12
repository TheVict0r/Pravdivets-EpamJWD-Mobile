package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface ArticleDAO {

	public List<Article> getAllArticles() throws DaoException;
	
	public Optional<Article> getArticleByID(long id) throws DaoException;

	public long addArticle(Article article) throws DaoException;

	public Optional<Article> getArticleByTitle(String title) throws DaoException;

	
}
