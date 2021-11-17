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
			user = findUserByPhone(login);
		}
		return user;
	}
	
	@Override
	public Optional<User> findUserById(long id) throws ServiceException {
		Optional<User> user = Optional.empty();
		try {
			user = userDao.findUserById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public Optional<User> findUserByEmail(String email) throws ServiceException {
		Optional<User> user = Optional.empty();
		try {
			user = userDao.findUserByEmail(email);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public Optional<User> findUserByPassport(String passport) throws ServiceException {
		Optional<User> user = Optional.empty();
		try {
			user = userDao.findUserByPassport(passport);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return user;
	}
	
	@Override
	public Optional<User> findUserByPhone(String phone) throws ServiceException {
		Optional<User> user = Optional.empty();
		if (InputDataValidator.isPhone(phone)) {
			try {
				user = userDao.findUserByPhone(phone);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return user;
	}

	@Override
	public boolean isPasswordCorrect(User user, String password) {
		boolean result = false;
		if(password.isBlank() || password == null || user == null) {
			return false;
		}
		
		if (InputDataValidator.isUserValid(user)) {
			result = HashGenerator.generateHash(password).equals(user.getPassword());
		}
		return result;
	}

	@Override
	public boolean doesPhoneExist(String phone) throws ServiceException {
		return findUserByPhone(phone).isPresent();
	}	
	
	@Override
	public boolean isPasswordCorrect(String password) {
		return InputDataValidator.isPassword(password);
	}	
	
	@Override
	public boolean isSignupRequired(String phone) throws ServiceException {
		boolean result = false;
		Optional<User> userOptional = findUserByPhone(phone);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			result = user.getPassword().isEmpty();
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

	@Override
	public void updateUser(User user) throws ServiceException {
		if (InputDataValidator.isUserValid(user)) {
			try {
				userDao.updateUser(user);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
	}

	@Override
	public void signup(String phone, String password) throws ServiceException {
		Optional<User> userOptional = findUserByPhone(phone);
		if(userOptional.isPresent()) {
			User user = userOptional.get();
			String passwordHash = HashGenerator.generateHash(password);
			user.setPassword(passwordHash);
			updateUser(user);
		}
	}

	
}
