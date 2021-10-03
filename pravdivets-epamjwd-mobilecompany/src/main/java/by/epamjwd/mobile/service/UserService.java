package by.epamjwd.mobile.service;

import java.util.Optional;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface UserService {
	
	Optional<User> getUserByLogin(String email) throws ServiceException;
	Optional<User> getUserByEmail(String email) throws ServiceException;
	Optional<User> getUserByPhoneNumber(int phoneNumber) throws ServiceException;
}
