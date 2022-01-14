package by.epamjwd.mobile.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.SubscriberDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class SubscriberServiceImpl implements SubscriberService {
	private static final long ERROR_ID = -1;
	
	SubscriberDAO subscriberDao = DAOProvider.getInstance().getSubscriberDAO();

	
	/**
	 * Provides subscriber retrieved by it's ID.
	 * 
	 * @param id - subscriber's ID 
	 * @return subscriber as an Optional value
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting subscriber from the data storage
	 */
	@Override
	public Optional<Subscriber> findSubscriberById(long id) throws ServiceException {
		Optional<Subscriber> subscriber;
		try {
			subscriber = subscriberDao.findSubscriberById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} 
		return subscriber;
	}

	/**
	 * Provides subscriber retrieved by it's phone number.
	 * 
	 * @param phone - subscriber's phone number 
	 * @return subscriber as an Optional value
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting subscriber from the data storage
	 */
	@Override
	public Optional<Subscriber> findSubscriberByPhone(String phone) throws ServiceException {
		Optional<Subscriber> subscriber = Optional.empty();
			
		if(InputDataValidator.isPhone(phone)) {
		try {
				subscriber = subscriberDao.findSubscriberByPhone(phone);
			} catch (DaoException e) {
				throw new ServiceException(e);
			} 
		}
		return subscriber;
	}

	/**
	 * Provides the list of subscriber's (phone numbers) related to the same user.
	 * 
	 * @param userID - user's ID
	 * @return list of subscribers
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting subscriber list from the data storage
	 */
	@Override
	public List<Subscriber> findSubscriberListByUserId(long userID) throws ServiceException {
		List<Subscriber> subscriberList;
		try {
			subscriberList = subscriberDao.findSubscriberListByUserId(userID);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} 
		return subscriberList;
	}
	
	
	/**
	 * Provides the list of subscriber's related to the same user by passport number.
	 * 
	 * @param passport - passport number
	 * @return list of subscribers
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting subscriber list from the data storage
	 */
	@Override
	public List<Subscriber> findSubscriberListByPassport(String passport) throws ServiceException {
		List<Subscriber> subscriberList = new ArrayList<>();
		if (InputDataValidator.isPassport(passport)) {
			try {
				subscriberList = subscriberDao.findSubscriberListByPassport(passport);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return subscriberList;
	}

	/**
	 * Provides the list of subscriber's related to the same user by full name.
	 * 
	 * @param firstName - subscriber's first name
	 * @param middleName - subscriber's middle name
	 * @param lastName - subscriber's last name
	 * @return list of subscribers
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting subscriber list from the data storage
	 */
	@Override
	public List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName) throws ServiceException {
		List<Subscriber> subscriberList = new ArrayList<>();
		
		if (InputDataValidator.isName(lastName) && InputDataValidator.isName(lastName)
				&& (InputDataValidator.isName(middleName) || middleName.isBlank())) {
			try {
				subscriberList = subscriberDao.findSubscriberListByFullName(firstName, middleName, lastName);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return subscriberList;
	}

	/**
	 * Checks the data storage to see if the phone number is there. 
	 * 
	 * @param phone - phone number
	 * @return {@code true} if there is no this {@code phone number} in the data storage
	 * @throws ServiceException in the case when DaoException occurs while getting a subscriber 
	 * from the data storage
	 */
	@Override
	public boolean isPhoneAvailable(String phone) throws ServiceException {
		boolean result = false;

		Optional<Subscriber> subscriber = Optional.empty();
		if (InputDataValidator.isPhone(phone)) {
			try {
				subscriber = subscriberDao.findSubscriberByPhone(phone);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}

		if (subscriber.isEmpty()) {
			result = true;
		}

		return result;
	}
	
	/**
	 * Checks to see if the user with provided passport number is already presented in the data storage. 
	 * 
	 * @param passport - passport number
	 * @return {@code true} if there is a user with {@code passport number } in the data storage
	 * @throws ServiceException in the case when DaoException occurs while getting a subscriber list
	 * from the data storage
	 */
	@Override
	public boolean isNewSubscriberUser(String passport) throws ServiceException {
		List<Subscriber> subscribers = new ArrayList<>();
		if (InputDataValidator.isPassport(passport)) {
			subscribers = findSubscriberListByPassport(passport);
		}
		return subscribers.isEmpty();
	}
	
	
	/**
	 * Checks, if the subscriber has debts - negative value on his account
	 * 
	 * @param passport - passport number
	 * @return {@code true} if account value is less than zero
	 * @throws ServiceException in the case when DaoException occurs while getting a subscriber list
	 * from the data storage
	 * */
	@Override
	public boolean isDebtor(String passport) throws ServiceException {
		List<Subscriber> subscribersWithDebts = new ArrayList<>();
		
		if (InputDataValidator.isPassport(passport)){
		subscribersWithDebts = findSubscribersDebtors(passport);
		}
		return subscribersWithDebts.size() > 0;
	}

	
	/**
	 * Retrieves all subscribers with accounts with negative values related to the same user.
	 *  
	 * @param passport - user's passport number
	 * @return - list of subscribers with accounts with negative values related to the same user
	 * @throws ServiceException in the case when DaoException occurs while getting a subscriber list
	 * from the data storage
	 */
	@Override
	public List<Subscriber> findSubscribersDebtors(String passport) throws ServiceException {
		List<Subscriber> subscribersWithDebts = new ArrayList<>();
		
		if (InputDataValidator.isPassport(passport)){
		List<Subscriber> subscribers = findSubscriberListByPassport(passport);
		for(Subscriber subscriber : subscribers) {
			if (subscriber.getAccount() < 0) {
				subscribersWithDebts.add(subscriber);
			}
		}
		}
		return subscribersWithDebts;
	}


	/**
	 * Adds to data storage one more subscriber to existing user.
	 * 
	 * @param subscriber - new subscriber
	 * @return new subscriber's ID
	 * @throws ServiceException in the case when DaoException occurs while adding a subscriber 
	 * to the data storage
	 */
	@Override
	public long addNewSubscriberToExistingUser(Subscriber subscriber) throws ServiceException {
		long subscriberID = ERROR_ID;
		
		if (InputDataValidator.isSubscriberValid(subscriber)) {

			try {
				subscriberID = subscriberDao.addNewSubscriberToExistingUser(subscriber);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		
		return subscriberID;

	}

	/**
	 * Updates subscriber's data.
	 * 
	 * @param subscriber - subscriber
	 * @throws ServiceException in the case when DaoException occurs while updating a subscriber 
	 */
	@Override
	public void updateSubscriber(Subscriber subscriber) throws ServiceException {
		if (InputDataValidator.isSubscriberValid(subscriber)) {
			try {
				subscriberDao.updateSubscriber(subscriber);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
	}

}
