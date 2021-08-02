package by.epamjwd.mobile.temporary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.connectionpool.ConnectionPool;
import by.epamjwd.mobile.connectionpool.exception.ConnectionPoolException;

public class ConnectionPoolMain {

	public static final String SELECT_FROM_REGIONS = "SELECT * FROM REGIONS";
	public static final String SELECT_FROM_NEWS = "SELECT * FROM NEWS";
	static Logger logger = LogManager.getLogger(ConnectionPool.class);
	
	public static void main(String[] args) {
		
		//printTable(SELECT_FROM_REGIONS);
		printTable(SELECT_FROM_NEWS);
		
	}

	public static void printTable(String sqlRequest) {
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			pool = ConnectionPool.getInstance();
			pool.initPoolData();
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
