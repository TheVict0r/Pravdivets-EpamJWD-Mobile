package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;

public class NewsRowMapper implements RowMapper<Article>{

	@Override
	public Article map(ResultSet resultSet) throws SQLException {
		Article newsArticle = new Article(
				resultSet.getLong(DBColumnName.NEWS_ID),
				resultSet.getDate(DBColumnName.NEWS_DATE),
				resultSet.getString(DBColumnName.NEWS_TITLE),
				resultSet.getString(DBColumnName.NEWS_LEAD),
				resultSet.getString(DBColumnName.NEWS_TEXT));
		return newsArticle;
	}

}
