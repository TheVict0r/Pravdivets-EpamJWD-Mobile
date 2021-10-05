package by.epamjwd.mobile.dao;

import java.util.Optional;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface UserDAO {
	
	boolean authorization (String login, String passwordHash);
	void registration(Abonent newAbonent);
	Abonent lookAbonent(int idAbonent);
	void activateService(int serviceId);
	void deActivateService(int serviceId);
	void switchServiceParameters(String oldServiceId, String newServiceId);
	void changePhoneNumber(String oldPhoneNumber, String newPhoneNumber);
	void pause();
	Optional<User> getUserByEmail(String email) throws DaoException;
	Optional<User> getUserByPhoneNumber(int phoneNumber) throws DaoException;
	
}
