package by.epamjwd.mobile.ZZZZtemporary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.dao.connectionpool.ConnectionPool;
import by.epamjwd.mobile.dao.connectionpool.exception.ConnectionPoolException;

public class ConnectionPoolMain {

	public final static String BASE_NAME = "db";
	public static final String SELECT_FROM_REGIONS = "SELECT * FROM REGIONS";
	public static final String SELECT_FROM_NEWS = "SELECT * FROM NEWS";
	static Logger logger = LogManager.getLogger(ConnectionPool.class);
	
	public static void main(String[] args) throws ConnectionPoolException {
		
		//printTable(SELECT_FROM_REGIONS);
		printTable(SELECT_FROM_NEWS);
		
	}

	public static void printTable(String sqlRequest) throws ConnectionPoolException {
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			pool = ConnectionPool.getInstance();
			pool.initPoolData(BASE_NAME);
			connection = pool.takeConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlRequest);
			
			while(resultSet.next()) {
				System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));
				//System.out.println(resultSet.getString(1) + " - " + resultSet.getString(2));
			}
		} catch (ConnectionPoolException e) {
			//e.printStackTrace();
			logger.error("ConnectionPoolException in ConnectionPool", e);
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.error("SQLException in ConnectionPool", e);
		} catch (NullPointerException e) {
			
			//e.printStackTrace();
			logger.error("NullPointerException in ConnectionPool", e);
		} finally {
			pool.dispose();
		}
	}
	
	
}
