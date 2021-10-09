package by.epamjwd.mobile.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.HashGenerator;

public class UserServiceImpl implements UserService {

	public static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";

	DAOProvider provider = DAOProvider.getInstance();
	UserDAO userDao = provider.getUserDAO();

	@Override
	public User findUserById(String id) throws ServiceException {
		User user;
		try {
			user = userDao.findUserById(id).get();
		} catch (DaoException e) {
			throw new ServiceException(e);
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
		}
		return user;
	}

	@Override
	public String findPathByUserType(User user) {
		if (user == null) {
			return PagePath.LOGIN_REDIRECT;
		}

		String path = null;

		switch (user.getRole()) {
		case CUSTOMER:
			path = PagePath.CUSTOMER_REDIRECT;
			break;
		case CONSULTANT:
			path = PagePath.CONSULTANT_REDIRECT;
			break;
		case ADMIN:
			path = PagePath.ADMIN_REDIRECT;
			break;
		default:
			path = PagePath.LOGIN_REDIRECT;
		}
		return path;
	}

	@Override
	public boolean isEmail(String anyString) {
		Pattern validEmailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = validEmailPattern.matcher(anyString);
		return matcher.find();
	}

	@Override
	public boolean isPasswordValid(User user, char[] password) {
		HashGenerator hashGenerator = new HashGenerator();
		String hashPassword = hashGenerator.generateHash(String.valueOf(password));
		return user.getPassword().equals(hashPassword);

		// return true;
		// for testing simplicity
	}

}
