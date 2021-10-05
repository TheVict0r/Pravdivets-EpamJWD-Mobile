package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;

public class UserRowMapper implements RowMapper<User>{

	@Override
	public User map(ResultSet resultSet) throws SQLException {
		User user = new User(
				resultSet.getLong  (DBColumnName.USERS_ID), 
				resultSet.getString(DBColumnName.USERS_PASSWORD), 
				resultSet.getString(DBColumnName.USERS_FIRST_NAME), 
				resultSet.getString(DBColumnName.USERS_MIDDLE_NAME), 
				resultSet.getString(DBColumnName.USERS_LAST_NAME), 
				resultSet.getString(DBColumnName.USERS_EMAIL), 
   Role.valueOf(resultSet.getString(DBColumnName.ROLES_ROLE).toUpperCase())); 
		return user;
	}

}
