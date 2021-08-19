package by.epamjwd.mobile.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.dao.connectionpool.ConnectionPool;


public class AppContextListener implements ServletContextListener  {

	private final static Logger LOGGER = LogManager.getLogger(AppContextListener.class);
	
	public final static String BASE_NAME = "db";
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ConnectionPool pool = null;
// TODO - check why the exception does not occur		
//		try {
			pool = ConnectionPool.getInstance();
			pool.initPoolData(BASE_NAME);
//		} catch(ConnectionPoolException e) {
//			LOGGER.error("Error while initializing the connection pool", e);
//			throw new RuntimeException("Error while initializing the connection pool", e);
//	}
	}
	
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ConnectionPool pool = null;
//		try {
		pool = ConnectionPool.getInstance();
		pool.dispose();
//		} catch(ConnectionPoolException e) {
//		LOGGER.error("Error while closing the connection pool", e);
//		throw new RuntimeException("Error while closing the connection pool", e);
		
		
	}
}
