package by.epamjwd.mobile.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.SubscriberStatus;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.repository.IDRepository;
import by.epamjwd.mobile.service.ServiceProvider;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class SubscriberServiceImpl implements SubscriberService {

	
	/**
	 * Provides Subscriber retrieved by it's ID.
	 * 
	 * @param id - subscriber's ID
	 * 
	 * @return Subscriber as an Optional value
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting a
	 *                          Subscriber from the data storage
	 */
	@Override
	public Optional<Subscriber> findSubscriberById(long id) throws ServiceException {
		try {
			return DAOProvider.getInstance().getSubscriberDAO().findSubscriberById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	
	/**
	 * Provides Subscriber retrieved by it's phone number.
	 * 
	 * @param phone - subscriber's phone number
	 * 
	 * @return Subscriber as an Optional value
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting a
	 *                          Subscriber from the data storage
	 */
	@Override
	public Optional<Subscriber> findSubscriberByPhone(String phone) throws ServiceException {
		Optional<Subscriber> subscriber = Optional.empty();

		if (InputDataValidator.isPhone(phone)) {
			try {
				subscriber = DAOProvider.getInstance().getSubscriberDAO().
						findSubscriberByPhone(phone);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return subscriber;
	}

	
	/**
	 * Provides the list of subscriber's (phone numbers) related to the same User.
	 * 
	 * <p>
	 * One User could have multiple phone numbers, so each phone number means a
	 * separate subscriber
	 * 
	 * @param userID - user's ID
	 * 
	 * @return list of Subscribers
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting a
	 *                          Subscriber list from the data storage
	 */
	@Override
	public List<Subscriber> findSubscriberListByUserId(long userID) throws ServiceException {
		try {
			return DAOProvider.getInstance().getSubscriberDAO().findSubscriberListByUserId(userID);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	
	/**
	 * Provides the list of Subscriber's related to the same user by passport
	 * number.
	 * 
	 * @param passport - passport number
	 * 
	 * @return list of Subscribers
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting a
	 *                          Subscriber list from the data storage
	 */
	@Override
	public List<Subscriber> findSubscriberListByPassport(String passport) throws ServiceException {
		List<Subscriber> subscriberList = new ArrayList<>();
		if (InputDataValidator.isPassport(passport)) {
			try {
				subscriberList = DAOProvider.getInstance().getSubscriberDAO().
						findSubscriberListByPassport(passport);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return subscriberList;
	}

	
	/**
	 * Provides the list of Subscriber's related to the same user by full name.
	 * 
	 * @param firstName  - subscriber's first name
	 * 
	 * @param middleName - subscriber's middle name
	 * 
	 * @param lastName   - subscriber's last name
	 * 
	 * @return list of Subscribers
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting a
	 *                          Subscriber list from the data storage
	 */
	@Override
	public List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName)
			throws ServiceException {
		List<Subscriber> subscriberList = new ArrayList<>();

		if (InputDataValidator.isName(firstName) && InputDataValidator.isName(lastName)
				&& (InputDataValidator.isName(middleName) || middleName.isBlank())) {
			try {
				subscriberList = DAOProvider.getInstance().getSubscriberDAO().
						findSubscriberListByFullName(firstName, middleName, lastName);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		
		return subscriberList;
	}

	
	/**
	 * Retrieves all Subscribers with accounts with negative values related to the
	 * same User.
	 * 
	 * @param passport - user's passport number
	 * 
	 * @return - list of Subscribers with accounts with negative values related to
	 *         the same User
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting a
	 *                          Subscriber list from the data storage
	 */
	@Override
	public List<Subscriber> findSubscribersDebtors(String passport) throws ServiceException {
		List<Subscriber> subscribersWithDebts = new ArrayList<>();
		List<Subscriber> subscribers = new ArrayList<>();

		if (InputDataValidator.isPassport(passport)) {
			subscribers = findSubscriberListByPassport(passport);
			for (Subscriber subscriber : subscribers) {
				if (subscriber.getAccount() < 0) {
					subscribersWithDebts.add(subscriber);
				}
			}
		}
		return subscribersWithDebts;
	}

	
	/**
	 * Adds to the data storage one more Subscriber to existing User.
	 * 
	 * @param subscriber - new Subscriber
	 * 
	 * @return new subscriber's ID
	 * 
	 * @throws ServiceException in the case when DaoException occurs while adding a
	 *                          Subscriber to the data storage
	 */
	@Override
	public long addNewSubscriberToExistingUser(Subscriber subscriber) throws ServiceException {
		long subscriberID = IDRepository.ERROR_ID;

		if (InputDataValidator.isSubscriberValid(subscriber)) {
			try {
				subscriberID = DAOProvider.getInstance().getSubscriberDAO().
						addNewSubscriberToExistingUser(subscriber);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}

		return subscriberID;

	}

	
	/**
	 * Updates Subscriber's data.
	 * 
	 * @param subscriber - Subscriber
	 * 
	 * @throws ServiceException in the case when DaoException occurs while updating
	 *                          the Subscriber
	 */
	@Override
	public void updateSubscriber(Subscriber subscriber) throws ServiceException {

		if (InputDataValidator.isSubscriberValid(subscriber)) {
			try {
				DAOProvider.getInstance().getSubscriberDAO().updateSubscriber(subscriber);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
	}

	
	/**
	 * Builds new Subscriber with empty ID.
	 * 
	 * @param phone  - phone number
	 * 
	 * @param planID - tariff plan ID
	 * 
	 * @param userID - user's ID
	 * 
	 * @return new Subscriber
	 * 
	 * @throws ServiceException in the case when DaoException occurs while searching
	 *                          tariff plan by ID
	 */
	@Override
	public Subscriber buildSubscriber(String phone, long planID, long userID) throws ServiceException {
		Subscriber subscriber = new Subscriber();
		Optional<Plan> planOptional = ServiceProvider.getInstance().getPlanService().findPlanByID(planID);
		if (planOptional.isPresent()) {
			Plan plan = planOptional.get();
			int account = plan.getUpfrontPayment();
			subscriber = new Subscriber(IDRepository.EMPTY_ID, new Date(), account, phone, new Date(),
					SubscriberStatus.ACTIVE, planID, userID);
		}
		return subscriber;
	}

	
	/**
	 * Checks for the presence of phone number in the data storage.
	 * 
	 * @param phone - phone number
	 * 
	 * @return {@code true} if the {@code phone number} is already presented in the
	 *         data storage
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting a
	 *                          Subscriber from the data storage
	 */
	@Override
	public boolean isPhoneExist(String phone) throws ServiceException {
		boolean result = false;
		if (InputDataValidator.isPhone(phone)) {
			result = findSubscriberByPhone(phone).isPresent();
		}
		return result;
	}

	
	/**
	 * Checks to see if the User with provided passport number is already presented
	 * in the data storage as a Subscriber.
	 * 
	 * @param passport - passport number
	 * 
	 * @return {@code true} if there is a User with {@code passport number } in the
	 *         data storage
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting a
	 *                          Subscriber list from the data storage
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
	 * Checks, if a Subscriber has debts - negative values on his all accounts
	 * 
	 * @param passport - passport number
	 * 
	 * @return {@code true} if account value is less than zero
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting a
	 *                          Subscriber list from the data storage
	 */
	@Override
	public boolean isDebtor(String passport) throws ServiceException {
		List<Subscriber> subscribersWithDebts = new ArrayList<>();

		if (InputDataValidator.isPassport(passport)) {
			subscribersWithDebts = findSubscribersDebtors(passport);
		}
		return subscribersWithDebts.size() > 0;
	}

	
	/**
	 * Checks if Subscriber need to be signed-up. (Have no password).
	 * 
	 * @param phone - phone number
	 * 
	 * @return {@code true} if the Subscriber is already presented in the data
	 *         storage and have no {@code password}
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting a
	 *                          User from the data storage
	 */
	@Override
	public boolean isSignupRequired(String phone) throws ServiceException {
		boolean result = false;

		if (InputDataValidator.isPhone(phone)) {
			Optional<User> userOptional = ServiceProvider.getInstance().getUserService().
					findUserByPhone(phone);
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				result = user.getPassword() == null;
			}
		}
		return result;
	}

}
