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

	public final static String SELECT_ALL_TARIFF_PLANS = "SELECT Tariff_Plans.id, Tariff_Plans.name, Tariff_Plans.regular_payment, Tariff_Plans.description, Services.service_names_id, Services.tarif FROM Tariff_Plans INNER JOIN Services ON Tariff_Plans.id = Services.tariff_plans_id INNER JOIN Service_Names ON Services.service_names_id = Service_Names.id";
	
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
			int idOld = 0;
			TariffPlan tariffPlan = null;
			while (resultSet.next()) {
				int idNew = resultSet.getInt("id");
				if (idNew != idOld) {
					tariffPlan = new TariffPlan();
					tariffPlan.setId(resultSet.getInt("id"));
					tariffPlan.setName(resultSet.getString("name"));
					tariffPlan.setRegularPayment(resultSet.getString("regular_payment"));
					tariffPlan.setDescription(resultSet.getString("description"));
					idOld = idNew;
				} else {
					int serviceId = resultSet.getInt("service_names_id");
					int tarifValue = resultSet.getInt("tarif");

					switch (serviceId) {
					case 1:
						tariffPlan.setPriceWithinNetwork(tarifValue);
						break;
					case 2:
						tariffPlan.setPriceOtherNetworks(tarifValue);
						break;
					case 3:
						tariffPlan.setPriceAbroad(tarifValue);
						break;
					case 4:
						tariffPlan.setPriceVideocall(tarifValue);
						break;
					case 5:
						tariffPlan.setPriceSMS(tarifValue);
						break;
					case 6:
						tariffPlan.setPriceMMS(tarifValue);
						break;
					case 7:
						tariffPlan.setPriceInternet(tarifValue);
						all.add(tariffPlan);
						break;
					default:
						LOGGER.error("Unexpected tarif ID value: " + serviceId);
					}
				}
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
		return all;
	}

	@Override
	public TariffPlan getTariffPlanByID(int id) {
		
		List<TariffPlan> all = getAllTariffPlans();
		TariffPlan result = null;
		
		for(TariffPlan tariffPlan : all) {
			if(tariffPlan.getId() == id) {
				result = tariffPlan;
			}
		}
		return result;
	}

	
		
	}
	
	

