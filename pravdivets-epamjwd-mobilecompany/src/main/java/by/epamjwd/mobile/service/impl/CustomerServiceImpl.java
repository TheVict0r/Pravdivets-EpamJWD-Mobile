package by.epamjwd.mobile.service.impl;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.CustomerDAO;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.repository.IDRepository;
import by.epamjwd.mobile.service.CustomerService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class CustomerServiceImpl implements CustomerService {

	/**
	 * Saves a new customer to the data storage.
	 * 
	 * <p>
	 * Customer consists from two connected entities:
	 * <p>
	 * (1) User - contains the data common for all users (subscribers and
	 * consultant) such as name, passport number, e-mail, password etc.
	 * <p>
	 * (2) Subscriber - contains only subscribers the data - phone number, tariff
	 * plan ID, account number, contract date etc.
	 * <p>
	 * Before the saving procedure starts the method checks are these two entities
	 * (user and subscriber) valid.
	 * 
	 * @param user       - user
	 * 
	 * @param subscriber - subscriber
	 * 
	 * @return the ID of subscriber added to the data storage
	 * 
	 * @throws ServiceException in the case when DaoException occurs while saving
	 *                          user and subscriber to the data storage
	 */
	@Override
	public long addNewCustomer(User user, Subscriber subscriber) throws ServiceException {
		CustomerDAO customerDao = DAOProvider.getInstance().getCustomerDAO();
		long subscriberID = IDRepository.ERROR_ID;

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
