package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Bill;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;

public class BillRowMapper implements RowMapper<Bill>{

	@Override
	public Bill map(ResultSet resultSet) throws SQLException {
		Bill bill = new Bill(
				resultSet.getLong(DBColumnName.BILLS_ID),
				resultSet.getLong(DBColumnName.BILLS_SUBSCRIBER_ID),
				resultSet.getDate(DBColumnName.BILLS_DATE),
				resultSet.getInt (DBColumnName.BILLS_AMOUNT));
		return bill;
	}

}
