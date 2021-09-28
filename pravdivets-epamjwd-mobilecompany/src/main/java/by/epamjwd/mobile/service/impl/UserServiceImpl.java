package by.epamjwd.mobile.service.impl;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.service.UserService;

public class UserServiceImpl implements UserService{

	DAOProvider provider = DAOProvider.getInstance();
	UserDAO userDao = provider.getUserDAO();

	
	@Override
	public User getUserByEmail(String email) {
		User result = userDao.getUserByEmail(email);
		return result;
	}

}
