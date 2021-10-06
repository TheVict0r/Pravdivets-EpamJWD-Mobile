package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.AbonentStatus;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;

public class AbonentRowMapper implements RowMapper<Abonent> {

	@Override
	public Abonent map(ResultSet resultSet) throws SQLException {
		Abonent abonent = new Abonent(
						resultSet.getLong  (DBColumnName.ABONENTS_ID),
						resultSet.getDate(DBColumnName.ABONENT_CONTRACT_DATE),
						resultSet.getString(DBColumnName.USERS_PASSWORD),
						resultSet.getString(DBColumnName.USERS_FIRST_NAME),
						resultSet.getString(DBColumnName.USERS_MIDDLE_NAME),
						resultSet.getString(DBColumnName.USERS_LAST_NAME),
						resultSet.getString(DBColumnName.CUSTOMERS_PASSPORT_NUMBER),
						resultSet.getString(DBColumnName.USERS_EMAIL),
						resultSet.getString(DBColumnName.CUSTOMERS_HOME_ADDRESS),
						resultSet.getInt(DBColumnName.ABONENTS_ACCOUNT),
						resultSet.getInt(DBColumnName.ABONENTS_PHONE_NUMBER),
						resultSet.getString(DBColumnName.TARIFF_PLANS_NAME),
  AbonentStatus.valueOf(resultSet.getString(DBColumnName.STATUS_STATUS).toUpperCase()),
				        resultSet.getDate(DBColumnName.ABONENTS_STATUS_DATE)
				         );
		return abonent;
	}

}
