package by.epamjwd.mobile.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.connectionpool.ConnectionPool;
import by.epamjwd.mobile.connectionpool.exception.ConnectionPoolException;
import by.epamjwd.mobile.dao.NewsDAO;

public class SQLNewsDAO implements NewsDAO{

	private final static Logger LOGGER = LogManager.getLogger(SQLNewsDAO.class);

	final String SELECT_ALL_FROM_NEWS = "SELECT * FROM NEWS ORDER BY id DESC";
	final String SELECT_LAST_THREE_ARTICLES_FROM_NEWS = "SELECT * FROM news ORDER BY id DESC LIMIT 3";

	public List<NewsArticle> getAllNews() {
		return getNewsBunch(SELECT_ALL_FROM_NEWS);
	}

	public List<NewsArticle> getLastThreeArticles() {
		return getNewsBunch(SELECT_LAST_THREE_ARTICLES_FROM_NEWS);
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
			pool.initPoolData();
			connection = pool.takeConnection();
			statement = connection.createStatement();
			newsResultSet = statement.executeQuery(request);
			while (newsResultSet.next()) {
				NewsArticle article = new NewsArticle();
				article.setId(newsResultSet.getInt(1));
				article.setDate(newsResultSet.getDate(2));
				article.setTitle(newsResultSet.getString(3));
				article.setLead(newsResultSet.getString(4));
				article.setText(newsResultSet.getString(5));
				allNews.add(article);
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

		return allNews;
	}

}
