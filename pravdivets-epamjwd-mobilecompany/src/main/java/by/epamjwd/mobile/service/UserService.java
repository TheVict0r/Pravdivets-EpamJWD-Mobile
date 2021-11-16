package by.epamjwd.mobile.service;


import java.util.Optional;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface UserService {
	
	Optional<User> findUserByLogin(String login) throws ServiceException;
	Optional<User> findUserById(long id) throws ServiceException;
	Optional<User> findUserByPassport(String passport) throws ServiceException;
	Optional<User> findUserByEmail(String email) throws ServiceException;
	Optional<User> findUserByPhone(String phone) throws ServiceException;
	boolean isPasswordCorrect(User user, String password);
	long addNewUser(User user) throws ServiceException;
	void updateUser(User user) throws ServiceException;
	boolean isSignupRequired(String phone) throws ServiceException;
	boolean isPhoneExist(String phoneString) throws ServiceException;
	void signup(String phone, String password1) throws ServiceException;
	boolean isPasswordCorrect(String password);
}
