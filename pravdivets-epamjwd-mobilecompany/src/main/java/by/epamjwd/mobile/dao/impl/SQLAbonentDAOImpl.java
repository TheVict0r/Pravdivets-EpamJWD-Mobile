package by.epamjwd.mobile.dao.impl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.AbonentDAO;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLAbonentDAOImpl extends AbstractDao<Abonent> implements AbonentDAO{

	public SQLAbonentDAOImpl() {
        super(RowMapperFactory.getInstance().getAbonentRowMapper(), DBTableName.ABONENTS);
	}

	public final static String GET_ABONENT_BY_PHONE_NUMBER = "SELECT Abonents.id, Abonents.contract_date, Users.password, Users.first_name, Users.middle_name, Users.last_name, Customers.passport_number, Users.email,  Customers.home_address, Abonents.account, Abonents.phone_number, Tariff_Plans.name, Status.status,  Abonents.status_date FROM Users INNER JOIN Customers ON Users.id = Customers.users_id INNER JOIN Abonents ON Customers.id = Abonents.customers_id INNER JOIN Tariff_Plans ON Abonents.tariff_plan_id = Tariff_Plans.id INNER JOIN Status ON Abonents.status_id = Status.id WHERE Abonents.phone_number = ?";
	
	@Override
	public Optional<Abonent> getAbonentByPhoneNumber(int phoneNumber) throws DaoException {
		return executeQueryForSingleResult(GET_ABONENT_BY_PHONE_NUMBER, phoneNumber);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> getUserByPhoneNumber(int phoneNumber) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long save(Abonent item) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

}
