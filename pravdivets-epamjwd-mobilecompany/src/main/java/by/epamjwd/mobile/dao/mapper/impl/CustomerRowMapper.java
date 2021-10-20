package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Customer;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer map(ResultSet resultSet) throws SQLException {
		Customer subscriber = new Customer(
				resultSet.getLong  (DBColumnName.CUSTOMERS_ID),
				resultSet.getString(DBColumnName.CUSTOMERS_PASSPORT_NUMBER),
				resultSet.getString(DBColumnName.CUSTOMERS_HOME_ADDRESS),
				resultSet.getLong  (DBColumnName.CUSTOMERS_USERS_ID)
									);
return subscriber;
	}

}
