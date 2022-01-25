package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.SQLParametersHelper;
import by.epamjwd.mobile.dao.SubscriberDAO;
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

	/**
	 * Provides Subscriber retrieved by it's ID.
	 * 
	 * @param id - subscriber's ID 
	 * 
	 * @return Subscriber as an Optional value
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public Optional<Subscriber> findSubscriberById(long id) throws DaoException {
		String query = buildFindSubscriberQueryByParameter(DBColumnName.SUBSCRIBERS_ID);
		return executeQueryForSingleResult(query, id);
	}

	/**
	 * Provides the list of subscriber's (phone numbers) related to the same user.
	 * 
	 * @param userID - user's ID
	 * 
	 * @return list of Subscribers
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public List<Subscriber> findSubscriberListByUserId(long userId) throws DaoException {
		String query = buildFindSubscriberQueryByParameter(DBColumnName.SUBSCRIBERS_USER_ID);
		return executeQuery(query, userId);
	}

	/**
	 * Provides Subscriber retrieved by it's phone number.
	 * 
	 * @param phone - subscriber's phone number 
	 * 
	 * @return Subscriber as an Optional value
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public Optional<Subscriber> findSubscriberByPhone(String phone) throws DaoException {
		String query = buildFindSubscriberQueryByParameter(DBColumnName.SUBSCRIBERS_PHONE);
		return executeQueryForSingleResult(query, phone);
	}

	
	/**
	 * Provides the list of Subscriber's related to the same user by passport number.
	 * 
	 * @param passport - passport number
	 * 
	 * @return list of Subscribers
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public List<Subscriber> findSubscriberListByPassport(String passport) throws DaoException {
		String query = buildFindSubscriberQueryByUserParameter(DBColumnName.USERS_PASSPORT);
		return executeQuery(query, passport);
	}

	/**
	 * Provides the list of Subscriber's related to the same user by full name.
	 * 
	 * @param firstName - subscriber's first name
	 * 
	 * @param middleName - subscriber's middle name
	 * 
	 * @param lastName - subscriber's last name
	 * 
	 * @return list of Subscribers
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, 
			String lastName) throws DaoException {
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

	/**
	 * Adds to database one more Subscriber to existing User.
	 * 
	 * @param subscriber - new Subscriber
	 * 
	 * @return new subscriber's ID
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public long addNewSubscriberToExistingUser(Subscriber subscriber) throws DaoException {
		long subscriderId;
		
		Object[] params = SQLParametersHelper.provideNewSubscriberParameters(subscriber);
				
		subscriderId = executeInsertQuery(ADD_SUBSCRIBER_TO_EXISTING_USER, params);
		return subscriderId;
	}

	/**
	 * Updates Subscriber's data.
	 * 
	 * @param subscriber - Subscriber
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public void updateSubscriber(Subscriber subscriber) throws DaoException {
		Object[] params = SQLParametersHelper.provideUpdateSubscriberParameters(subscriber);
		executeUpdateQuery(UPDATE_SUBSCRIBER, params);
	}

	
	/**
	 * Builds query string, that can be used for "find" type requests.  
	 * 
	 */
	private String buildFindSubscriberQuery() {
		String query = new StringBuilder("SELECT * FROM ").append(DBTableName.SUBSCRIBERS).toString();
		return query;
	}
	
	/**
	 * Builds query string, that can be used for "find Subscriber by subscriber's parameter" type requests.  
	 * 
	 */
	private String buildFindSubscriberQueryByParameter(String parameter){
		String query = new StringBuilder(buildFindSubscriberQuery())
				.append(" WHERE ")
				.append(DBTableName.SUBSCRIBERS).append(".").append(parameter).append(" = ?")
				.toString();
		return query;
	}

	/**
	 * Builds query string, that can be used for "find Subscriber by user's parameter" type requests.  
	 * 
	 * <p> Note, that Subscriber entity contains User fields as well.
	 */
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
	
}
