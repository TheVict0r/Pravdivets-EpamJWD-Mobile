package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;

public class ArticleRowMapper implements RowMapper<Article>{

	/**
	 * Makes Article object from result set
	 * 
	 * @param resultSet - result set containing the data for Article object 
	 */
	@Override
	public Article map(ResultSet resultSet) throws SQLException {
		Article article = new Article(
				resultSet.getLong(DBColumnName.NEWS_ID),
				resultSet.getDate(DBColumnName.NEWS_DATE),
				resultSet.getString(DBColumnName.NEWS_TITLE),
				resultSet.getString(DBColumnName.NEWS_INTRO),
				resultSet.getString(DBColumnName.NEWS_TEXT));
		return article;
	}

}
