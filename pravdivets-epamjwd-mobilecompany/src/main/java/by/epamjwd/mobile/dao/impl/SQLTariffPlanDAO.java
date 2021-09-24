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
import by.epamjwd.mobile.dao.TariffPlanDAO;
import by.epamjwd.mobile.dao.connectionpool.ConnectionPool;
import by.epamjwd.mobile.dao.connectionpool.exception.ConnectionPoolException;

public class SQLTariffPlanDAO implements TariffPlanDAO{

	private final static Logger LOGGER = LogManager.getLogger(SQLTariffPlanDAO.class);	

	public final static String BASE_NAME = "db";
	// CHECK CODE DUPLICATION FOR "BASE_NAME" WITH LISTENER

	public final static String SELECT_ALL_TARIFF_PLANS = "SELECT Tariff_Plans.id, Tariff_Plans.name, Tariff_Plans.regular_payment, Tariff_Plans.description, Service_Names.name, Services.service_names_id, Services.tarif FROM Tariff_Plans INNER JOIN Services ON Tariff_Plans.id = Services.tariff_plans_id INNER JOIN Service_Names ON Services.service_names_id = Service_Names.id";
	public final static String SELECT_TARIFF_PLAN_BY_ID = "SELECT Tariff_Plans.id, Tariff_Plans.name, Tariff_Plans.regular_payment, Tariff_Plans.description, Service_Names.name, Services.tarif FROM Tariff_Plans INNER JOIN Services ON Tariff_Plans.id = Services.tariff_plans_id INNER JOIN Service_Names ON Services.service_names_id = Service_Names.id WHERE id = ?";

	
	@Override
	public List<TariffPlan> getAllTariffPlans() {
		List<TariffPlan> allTariffPlans;
		ConnectionPool pool = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet tariffPlanResultSet = null;

		allTariffPlans = new ArrayList<>();

		try {
			pool = ConnectionPool.getInstance();
			pool.initPoolData(BASE_NAME);
			connection = pool.takeConnection();
			statement = connection.createStatement();
			tariffPlanResultSet = statement.executeQuery(SELECT_ALL_TARIFF_PLANS);
			while (tariffPlanResultSet.next()) {
				TariffPlan tariffPlan = new TariffPlan();
				fillInTariffPlans(tariffPlan, tariffPlanResultSet);
				allTariffPlans.add(tariffPlan);
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
		return allTariffPlans;
	}

	@Override
	public TariffPlan getTariffPlanByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	private void fillInTariffPlans(TariffPlan tariffPlan, ResultSet tariffPlanResultSet){
		
		try {
			tariffPlan.setId(tariffPlanResultSet.getInt("id"));
			tariffPlan.setName(tariffPlanResultSet.getString("name"));
			tariffPlan.setRegularPayment(tariffPlanResultSet.getString("regular_payment"));
			tariffPlan.setDescription(tariffPlanResultSet.getString("description"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		
//		.name, Tariff_Plans., Tariff_Plans., Service_Names.name, Services.tarif FROM Tariff_Plans INNER JOIN Services ON Tariff_Plans.id = Services.tariff_plans_id INNER JOIN Service_Names ON Services.service_names_id = Service_Names.id
//		
//		private int priceWithinNetwork;
//		private int priceOtherNetworks;
//		private int priceAbroad;
//		private int priceVideocall;
//		private int priceSMS;
//		private int priceMMS;
//		private int priceInternet;
		
		
		
	}
	
	
}
