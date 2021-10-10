package by.epamjwd.mobile.service;


import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface UserService {
	
	User findUserByLogin(String login) throws ServiceException;
	User findUserByEmail(String email) throws ServiceException;
	User findUserById(String id) throws ServiceException;
	User findUserByPhoneNumber(int phoneNumber) throws ServiceException;
	boolean isPasswordValid(User user, char[] password);
}
