package by.epamjwd.mobile.service.impl;

import java.util.NoSuchElementException;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.LoginChecker;

public class UserServiceImpl implements UserService {

	DAOProvider provider = DAOProvider.getInstance();
	UserDAO userDao = provider.getUserDAO();

	@Override
	public User findUserByLogin(String login) throws ServiceException {
		User user = null;
		if (LoginChecker.isEmail(login)) {
			user = findUserByEmail(login);
		} else if (LoginChecker.isPhoneNumber(login)) {
			int phoneNumber = Integer.parseInt(login);
			user = findUserByPhoneNumber(phoneNumber);
		}
		return user;
	}

	@Override
	public User findUserByEmail(String email) throws ServiceException {
		User user;
		try {
			user = userDao.findUserByEmail(email).get();
		} catch (DaoException e) {
			throw new ServiceException(e);
		} catch (NoSuchElementException e) {
			throw new ServiceException("The Optional<User> contains null for email  - " + email, e);
		}
		return user;
	}

	@Override
	public User findUserByPhoneNumber(int phoneNumber) throws ServiceException {
		User user;
		try {
			user = userDao.findUserByPhoneNumber(phoneNumber).get();
		} catch (DaoException e) {
			throw new ServiceException(e);
		} catch (NoSuchElementException e) {
			throw new ServiceException("The Optional<User> contains null for phone number - " + phoneNumber, e);
		}
		return user;
	}

	@Override
	public User findUserById(String id) throws ServiceException {
		User user;
		try {
			user = userDao.findUserById(id).get();
		} catch (DaoException e) {
			throw new ServiceException(e);
		} catch (NoSuchElementException e) {
			throw new ServiceException("The Optional<User> contains null for ID - " + id, e);
		}
		return user;
	}

	@Override
	public boolean isPasswordValid(User user, String hashPassword) {
		if (user == null) {
			return false;
		}
		return user.getPassword().equals(hashPassword);
	}

}
