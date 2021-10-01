package by.epamjwd.mobile.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.controller.repository.DBColumnName;
import by.epamjwd.mobile.dao.NewsDAO;
import by.epamjwd.mobile.dao.connectionpool.ConnectionPool;
import by.epamjwd.mobile.dao.connectionpool.exception.ConnectionPoolException;

public class SQLNewsDAO implements NewsDAO {

	private final static Logger LOGGER = LogManager.getLogger(SQLNewsDAO.class);

	public final static String BASE_NAME = "db";
	// CHECK CODE DUPLICATION FOR "BASE_NAME" WITH LISTENER

	public final static String SELECT_ALL_FROM_NEWS = "SELECT * FROM NEWS ORDER BY id DESC";
	public final static String SELECT_ARTICLE_FROM_NEWS_BY_ID = "SELECT * FROM news WHERE id = ?";

	@Override
	public List<NewsArticle> getAllNews() {
		
		List<NewsArticle> allNews;
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet newsResultSet = null;

		allNews = new ArrayList<>();

		try {
			pool = ConnectionPool.getInstance();
			pool.initPoolData(BASE_NAME);
			connection = pool.takeConnection();
			statement = connection.createStatement();
			newsResultSet = statement.executeQuery(SELECT_ALL_FROM_NEWS);
			while (newsResultSet.next()) {
				NewsArticle article = new NewsArticle();
				fillInArticle(article, newsResultSet);
				allNews.add(article);
			}
			// check exception messages
		} catch (ConnectionPoolException e) {
			LOGGER.error("ConnectionPoolException in ConnectionPool", e);
		} catch (SQLException e) {
			LOGGER.error("SQLException in ConnectionPool", e);
		} catch (NullPointerException e) {
			LOGGER.error("NullPointerException in ConnectionPool", e);
		} finally {
			pool.dispose();
		}
		return allNews;
	}

	@Override
	public NewsArticle getArticleByID(int id) {
		NewsArticle article = null;
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet newsResultSet = null;

		try {
			pool = ConnectionPool.getInstance();
			pool.initPoolData(BASE_NAME);
			connection = pool.takeConnection();
			statement = connection.prepareStatement(SELECT_ARTICLE_FROM_NEWS_BY_ID);
			statement.setObject(1, id);

			newsResultSet = statement.executeQuery();

			while (newsResultSet.next()) {
				article = new NewsArticle();
				fillInArticle(article, newsResultSet);
			}
		} catch (ConnectionPoolException e) {
			LOGGER.error("ConnectionPoolException in ConnectionPool", e);
		} catch (SQLException e) {
			LOGGER.error("SQLException in ConnectionPool", e);
		} catch (NullPointerException e) {
			LOGGER.error("NullPointerException in ConnectionPool", e);
		} finally {
			pool.dispose();
		}
		return article;
	}

	private void fillInArticle(NewsArticle article, ResultSet newsResultSet) {
		try {
			article.setId(newsResultSet.getInt(DBColumnName.NEWS_ID));
			article.setDate(newsResultSet.getDate(DBColumnName.NEWS_DATE));
			article.setTitle(newsResultSet.getString(DBColumnName.NEWS_TITLE));
			article.setLead(newsResultSet.getString(DBColumnName.NEWS_LEAD));
			article.setText(newsResultSet.getString(DBColumnName.NEWS_TEXT));
		} catch (SQLException e) {
			LOGGER.error("SQLException while filling in the article bean", e);
		}

	}

}
