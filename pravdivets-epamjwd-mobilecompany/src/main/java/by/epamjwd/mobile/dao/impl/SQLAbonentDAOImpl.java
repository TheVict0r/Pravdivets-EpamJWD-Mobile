package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.AbonentDAO;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLAbonentDAOImpl extends AbstractDao<Abonent> implements AbonentDAO{

	public SQLAbonentDAOImpl() {
        super(RowMapperFactory.getInstance().getAbonentRowMapper(), DBTableName.ABONENTS);
	}

	public final static String BASIC_ABONENT_SELECT_QUERY = "SELECT Abonents.id, Abonents.contract_date, "
			+ "Users.password, Users.first_name, Users.middle_name, Users.last_name, Customers.passport_number, "
			+ "Users.email,  Customers.home_address, Abonents.account, Abonents.phone_number, Tariff_Plans.name, "
			+ "Status.status,  Abonents.status_date FROM Users INNER JOIN Customers ON Users.id = Customers.users_id "
			+ "INNER JOIN Abonents ON Customers.id = Abonents.customers_id INNER JOIN Tariff_Plans "
			+ "ON Abonents.tariff_plan_id = Tariff_Plans.id INNER JOIN Status ON Abonents.status_id = Status.id "
			+ "WHERE ";
	
	@Override
	public Optional<Abonent> findAbonentByPhoneNumber(int phoneNumber) throws DaoException {
		String query = makeAbonentSelectQuery(DBTableName.ABONENTS, DBColumnName.ABONENTS_PHONE_NUMBER);
		return executeQueryForSingleResult(query, phoneNumber);
	}

	@Override
	public List<Abonent> findAbonentListByEmail(String email) throws DaoException {
		String query = makeAbonentSelectQuery(DBTableName.USERS, DBColumnName.USERS_EMAIL);
		return executeQuery(query, email);
	}

	@Override
	public List<Abonent> findAbonentListByUserId(String id) throws DaoException {
		String query = makeAbonentSelectQuery(DBTableName.USERS, DBColumnName.USERS_ID);
		return executeQuery(query, id);
	}
	
	@Override
	public List<Abonent> findAbonentListByFullName(String firstName, String middleName, String lastName) throws DaoException {
		String query = new StringBuilder(BASIC_ABONENT_SELECT_QUERY)
				.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_FIRST_NAME).append(" = ? AND ")
				.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_MIDDLE_NAME).append(" = ? AND ")
				.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_LAST_NAME).append(" = ?")
				.toString();
		return executeQuery(query, firstName, middleName, lastName);
	}
	
	@Override
	public Optional<Abonent> findAbonentById(String id) throws DaoException {
		String query = makeAbonentSelectQuery(DBTableName.ABONENTS, DBColumnName.ABONENTS_ID);
		return executeQueryForSingleResult(query, id);
	}
	
//	@Override
//	public void registration(Abonent newAbonent) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Abonent lookAbonent(int idAbonent) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void activateService(int serviceId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deActivateService(int serviceId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void switchServiceParameters(String oldServiceId, String newServiceId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void changePhoneNumber(String oldPhoneNumber, String newPhoneNumber) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void pause() {
//		// TODO Auto-generated method stub
//		
//	}


	@Override
	public long save(Abonent item) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	private String makeAbonentSelectQuery(String tableName, String columnName) {
		
		return new StringBuilder(BASIC_ABONENT_SELECT_QUERY)
				.append(tableName)
				.append(".")
				.append(columnName)
				.append(" = ?")
				.toString();
	}



}
