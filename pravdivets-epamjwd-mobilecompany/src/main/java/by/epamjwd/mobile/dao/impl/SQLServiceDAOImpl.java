package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.ServiceDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLServiceDAOImpl extends AbstractDao<Service> implements ServiceDAO{
	public final static String COMMA = ", ";
	public final static String QUESTION_MARK = "=?, ";

	public final static String GET_SERVICE_BY_NAME = "SELECT * FROM " + 
			DBTableName.SERVICES + " WHERE " + 
			DBColumnName.SERVICES_NAME + "= ?";

	public final static String ADD_NEW_SERVICE = "INSERT INTO " +
			DBTableName.SERVICES + "(" + 
			DBColumnName.SERVICES_NAME + COMMA + 
			DBColumnName.SERVICES_TARIF + COMMA +
			DBColumnName.SERVICES_DESCRIPTION + 
			") VALUES (?, ?, ?)";

	public static void main(String[] args) {
		System.out.println(ADD_NEW_SERVICE);
	}

	
	public SQLServiceDAOImpl() {
        super(RowMapperFactory.getInstance().getServiceRowMapper(), DBTableName.SERVICES);
	}

	/**
	 * ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public List<Service> getAllServices() throws DaoException {
		return findAll();
	}

	/**
	 * ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public Optional<Service> getServiceByID(long id) throws DaoException {
		return findById(id);
	}

	/**
	 * ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public Optional<Service> getServiceByName(String name) throws DaoException {
		return executeQueryForSingleResult(GET_SERVICE_BY_NAME, name);
	}

	/**
	 * ЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫЫ
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public long addService(Service service) throws DaoException {
		long serviceId;
		Object[] params = SQLParametersHelper.provideNewServiceParameters(service);
		serviceId = executeInsertQuery(ADD_NEW_SERVICE, params);
		return serviceId;
	}

	
}
