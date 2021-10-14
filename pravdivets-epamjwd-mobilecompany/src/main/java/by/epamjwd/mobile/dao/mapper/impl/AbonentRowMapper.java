package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.SubscriberStatus;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.util.PhoneNumberFormatter;

public class AbonentRowMapper implements RowMapper<Subscriber> {

	@Override
	public Subscriber map(ResultSet resultSet) throws SQLException {
		Subscriber abonent = new Subscriber(
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
  SubscriberStatus.valueOf(resultSet.getString(DBColumnName.STATUS_STATUS).toUpperCase()),
				        resultSet.getDate(DBColumnName.ABONENTS_STATUS_DATE)
				         );
		return abonent;
	}

}
