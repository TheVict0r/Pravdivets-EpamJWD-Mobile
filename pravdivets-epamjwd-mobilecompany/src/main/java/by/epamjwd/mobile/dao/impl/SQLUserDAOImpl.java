package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.AbstractDao;
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
		Optional<User> user = Optional.empty();
		String query = buildFindUserQueryByParameter(DBColumnName.USERS_ID);
		user = executeQueryForSingleResult(query, id);
		return user;
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
		Optional<User> user = Optional.empty();
		String query = buildFindUserQueryByParameter(DBColumnName.USERS_EMAIL);
		user = executeQueryForSingleResult(query, email);
		return user;
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
		Optional<User> user = Optional.empty();
		String query = buildFindUserQueryByParameter(DBColumnName.USERS_PASSPORT);
		user = executeQueryForSingleResult(query, passport);
		return user;
	}

	
	/**
	 * Provides the list of Users retrieved by their full name (first name, middle name and last name).
	 * 
	 * <p> Middle name can be missing or blank
	 * 
	 * @param firstName - user's first name 
	 * 
	 * @param middleName - user's middle name 
	 * 
	 * @param lastName - user's last name 
	 * 
	 * @return list of Users
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public List<User> findUsersListByFullName(String firstName, String middleName, String lastName) throws DaoException {
		List<User> result = null;
		String query = null;
		StringBuilder builder = new StringBuilder(buildFindUserQuery())
				.append(" WHERE ")
				.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_FIRST_NAME)
				.append(" = ? AND ")
				.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_LAST_NAME);
		
		if("".equals(middleName) || middleName.isBlank()) {
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
		Optional<User> user = Optional.empty();
		
		String query = new StringBuilder(buildFindUserQuery())
				.append(" INNER JOIN ")
				.append(DBTableName.SUBSCRIBERS)
				.append(" ON ")
				.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_ID)
				.append(" = ")
				.append(DBTableName.SUBSCRIBERS).append(".").append(DBColumnName.SUBSCRIBERS_USER_ID)
				.append(" WHERE ")
				.append(DBTableName.SUBSCRIBERS).append(".").append(DBColumnName.SUBSCRIBERS_PHONE)
				.append(" = ?")				
				.toString();

		user = executeQueryForSingleResult(query, phone);
		return user;
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
		long userId;
		Object[] params = SQLParametersHelper.provideNewUserParameters(user);
		userId = executeInsertQuery(ADD_NEW_USER, params);
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
	 * Builds basic string query for searching the users. 
	 * 
	 * @return string query
	 */
	private String buildFindUserQuery(){
		String query = new StringBuilder("SELECT * FROM ").append(DBTableName.USERS).toString();
		return query;
	}
	
	/**
	 * Builds string query for searching the users by parameter.
	 * 
	 * @param parameter - parameter for searching by
	 * 
	 * @return string query
	 */
	private String buildFindUserQueryByParameter(String parameter) {
		String query = new StringBuilder(buildFindUserQuery())
				.append(" WHERE ")
				.append(DBTableName.USERS).append(".").append(parameter)
				.append(" = ?")
				.toString();
		return query;
	}
	
}

