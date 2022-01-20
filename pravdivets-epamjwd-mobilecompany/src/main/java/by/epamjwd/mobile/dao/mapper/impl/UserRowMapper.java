package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;

public class UserRowMapper implements RowMapper<User>{

	/**
	 * Makes User object from result set
	 * 
	 * @param resultSet - result set containing the data for User object 
	 */
	@Override
	public User map(ResultSet resultSet) throws SQLException {
		
		Role userRole = Role.values()[resultSet.getInt(DBColumnName.USERS_ROLE_ID) - 1];

		User user = new User(
				resultSet.getLong  (DBColumnName.USERS_ID), 
				resultSet.getString(DBColumnName.USERS_PASSWORD), 
				resultSet.getString(DBColumnName.USERS_FIRST_NAME), 
				resultSet.getString(DBColumnName.USERS_MIDDLE_NAME), 
				resultSet.getString(DBColumnName.USERS_LAST_NAME), 
				resultSet.getString(DBColumnName.USERS_PASSPORT), 
				resultSet.getString(DBColumnName.USERS_EMAIL), 
				userRole); 
		return user;
	}

	
	
}
