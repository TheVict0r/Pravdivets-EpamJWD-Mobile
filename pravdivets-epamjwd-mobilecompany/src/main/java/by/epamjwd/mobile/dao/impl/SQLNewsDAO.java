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
import by.epamjwd.mobile.dao.NewsDAO;
import by.epamjwd.mobile.dao.connectionpool.ConnectionPool;
import by.epamjwd.mobile.dao.connectionpool.exception.ConnectionPoolException;

public class SQLNewsDAO implements NewsDAO {

	private final static Logger LOGGER = LogManager.getLogger(SQLNewsDAO.class);

	public final static String BASE_NAME = "db";
	// CHECK CODE DUPLICATION FOR "BASE_NAME" WITH LISTENER

	public final static String SELECT_ALL_FROM_NEWS = "SELECT * FROM NEWS ORDER BY id DESC";
	public final static String SELECT_LAST_THREE_ARTICLES_FROM_NEWS = "SELECT * FROM news ORDER BY id DESC LIMIT 3";
	public final static String SELECT_ARTICLE_FROM_NEWS_BY_ID = "SELECT * FROM news WHERE id = ?";

	@Override
	public List<NewsArticle> getAllNews() {
		return getNewsBunch(SELECT_ALL_FROM_NEWS);
	}

	@Override
	public List<NewsArticle> getLastThreeArticles() {
		return getNewsBunch(SELECT_LAST_THREE_ARTICLES_FROM_NEWS);
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
			statement.setInt(1, id);

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

	private List<NewsArticle> getNewsBunch(String request) {

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
			newsResultSet = statement.executeQuery(request);
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

	private void fillInArticle(NewsArticle article, ResultSet newsResultSet) {
		try {
			article.setId(newsResultSet.getInt(1));
			article.setDate(newsResultSet.getDate(2));
			article.setTitle(newsResultSet.getString(3));
			article.setLead(newsResultSet.getString(4));
			article.setText(newsResultSet.getString(5));
		} catch (SQLException e) {
			LOGGER.error("SQLException while filling in the article bean", e);
		}

	}

}
