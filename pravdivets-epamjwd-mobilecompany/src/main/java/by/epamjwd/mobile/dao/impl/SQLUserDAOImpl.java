package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
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

	@Override
	public Optional<User> findUserById(long id) throws DaoException {
		Optional<User> user = Optional.empty();
		String query = buildFindUserQueryByParameter(DBColumnName.USERS_ID);
		user = executeQueryForSingleResult(query, id);
		return user;
	}
	
	@Override
	public Optional<User> findUserByEmail(String email) throws DaoException {
		Optional<User> user = Optional.empty();
		String query = buildFindUserQueryByParameter(DBColumnName.USERS_EMAIL);
		user = executeQueryForSingleResult(query, email);
		return user;
	}
	
	@Override
	public Optional<User> findUserByPassport(String passport) throws DaoException {
		Optional<User> user = Optional.empty();
		String query = buildFindUserQueryByParameter(DBColumnName.USERS_PASSPORT);
		user = executeQueryForSingleResult(query, passport);
		return user;
	}


	@Override
	public List<User> findUsersListByFullName(String firstName, String middleName, String lastName) throws DaoException {
		List<User> result = null;
		String query = null;
		StringBuilder builder = new StringBuilder(buildFindUserQuery())
				.append(" WHERE ")
				.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_FIRST_NAME)
				.append(" = ? AND ")
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

	private String buildFindUserQuery(){
		String query = new StringBuilder("SELECT * FROM ").append(DBTableName.USERS).toString();
		return query;
	}
	
	private String buildFindUserQueryByParameter(String parameter) {
		String query = new StringBuilder(buildFindUserQuery())
				.append(" WHERE ")
				.append(DBTableName.USERS).append(".").append(parameter)
				.append(" = ?")
				.toString();
		return query;
	}

	@Override
	public long addUser(User user) throws DaoException {
		long userId;
		Object[] params = SQLParametersHelper.provideNewUserParameters(user);
		userId = executeInsertQuery(ADD_NEW_USER, params);
		return userId;
	}
	

	@Override
	public void updateUser(User user) throws DaoException {
		Object[] params = SQLParametersHelper.provideUpdateUserParameters(user);
		executeUpdateQuery(UPDATE_USER, params);
	}

	
	
	@Override
	public boolean authorization(String login, String passwordHash) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registration(Subscriber newAbonent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Subscriber lookAbonent(int idAbonent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void activateService(int serviceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deActivateService(int serviceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void switchServiceParameters(String oldServiceId, String newServiceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePhoneNumber(String oldPhoneNumber, String newPhoneNumber) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}


	

	@Override
	public long save(User item) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
	
}
