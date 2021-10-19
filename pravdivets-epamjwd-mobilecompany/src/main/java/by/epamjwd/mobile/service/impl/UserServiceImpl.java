package by.epamjwd.mobile.service.impl;

import java.util.Optional;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.InputValueChecker;

public class UserServiceImpl implements UserService {

	DAOProvider provider = DAOProvider.getInstance();
	UserDAO userDao = provider.getUserDAO();

	@Override
	public Optional<User> findUserByLogin(String login) throws ServiceException {
		Optional<User>  user = Optional.empty();
		if (InputValueChecker.isEmail(login)) {
			user = findUserByEmail(login);
		} else if (InputValueChecker.isPhoneNumber(login)) {
			int phoneNumber = Integer.parseInt(login);
			user = findUserByPhoneNumber(phoneNumber);
		}
		return user;
	}

	@Override
	public Optional<User> findUserByEmail(String email) throws ServiceException {
		Optional<User> user;
		try {
			user = userDao.findUserByEmail(email);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public Optional<User>  findUserByPhoneNumber(int phoneNumber) throws ServiceException {
		Optional<User> user;
		try {
			user = userDao.findUserByPhoneNumber(phoneNumber);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public Optional<User>  findUserById(String id) throws ServiceException {
		Optional<User> user;
		try {
			user = userDao.findUserById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
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
