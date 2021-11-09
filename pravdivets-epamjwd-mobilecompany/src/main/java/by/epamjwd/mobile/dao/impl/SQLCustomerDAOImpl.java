package by.epamjwd.mobile.dao.impl;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.CustomerDAO;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.SubscriberDAO;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.exception.DaoException;

public class SQLCustomerDAOImpl implements CustomerDAO{

	//DAOProvider provider = DAOProvider.getInstance();
	UserDAO userDAO = new SQLUserDAOImpl();
	SubscriberDAO subscriberDAO = new SQLSubscriberDAOImpl();
	
	
	@Override
	public long addNewCustomer(User user, Subscriber subscriber) throws DaoException {
		long sunscriberID;
		long userID;
		
		userID = userDAO.addUser(user);
		subscriber.setUserId(userID);
		sunscriberID = subscriberDAO.addNewSubscriberToExistingUser(subscriber);
		
		return sunscriberID;
	}

}
