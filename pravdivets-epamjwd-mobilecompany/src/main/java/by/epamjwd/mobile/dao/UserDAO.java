package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface UserDAO {
	
	boolean authorization (String login, String passwordHash);
	void registration(Subscriber newAbonent);
	Subscriber lookAbonent(int idAbonent);
	void activateService(int serviceId);
	void deActivateService(int serviceId);
	void switchServiceParameters(String oldServiceId, String newServiceId);
	void changePhoneNumber(String oldPhoneNumber, String newPhoneNumber);
	void pause();
	Optional<User> findUserByEmail(String email) throws DaoException;
	Optional<User> findUserByPhone(int phoneNumber) throws DaoException;
	Optional<User> findUserById(long id) throws DaoException;
	Optional<User> findUserByPassport(String passport) throws DaoException;
	List<User> findUsersListByFullName(String firstName, String middleName, String lastName) throws DaoException;
	long addUser(User user) throws DaoException;
	
}
