package by.epamjwd.mobile.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.Role;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.connectionpool.ConnectionPool;
import by.epamjwd.mobile.dao.connectionpool.exception.ConnectionPoolException;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLUserDAOImpl extends AbstractDao<User> implements UserDAO{

	public SQLUserDAOImpl() {
        super(RowMapperFactory.getInstance().getUserRowMapper(), DBTableName.USERS);
	}

	private final static Logger LOGGER = LogManager.getLogger(SQLUserDAOImpl.class);

	public final static String GET_USER_BY_EMAIL = "SELECT Users.ID, Users.email, Users.password, Users.first_name, Users.middle_name, Users.last_name, Roles.role FROM Users INNER JOIN Roles ON Users.roles_id = Roles.id WHERE Users.email = ?";
	
	
	
	@Override
	public Optional<User> getUserByPhoneNumber(int phoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public boolean authorization(String login, String passwordHash) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registration(Abonent newAbonent) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Abonent lookAbonent(int idAbonent) {
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
	public Optional<User> getUserByEmail(String email) throws DaoException {
		return executeQueryForSingleResult(GET_USER_BY_EMAIL, email);
		
	}



	@Override
	public long save(User item) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
}
