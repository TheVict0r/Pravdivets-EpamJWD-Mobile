package by.epamjwd.mobile.service.impl;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.controller.repository.PagePath;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.UserService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	public static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	public static final String PHONE_NUMBER_REGEX = "\\d{9}";

	DAOProvider provider = DAOProvider.getInstance();
	UserDAO userDao = provider.getUserDAO();

	@Override
	public Optional<User> getUserByLogin(String login) throws ServiceException {
		Optional<User> user = Optional.ofNullable(null);
		if(isPhoneNumber(login)) {
			user = getUserByPhoneNumber(Integer.parseInt(login));
		} else if(isEmail(login)) {
			user = getUserByEmail(login);
		} 
		return user;
	}

	@Override
	public Optional<User> getUserByEmail(String email) throws ServiceException {
		Optional<User> result;
		try {
			result = userDao.getUserByEmail(email);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public Optional<User> getUserByPhoneNumber(int phoneNumber) throws ServiceException {
		Optional<User> result;
		try {
			result = userDao.getUserByPhoneNumber(phoneNumber);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public String getPathByUserType(User user) {
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
	
	
	private boolean isPhoneNumber(String anyString) {
		Pattern validEmailPattern = Pattern.compile(PHONE_NUMBER_REGEX);
		Matcher matcher = validEmailPattern.matcher(anyString);
		return matcher.find();
	}

	private boolean isEmail(String anyString) {
		Pattern validEmailPattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
		Matcher matcher = validEmailPattern.matcher(anyString);
		return matcher.find();
	}

}
