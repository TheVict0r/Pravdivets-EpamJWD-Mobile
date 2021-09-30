package by.epamjwd.mobile.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.TariffPlan;
import by.epamjwd.mobile.controller.command.repository.DBColumnName;
import by.epamjwd.mobile.dao.TariffPlanDAO;
import by.epamjwd.mobile.dao.connectionpool.ConnectionPool;
import by.epamjwd.mobile.dao.connectionpool.exception.ConnectionPoolException;

public class SQLTariffPlanDAO implements TariffPlanDAO{

	private final static Logger LOGGER = LogManager.getLogger(SQLTariffPlanDAO.class);	

	public final static String BASE_NAME = "db";
	// CHECK CODE DUPLICATION FOR "BASE_NAME" WITH LISTENER

	public final static String SELECT_ALL_TARIFF_PLANS = "SELECT * FROM Tariff_Plans";
	
	@Override
	public List<TariffPlan> getAllTariffPlans() {
		List<TariffPlan> all;
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
			resultSet = statement.executeQuery(SELECT_ALL_TARIFF_PLANS);
			
			while (resultSet.next()) {
				TariffPlan tariffPlan = new TariffPlan();
				fillTariffPlan(tariffPlan, resultSet);
				all.add(tariffPlan);
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
	public TariffPlan getTariffPlanByID(int id) {
		List<TariffPlan> allTariffPlans = getAllTariffPlans();
		TariffPlan result = null;
		for (TariffPlan tariffPlan : allTariffPlans) {
			if (tariffPlan.getId() == id) {
				result = tariffPlan;
			}
		}
		return result;
	}

	
	
	private void fillTariffPlan(TariffPlan tariffPlan, ResultSet resultSet) {
		try {
			tariffPlan.setId(resultSet.getInt(DBColumnName.TARIFF_PLANS_ID));
			tariffPlan.setName(resultSet.getString(DBColumnName.TARIFF_PLANS_NAME));
			tariffPlan.setRegularPayment(resultSet.getInt(DBColumnName.TARIFF_PLANS_REGULAR_PAYMENT));
			tariffPlan.setPriceWithinNetwork(resultSet.getInt(DBColumnName.TARIFF_PLANS_WITHIN_NETWORK));
			tariffPlan.setPriceOtherNetworks(resultSet.getInt(DBColumnName.TARIFF_PLANS_OTHER_NETWORKS));
			tariffPlan.setPriceAbroad(resultSet.getInt(DBColumnName.TARIFF_PLANS_ABROAD));
			tariffPlan.setPriceVideocall(resultSet.getInt(DBColumnName.TARIFF_PLANS_VIDEOCALL));
			tariffPlan.setPriceSMS(resultSet.getInt(DBColumnName.TARIFF_PLANS_SMS));
			tariffPlan.setPriceMMS(resultSet.getInt(DBColumnName.TARIFF_PLANS_MMS));
			tariffPlan.setPriceInternet(resultSet.getInt(DBColumnName.TARIFF_PLANS_INTERNET));
			tariffPlan.setDescription(resultSet.getString(DBColumnName.TARIFF_PLANS_DESCRIPTION));
		} catch (SQLException e) {
			LOGGER.error("SQLException while filling in the TariffPlan bean", e);
		}
	}

}
