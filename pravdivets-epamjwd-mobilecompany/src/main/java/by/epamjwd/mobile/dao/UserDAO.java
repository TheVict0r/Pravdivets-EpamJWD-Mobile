package by.epamjwd.mobile.dao;

import java.util.Optional;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface UserDAO {
	
	Optional<User> findUserByEmail(String email) throws DaoException;
	
	Optional<User> findUserByPhone(String phone) throws DaoException;
	
	Optional<User> findUserById(long id) throws DaoException;
	
	Optional<User> findUserByPassport(String passport) throws DaoException;
	
	long addUser(User user) throws DaoException;
	
	void updateUser(User user) throws DaoException;
	
}
