package by.epamjwd.mobile.service.impl;

import java.util.Optional;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;
import by.epamjwd.mobile.util.HashGenerator;

public class UserServiceImpl implements UserService {
	private static final long ERROR_ID = -1;
	
	DAOProvider provider = DAOProvider.getInstance();
	UserDAO userDao = provider.getUserDAO();

	@Override
	public Optional<User> findUserByLogin(String login) throws ServiceException {
		Optional<User>  user = Optional.empty();
		if (InputDataValidator.isEmail(login)) {
			user = findUserByEmail(login);
		} else if (InputDataValidator.isPhone(login)) {
			int phone = Integer.parseInt(login);
			user = findUserByPhone(phone);
		}
		return user;
	}
	
	@Override
	public Optional<User> findUserById(long id) throws ServiceException {
		Optional<User> user;
		try {
			user = userDao.findUserById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
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
	public Optional<User> findUserByPassport(String passport) throws ServiceException {
		Optional<User> user;
		try {
			user = userDao.findUserByPassport(passport);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return user;
	}
	
	@Override
	public Optional<User> findUserByPhone(int phone) throws ServiceException {
		Optional<User> user;
		try {
			user = userDao.findUserByPhone(phone);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public boolean isPasswordCorrect(User user, String password) {
		boolean result = false;
		if (InputDataValidator.isUserValid(user)) {
			result = user.getPassword().equals(HashGenerator.generateHash(password));
		}
		return result;
	}

	@Override
	public long addNewUser(User user) throws ServiceException {
		long userId = ERROR_ID;
		if (InputDataValidator.isUserValid(user)) {
			try {
				userId = userDao.addUser(user);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return userId;
	}

	
}
