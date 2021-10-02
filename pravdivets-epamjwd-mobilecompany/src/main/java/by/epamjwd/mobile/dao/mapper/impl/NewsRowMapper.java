package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;

public class NewsRowMapper implements RowMapper<NewsArticle>{

	@Override
	public NewsArticle map(ResultSet resultSet) throws SQLException {
		NewsArticle newsArticle = new NewsArticle(
				resultSet.getInt(DBColumnName.NEWS_ID),
				resultSet.getDate(DBColumnName.NEWS_DATE),
				resultSet.getString(DBColumnName.NEWS_TITLE),
				resultSet.getString(DBColumnName.NEWS_LEAD),
				resultSet.getString(DBColumnName.NEWS_TEXT));
		return newsArticle;
	}

}
