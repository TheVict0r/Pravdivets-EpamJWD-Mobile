package by.epamjwd.mobile.service;


import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface UserService {
	
	User findUserByEmail(String email) throws ServiceException;
	User  findUserById(String id) throws ServiceException;
	User  findUserByPhoneNumber(int phoneNumber) throws ServiceException;
	String findPathByUserType(User user);
	boolean isEmail(String anyString);
	boolean isPasswordValid(User user, char[] password);
}
