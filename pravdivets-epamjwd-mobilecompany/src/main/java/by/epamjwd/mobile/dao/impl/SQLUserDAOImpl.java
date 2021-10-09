package by.epamjwd.mobile.dao.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLUserDAOImpl extends AbstractDao<User> implements UserDAO{

	public SQLUserDAOImpl() {
        super(RowMapperFactory.getInstance().getUserRowMapper(), DBTableName.USERS);
	}


	public final static String FIND_USER_BY_EMAIL        = "SELECT Users.ID, Users.email, Users.password, Users.first_name, Users.middle_name, Users.last_name, Roles.role FROM Users INNER JOIN Roles ON Users.roles_id = Roles.id WHERE Users.email = ?";
	public final static String FIND_USER_BY_ID           = "SELECT Users.ID, Users.email, Users.password, Users.first_name, Users.middle_name, Users.last_name, Roles.role FROM Users INNER JOIN Roles ON Users.roles_id = Roles.id WHERE Users.id = ?";
	public final static String FIND_USER_BY_PHONE_NUMBER = "SELECT Users.ID, Users.email, Users.password, Users.first_name, Users.middle_name, Users.last_name, Roles.role FROM Users INNER JOIN Roles ON Users.roles_id = Roles.id INNER JOIN Customers ON Users.id = Customers.users_id INNER JOIN Abonents ON Customers.id = Abonents.customers_id WHERE Abonents.phone_number = ?";
	
	
	
	@Override
	public Optional<User> findUserByPhoneNumber(int phoneNumber) throws DaoException {
		return executeQueryForSingleResult(FIND_USER_BY_PHONE_NUMBER, phoneNumber);
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
	public Optional<User> findUserByEmail(String email) throws DaoException {
		return executeQueryForSingleResult(FIND_USER_BY_EMAIL, email);
		
	}

	@Override
	public Optional<User> findUserById(String id) throws DaoException {
		return executeQueryForSingleResult(FIND_USER_BY_ID, id);
		
	}
	

	@Override
	public long save(User item) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	
}
