package by.epamjwd.mobile.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.controller.repository.DBColumnName;
import by.epamjwd.mobile.controller.repository.DBTableName;
import by.epamjwd.mobile.dao.ServiceDAO;
import by.epamjwd.mobile.dao.connectionpool.ConnectionPool;
import by.epamjwd.mobile.dao.connectionpool.exception.ConnectionPoolException;

public class SQLServiceDAO implements ServiceDAO{

	private final static Logger LOGGER = LogManager.getLogger(SQLServiceDAO.class);	

	public final static String BASE_NAME = "db";
	// CHECK CODE DUPLICATION FOR "BASE_NAME" WITH LISTENER

	public final static String SELECT_ALL_SERVICES = "SELECT * FROM " + DBTableName.SERVICES;

	
	
	@Override
	public List<Service> getAllServices() {
		List<Service> all;
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		all = new ArrayList<>();

		try {
			pool = ConnectionPool.getInstance();
			pool.initPoolData(BASE_NAME);
			connection = pool.takeConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_ALL_SERVICES);
			
			while (resultSet.next()) {
				Service service = new Service();
				fillService(service, resultSet);
				all.add(service);
			}
			// check exception messages
		} catch (ConnectionPoolException e) {
			LOGGER.error("ConnectionPoolException in ConnectionPool", e);
		} catch (NullPointerException e) {
			LOGGER.error("NullPointerException in ConnectionPool", e);
		} catch (SQLException e) {
			LOGGER.error("SQLException in ConnectionPool", e);
		} finally {
			pool.dispose();
		}

		return all;
	}

	@Override
	public Service getServiceByID(int id) {
		List<Service> allServices = getAllServices();
		Service result = null;
		for (Service service : allServices) {
			if (service.getId() == id) {
				result = service;
			}
		}
		return result;
	}

	private void fillService(Service service, ResultSet resultSet) {
		try {
			service.setId(resultSet.getInt(DBColumnName.SERVICES_ID));
			service.setName(resultSet.getString(DBColumnName.SERVICES_NAME));
			service.setTarif(resultSet.getInt(DBColumnName.SERVICES_TARIF));
			service.setDescription(resultSet.getString(DBColumnName.SERVICES_DESCRIPTION));
		} catch (SQLException e) {
			LOGGER.error("SQLException while filling in the TariffPlan bean", e);
		}
	}
	
	
	
}
