package by.epamjwd.mobile.dao.impl;

import java.util.Optional;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.SQLParametersHelper;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLUserDAOImpl extends AbstractDao<User> implements UserDAO{

	public final static String COMMA = ", ";
	public final static String QUESTION_MARK = "=?, ";
	
	public final static String ADD_NEW_USER = "INSERT INTO " + 
			 DBTableName.USERS + "(" + 
			DBColumnName.USERS_PASSWORD + COMMA + 
			DBColumnName.USERS_FIRST_NAME + COMMA + 
			DBColumnName.USERS_MIDDLE_NAME + COMMA + 
			DBColumnName.USERS_LAST_NAME + COMMA + 
			DBColumnName.USERS_PASSPORT + COMMA + 
			DBColumnName.USERS_EMAIL + COMMA + 
			DBColumnName.USERS_ROLE_ID + 
						") VALUES (?, ?, ?, ?, ?, ?, ?)";

	public final static String UPDATE_USER = "UPDATE " + 
			DBTableName.USERS + " SET "+ 
			DBColumnName.USERS_PASSWORD + QUESTION_MARK + 
			DBColumnName.USERS_FIRST_NAME + QUESTION_MARK + 
			DBColumnName.USERS_MIDDLE_NAME + QUESTION_MARK +  
			DBColumnName.USERS_LAST_NAME + QUESTION_MARK + 
			DBColumnName.USERS_PASSPORT + QUESTION_MARK + 
			DBColumnName.USERS_EMAIL + "=? " +  
			"WHERE " + DBColumnName.USERS_ID + "=?";

	
	public SQLUserDAOImpl() {
        super(RowMapperFactory.getInstance().getUserRowMapper(), DBTableName.USERS);
	}

	/**
	 * Provides User retrieved by it's ID.
	 * 
	 * @param id - user's ID 
	 * 
	 * @return User as an Optional value
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public Optional<User> findUserById(long id) throws DaoException {
		String query = buildFindUserQueryByParameter(DBColumnName.USERS_ID);
		return executeQueryForSingleResult(query, id);
	}

	/**
	 * Provides User retrieved by it's e-mail. 
	 * 
	 * @param email - user's e-mail
	 * 
	 * @return User as an Optional value
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public Optional<User> findUserByEmail(String email) throws DaoException {
		String query = buildFindUserQueryByParameter(DBColumnName.USERS_EMAIL);
		return executeQueryForSingleResult(query, email);
	}

	/**
	 * Provides User retrieved by it's passport number. 
	 * 
	 * @param passport - user's passport number
	 * 
	 * @return User as an Optional value
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public Optional<User> findUserByPassport(String passport) throws DaoException {
		String query = buildFindUserQueryByParameter(DBColumnName.USERS_PASSPORT);
		return executeQueryForSingleResult(query, passport);
	}

	
	/**
	 * Provides User retrieved by it's phone number. 
	 * 
	 * @param phone - user's phone number
	 * 
	 * @return User as an Optional value
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public Optional<User> findUserByPhone(String phone) throws DaoException {
		String query = "SELECT * FROM " + DBTableName.USERS + 
				" INNER JOIN " + DBTableName.SUBSCRIBERS + 
				" ON " + 
				DBTableName.USERS + "." + DBColumnName.USERS_ID + 
				" = " + 
				DBTableName.SUBSCRIBERS + "." + DBColumnName.SUBSCRIBERS_USER_ID + 
				" WHERE " +
				DBTableName.SUBSCRIBERS + "." + DBColumnName.SUBSCRIBERS_PHONE + " = ?";
		
		return executeQueryForSingleResult(query, phone);
	}

	/**
	 * Adds User to database.
	 * 
	 * @param User - new User
	 * 
	 * @return new User's ID
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public long addUser(User user) throws DaoException {
		Object[] params = SQLParametersHelper.provideNewUserParameters(user);
		long userId = executeInsertQuery(ADD_NEW_USER, params);
		return userId;
	}
	
	/**
	 * Updates User's data.
	 * 
	 * @param user - User
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public void updateUser(User user) throws DaoException {
		Object[] params = SQLParametersHelper.provideUpdateUserParameters(user);
		executeUpdateQuery(UPDATE_USER, params);
	}

	/**
	 * Builds string query for searching the users by parameter.
	 * 
	 * @param parameter - parameter for searching by
	 * 
	 * @return string query
	 */
	private String buildFindUserQueryByParameter(String parameter) {
		return "SELECT * FROM " + DBTableName.USERS + " WHERE " + parameter + " = ?";
		
	}
	
}

