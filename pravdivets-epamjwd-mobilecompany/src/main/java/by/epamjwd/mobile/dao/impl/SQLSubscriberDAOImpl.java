package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.dao.SubscriberDAO;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLSubscriberDAOImpl extends AbstractDao<Subscriber> implements SubscriberDAO{

	public final static String COMMA = ", ";
	public final static String QUESTION_MARK = "=?, ";
	
	public static final String ADD_SUBSCRIBER_TO_EXISTING_USER = "INSERT INTO " + 
			 DBTableName.SUBSCRIBERS + " (" + 
			DBColumnName.SUBSCRIBERS_CONTRACT_DATE + COMMA + 
			DBColumnName.SUBSCRIBERS_ACCOUNT + COMMA + 
			DBColumnName.SUBSCRIBERS_PHONE + COMMA + 
			DBColumnName.SUBSCRIBERS_STATUS_DATE + COMMA + 
			DBColumnName.SUBSCRIBERS_STATUS_ID + COMMA + 
			DBColumnName.SUBSCRIBERS_PLAN_ID + COMMA + 
			DBColumnName.SUBSCRIBERS_USER_ID + ") VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	public static final String UPDATE_SUBSCRIBER = "UPDATE subscribers SET phone=?, status_date=?, status_id=?, plan_id=? WHERE id=?";

	public SQLSubscriberDAOImpl() {
        super(RowMapperFactory.getInstance().getSubscriberRowMapper(), DBTableName.SUBSCRIBERS);
	}


	@Override
	public Optional<Subscriber> findSubscriberByPhone(String phone) throws DaoException {
		String query = buildFindSubscriberQueryByParameter(DBColumnName.SUBSCRIBERS_PHONE);
		return executeQueryForSingleResult(query, phone);
	}

	@Override
	public Optional<Subscriber> findSubscriberById(long id) throws DaoException {
		String query = buildFindSubscriberQueryByParameter(DBColumnName.SUBSCRIBERS_ID);
		return executeQueryForSingleResult(query, id);
	}
	
	@Override
	public List<Subscriber> findSubscriberListByUserId(long userId) throws DaoException {
		String query = buildFindSubscriberQueryByParameter(DBColumnName.SUBSCRIBERS_USER_ID);
		return executeQuery(query, userId);
	}

	@Override
	public List<Subscriber> findSubscriberListByPassport(String passport) throws DaoException {
		String query = buildFindSubscriberQueryByUserParameter(DBColumnName.USERS_PASSPORT);
		return executeQuery(query, passport);
	}
	
	private String buildFindSubscriberQuery(){
		String query = new StringBuilder("SELECT * FROM ").append(DBTableName.SUBSCRIBERS)
				.toString();
	
				return query;
	}
	
	private String buildFindSubscriberQueryByParameter(String parameter){
		String query = new StringBuilder(buildFindSubscriberQuery())
				.append(" WHERE ")
				.append(DBTableName.SUBSCRIBERS).append(".").append(parameter).append(" = ?")
				.toString();
		return query;
	}

	private String buildFindSubscriberQueryByUserParameter(String userParameter){
		String query = new StringBuilder(buildFindSubscriberQuery())
				.append(" INNER JOIN ").append(DBTableName.USERS)
				.append(" ON ")
				.append(DBTableName.SUBSCRIBERS).append(".").append(DBColumnName.SUBSCRIBERS_USER_ID)
				.append(" = ")
				.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_ID)
				.append(" WHERE ")
				.append(DBTableName.USERS).append(".").append(userParameter)
				.append(" = ?")
				.toString();
		return query;
	}
	
	@Override
	public List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName) throws DaoException {
		List<Subscriber> result = null;
		String query = null;
		
		StringBuilder builder = new StringBuilder(buildFindSubscriberQueryByUserParameter(DBColumnName.USERS_FIRST_NAME))
				.append(" AND ")
				.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_LAST_NAME);
		
		if("".equals(middleName)) {
			query = builder
					.append(" = ?")
					.toString();
			result = executeQuery(query, firstName,  lastName);
		} else {
			query = builder
					.append(" = ? AND ")
					.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_MIDDLE_NAME)
					.append(" = ?")
					.toString();
			result = executeQuery(query, firstName,  lastName, middleName);
		}
				
		return result;
	}		
	
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


	@Override
	public long addNewSubscriberToExistingUser(Subscriber subscriber) throws DaoException {
		long subscriderId;
		
		Object[] params = SQLParametersHelper.provideNewSubscriberParameters(subscriber);
				
		subscriderId = executeInsertQuery(ADD_SUBSCRIBER_TO_EXISTING_USER, params);
		return subscriderId;
	}

	@Override
	public void updateSubscriber(Subscriber subscriber) throws DaoException {
		Object[] params = SQLParametersHelper.provideUpdateSubscriberParameters(subscriber);
		executeUpdateQuery(UPDATE_SUBSCRIBER, params);
	}


	
	
}
