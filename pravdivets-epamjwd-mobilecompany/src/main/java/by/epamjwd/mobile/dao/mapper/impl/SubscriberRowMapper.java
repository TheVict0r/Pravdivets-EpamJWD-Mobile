package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.SubscriberStatus;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;

public class SubscriberRowMapper implements RowMapper<Subscriber> {

	@Override
	public Subscriber map(ResultSet resultSet) throws SQLException {
			Subscriber subscriber = new Subscriber(
							resultSet.getLong  (DBColumnName.SUBSCRIBERS_ID),
							resultSet.getDate  (DBColumnName.SUBSCRIBERS_CONTRACT_DATE),
							resultSet.getInt   (DBColumnName.SUBSCRIBERS_ACCOUNT),
							resultSet.getInt   (DBColumnName.SUBSCRIBERS_PHONE),
							resultSet.getDate  (DBColumnName.SUBSCRIBERS_STATUS_DATE),
   SubscriberStatus.valueOf(resultSet.getString(DBColumnName.STATUSES_STATUS).toUpperCase()),
							resultSet.getLong  (DBColumnName.SUBSCRIBERS_PLAN_ID),
							resultSet.getLong  (DBColumnName.SUBSCRIBERS_USER_ID)
												);
		return subscriber;
	}

}
