package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;


import by.epamjwd.mobile.bean.Article;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.ArticleDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLNewsDAOImpl extends AbstractDao<Article> implements ArticleDAO {

	
	public SQLNewsDAOImpl() {
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
	public long save(Article item) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

}
