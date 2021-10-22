package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.SubscriberDAO;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLSubscriberDAOImpl extends AbstractDao<Subscriber> implements SubscriberDAO{

	public SQLSubscriberDAOImpl() {
        super(RowMapperFactory.getInstance().getSubscriberRowMapper(), DBTableName.SUBSCRIBERS);
	}


	@Override
	public Optional<Subscriber> findSubscriberByPhoneNumber(int phoneNumber) throws DaoException {
		String query = buildSelectSubscriberQueryByParameter(DBColumnName.SUBSCRIBERS_PHONE);
		return executeQueryForSingleResult(query, phoneNumber);
	}

	@Override
	public Optional<Subscriber> findSubscriberById(String id) throws DaoException {
		String query = buildSelectSubscriberQueryByParameter(DBColumnName.SUBSCRIBERS_ID);
		return executeQueryForSingleResult(query, id);
	}
	

	@Override
	public List<Subscriber> findSubscriberListByUserId(String userId) throws DaoException {
		String query = buildSelectSubscriberQueryByParameter(DBColumnName.SUBSCRIBERS_USER_ID);
		return executeQuery(query, userId);
	}

	
	private String buildSelectSubscriberQueryByParameter(String parameter){
		String query = new StringBuilder("SELECT * , ")
				.append(DBColumnName.STATUSES_STATUS)
				.append(" FROM ").append(DBTableName.SUBSCRIBERS).append(" INNER JOIN ").append(DBTableName.STATUSES)
				.append(" ON ")
				.append(DBTableName.SUBSCRIBERS).append(".").append(DBColumnName.SUBSCRIBERS_STATUS_ID)
				.append(" = ")
				.append(DBTableName.STATUSES).append(".").append(DBColumnName.STATUSES_ID)
				.append(" WHERE ")
				.append(DBTableName.SUBSCRIBERS).append(".").append(parameter).append(" = ?")
				.toString();
	
				return query;
	}

	
	
	
	
	@Override
	public List<Subscriber> findSubscriberListByPassport(String passport) throws DaoException {
		//String query = makeSubscriberSelectQuery(DBTableName.CUSTOMERS, DBColumnName.CUSTOMERS_PASSPORT_NUMBER);
		//return executeQuery(query, passport);
		return null;
	}


	@Override
	public List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName) throws DaoException {
		List<Subscriber> result = null;
//		String query = null;
//		StringBuilder builder = new StringBuilder(BASIC_SUBSCRIBER_SELECT_QUERY)
//				.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_FIRST_NAME).append(" = ? AND ")
//				.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_LAST_NAME);
//		
//		if("".equals(middleName)) {
//			query = builder
//					.append(" = ?")
//					.toString();
//			result = executeQuery(query, firstName,  lastName);
//		} else {
//			query = builder
//					.append(" = ? AND ")
//					.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_MIDDLE_NAME)
//					.append(" = ?")
//					.toString();
//			result = executeQuery(query, firstName,  lastName, middleName);
//		}
		return result;
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
	public long save(Subscriber item) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}


}
