package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.SubscriberStatus;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;

public class SubscriberRowMapper implements RowMapper<Subscriber> {

	/**
	 * Difference in indexes: SQL indexes start from 1, Java Enum indexes start from 0
	 */
	private final static int INDEX_SHIFT = 1;
	
	/**
	 * Makes Subscriber object from result set
	 * 
	 * @param resultSet - result set containing the data for Subscriber object 
	 */
	@Override
	public Subscriber map(ResultSet resultSet) throws SQLException {
			
		SubscriberStatus status = SubscriberStatus.values()[resultSet.getInt(DBColumnName.SUBSCRIBERS_STATUS_ID) - INDEX_SHIFT];
		
		return new Subscriber(
							resultSet.getLong  (DBColumnName.SUBSCRIBERS_ID),
							resultSet.getDate  (DBColumnName.SUBSCRIBERS_CONTRACT_DATE),
							resultSet.getInt   (DBColumnName.SUBSCRIBERS_ACCOUNT),
							resultSet.getString(DBColumnName.SUBSCRIBERS_PHONE),
							resultSet.getDate  (DBColumnName.SUBSCRIBERS_STATUS_DATE),
							status,
							resultSet.getLong  (DBColumnName.SUBSCRIBERS_PLAN_ID),
							resultSet.getLong  (DBColumnName.SUBSCRIBERS_USER_ID));
	}

}
