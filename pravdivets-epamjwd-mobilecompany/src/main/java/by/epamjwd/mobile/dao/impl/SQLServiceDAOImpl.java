package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.ServiceDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLServiceDAOImpl extends AbstractDao<Service> implements ServiceDAO{

	public SQLServiceDAOImpl() {
        super(RowMapperFactory.getInstance().getServiceRowMapper(), DBTableName.SERVICES);
	}

	@Override
	public List<Service> getAllServices() throws DaoException {
		return findAll();
	}

	@Override
	public Optional<Service> getServiceByID(long id) throws DaoException {
		return findById(id);
	}

	@Override
	public long save(Service item) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
