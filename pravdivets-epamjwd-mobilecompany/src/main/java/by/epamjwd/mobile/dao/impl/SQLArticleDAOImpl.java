package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;


import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.ArticleDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLArticleDAOImpl extends AbstractDao<Article> implements ArticleDAO {
	public final static String COMMA = ", ";
	public final static String QUESTION_MARK = "=?, ";

	private static final String ADD_NEW_ARTICLE = "INSERT INTO " +
			DBTableName.NEWS + " (" + 
			DBColumnName.NEWS_DATE + COMMA + 
			DBColumnName.NEWS_TITLE + COMMA +
			DBColumnName.NEWS_INTRO + COMMA +
			DBColumnName.NEWS_TEXT + 
			") VALUES (?, ?, ?, ?)";
	private static final String GET_ARTICLE_BY_TITLE = "SELECT * FROM " + 
			DBTableName.NEWS + " WHERE " + 
			DBColumnName.NEWS_TITLE + "= ?";


	public SQLArticleDAOImpl() {
		super(RowMapperFactory.getInstance().getNewsRowMapper(), DBTableName.NEWS);

	}

	@Override
	public List<Article> getAllArticles() throws DaoException {
		return findALLDescending();
	}

	@Override
	public Optional<Article> getArticleByID(long id) throws DaoException {
		return findById(id);
	}


	@Override
	public long addArticle(Article article) throws DaoException {
		long articleID;
		Object[] params = SQLParametersHelper.provideNewArticleParameters(article);
		articleID = executeInsertQuery(ADD_NEW_ARTICLE, params);
		return articleID;
	}

	@Override
	public Optional<Article> getArticleByTitle(String title) throws DaoException {
		return executeQueryForSingleResult(GET_ARTICLE_BY_TITLE, title);
	}

}
