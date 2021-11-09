package by.epamjwd.mobile.service.impl;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.CustomerDAO;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.CustomerService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class CustomerServiceImpl implements CustomerService  {

	private static final long ERROR_ID = -1L;
	
	DAOProvider provider = DAOProvider.getInstance();
	CustomerDAO customerDao = provider.getCustomerDAO();

	@Override
	public long addNewCustomer(User user, Subscriber subscriber) throws ServiceException {
		long subscriberID = ERROR_ID;
		if (InputDataValidator.isUserValid(user) && InputDataValidator.isSubscriberValid(subscriber)) {
			try {
				subscriberID = customerDao.addNewCustomer(user, subscriber);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return subscriberID;
	}

}
