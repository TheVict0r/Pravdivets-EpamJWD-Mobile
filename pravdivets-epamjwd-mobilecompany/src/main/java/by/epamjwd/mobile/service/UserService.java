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
	long addUser(User user) throws ServiceException;
	void updateUser(User user) throws ServiceException;
	void updatePassword(User user, String password) throws ServiceException;
	void updatePassword(String phone, String password) throws ServiceException;
	int sendCodeToUserByMail(String phone) throws ServiceException;
	boolean isEmailBooked(String email) throws ServiceException;
	boolean isPassportBooked(String passport) throws ServiceException;
	User buildConsultantUser(String firstName, String middleName, String lastName, String password, String passport,
			String email);
	User buildSubscriberUser(String firstName, String middleName, String lastName, String passport, String email);
}
