package by.epamjwd.mobile.dao.impl;

import java.util.Optional;

import by.epamjwd.mobile.bean.Customer;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.CustomerDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLCustomerDAOImpl extends AbstractDao<Customer> implements CustomerDAO{

	public SQLCustomerDAOImpl() {
        super(RowMapperFactory.getInstance().getCustomerRowMapper(), DBTableName.CUSTOMERS);
	}

	@Override
	public Optional<Customer> findCustomerByPassport(String passport) throws DaoException {
		Optional<Customer> customer = Optional.empty();
		String query = null;
		
		query = new StringBuilder("SELECT * ")
				.append(" FROM ").append(DBTableName.CUSTOMERS)
				.append(" WHERE ").append(DBTableName.CUSTOMERS).append(".").append(DBColumnName.CUSTOMERS_PASSPORT_NUMBER)
				.append(" = ?").toString();
		
		customer = executeQueryForSingleResult(query, passport);
		
		return customer;
	}

	@Override
	public long save(Customer item) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}
}
