package by.epamjwd.mobile.service;

import java.util.Optional;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface UserService {
	
	Optional<User> findUserByLogin(String login) throws ServiceException;
	Optional<User> findUserByEmail(String email) throws ServiceException;
	Optional<User> findUserById(String id) throws ServiceException;
	Optional<User> findUserByPhoneNumber(int phoneNumber) throws ServiceException;
	String findPathByUserType(User user);
}
