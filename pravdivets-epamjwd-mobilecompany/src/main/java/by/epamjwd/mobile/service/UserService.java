package by.epamjwd.mobile.service;


import java.util.Optional;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface UserService {
	
	Optional<User> findUserByLogin(String login) throws ServiceException;
	Optional<User> findUserById(long id) throws ServiceException;
	Optional<User> findUserByPassport(String passport) throws ServiceException;
	Optional<User> findUserByEmail(String email) throws ServiceException;
	Optional<User> findUserByPhone(int phoneNumber) throws ServiceException;
	boolean isPasswordCorrect(User user, String password);
	long addNewUser(User user) throws ServiceException;
}
