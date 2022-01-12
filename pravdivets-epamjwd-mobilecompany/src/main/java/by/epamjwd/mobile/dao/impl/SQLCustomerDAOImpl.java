package by.epamjwd.mobile.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.CustomerDAO;
import by.epamjwd.mobile.dao.connectionpool.ConnectionPool;
import by.epamjwd.mobile.dao.connectionpool.exception.ConnectionPoolException;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLCustomerDAOImpl extends AbstractDao<User> implements CustomerDAO{
	private static final long ERROR_ID = -1;

	private final static Logger LOGGER = LogManager.getLogger(SQLCustomerDAOImpl.class);

	
	public SQLCustomerDAOImpl() {
        super(RowMapperFactory.getInstance().getUserRowMapper(), DBTableName.USERS);
	}

	@Override
	public long addNewCustomer(User user, Subscriber subscriber) throws DaoException {
		long subscriberID = ERROR_ID;
		long userID;
		
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			connection.setAutoCommit(false);
			Object[] userParameters = SQLParametersHelper.provideNewUserParameters(user);
			userID = executeInsertQuery(SQLUserDAOImpl.ADD_NEW_USER, userParameters);
			subscriber.setUserId(userID);
			Object[] subscriberParameters = SQLParametersHelper.provideNewSubscriberParameters(subscriber);
			subscriberID = executeInsertQuery(SQLSubscriberDAOImpl.ADD_SUBSCRIBER_TO_EXISTING_USER,
					subscriberParameters);
			connection.commit();
		} catch (ConnectionPoolException e1) {
			LOGGER.error("Unable to initialize connection pool", e1);
			throw new RuntimeException("Unable to initialize connection pool.", e1);
		} catch (SQLException e2) {
			try {
				connection.rollback();
			} catch (SQLException e3) {
				LOGGER.error("Unable to rollback transaction", e3);
				throw new DaoException("Unable to rollback transaction", e3);
			}
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e4) {
				LOGGER.error("Unable to setAutoCommit(true) in transaction", e4);
				throw new DaoException("Unable to setAutoCommit(true) in transaction", e4);
			} 
			try {
				ConnectionPool.getInstance().releaseConnection(connection);
			} catch (ConnectionPoolException e5) {
				LOGGER.error("Unable to release connection in connection pool", e5);
				throw new RuntimeException("Unable to to release connection in connection pool", e5);
			}
		}
		return subscriberID;
	}

}
