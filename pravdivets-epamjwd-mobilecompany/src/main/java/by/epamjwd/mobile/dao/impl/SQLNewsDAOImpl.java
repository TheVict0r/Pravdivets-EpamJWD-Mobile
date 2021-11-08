package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;


import by.epamjwd.mobile.bean.NewsArticle;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.NewsDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLNewsDAOImpl extends AbstractDao<NewsArticle> implements NewsDAO {

	
	public SQLNewsDAOImpl() {
		super(RowMapperFactory.getInstance().getNewsRowMapper(), DBTableName.NEWS);

	}

	@Override
	public List<NewsArticle> getAllNews() throws DaoException {
		return findALLDescending();
	}

	@Override
	public Optional<NewsArticle> getArticleByID(long id) throws DaoException {
		return findById(id);
	}


	@Override
	public long save(NewsArticle item) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

}
