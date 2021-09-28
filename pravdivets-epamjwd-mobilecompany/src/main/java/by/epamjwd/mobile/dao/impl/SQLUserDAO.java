package by.epamjwd.mobile.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.connectionpool.ConnectionPool;
import by.epamjwd.mobile.dao.connectionpool.exception.ConnectionPoolException;

public class SQLUserDAO implements UserDAO{

	private final static Logger LOGGER = LogManager.getLogger(SQLUserDAO.class);

	public final static String BASE_NAME = "db";
	// CHECK CODE DUPLICATION FOR "BASE_NAME" WITH LISTENER

	public final static String GET_USER_BY_EMAIL = "SELECT Users.ID, Users.email, Users.password, Users.first_name, Users.middle_name, Users.last_name, Roles.role, Regions.region FROM Users INNER JOIN Roles ON Users.role_id_role = Roles.id_role INNER JOIN Regions ON Users.region_id_region = Regions.id_region WHERE Users.email = ?";
	
	@Override
	public boolean authorization(String login, String passwordHash) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registration(Abonent newAbonent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Abonent lookAbonent(int idAbonent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activateService(int serviceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deActivateService(int serviceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void switchServiceParameters(String oldServiceId, String newServiceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePhoneNumber(String oldPhoneNumber, String newPhoneNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserByEmail(String email) {
		ConnectionPool pool = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet userResultSet = null;
		User user = null;
		
		try {
			pool = ConnectionPool.getInstance();
			pool.initPoolData(BASE_NAME);
			connection = pool.takeConnection();
			statement = connection.prepareStatement(GET_USER_BY_EMAIL);
			statement.setString(1, email);
			userResultSet = statement.executeQuery();
			while (userResultSet.next()) {
				user = new User(userResultSet.getInt("ID"), userResultSet.getString("password"), 
						userResultSet.getString("first_name"), userResultSet.getString("middle_name"), 
						userResultSet.getString("last_name"), userResultSet.getString("email"), 
						Role.valueOf(userResultSet.getString("role").toUpperCase()), 
						userResultSet.getString("region"));
			}
			// check exception messages
		} catch (ConnectionPoolException e) {
			LOGGER.error("ConnectionPoolException in ConnectionPool", e);
		} catch (SQLException e) {
			LOGGER.error("SQLException in ConnectionPool", e);
		} catch (NullPointerException e) {
			LOGGER.error("NullPointerException in ConnectionPool", e);
		} finally {
			pool.dispose();
		}
		return user;
	}

	
	
	
}
