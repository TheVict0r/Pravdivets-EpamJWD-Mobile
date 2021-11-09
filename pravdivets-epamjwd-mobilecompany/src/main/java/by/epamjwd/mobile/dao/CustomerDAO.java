package by.epamjwd.mobile.dao;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface CustomerDAO {
	
	long addNewCustomer(User user, Subscriber subscriber) throws DaoException;
	
}
