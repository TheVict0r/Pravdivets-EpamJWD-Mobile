package by.epamjwd.mobile.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.dao.SubscriberDAO;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class SubscriberServiceImpl implements SubscriberService {
	private static final long ERROR_ID = -1;
	
	SubscriberDAO subscriberDao = DAOProvider.getInstance().getSubscriberDAO();
	
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

	
	@Override
	public List<Subscriber> findSubscriberListByUserId(long id) throws ServiceException {
		List<Subscriber> subscriberList;
		try {
			subscriberList = subscriberDao.findSubscriberListByUserId(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} 
		return subscriberList;
	}
	
	
	@Override
	public boolean isPhoneAvailable(String phone) throws ServiceException {
		boolean result = false;
		
		Optional<Subscriber> subscriber;
		try {
			subscriber = subscriberDao.findSubscriberByPhone(phone);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

		if (subscriber.isEmpty()) {
			result = true;
		}

		return result;
	}
	
	
	
//	@Override
//	public List<Subscriber> findSubscriberListByEmail(String email) throws ServiceException {
//		List<Subscriber> subscriberList;
//		try {
//			subscriberList = subscriberDao.findSubscriberListByEmail(email);
//		} catch (DaoException e) {
//			throw new ServiceException(e);
//		} if (subscriberList.isEmpty()) {
//			throw new ServiceException("Empty subscriber list for e-mail " + email);
//		}
//		return subscriberList;
//	}
//
//	
//	
	@Override
	public List<Subscriber> findSubscriberListByPassport(String passport) throws ServiceException {
		List<Subscriber> subscriberList;
		try {
			subscriberList = subscriberDao.findSubscriberListByPassport(passport);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return subscriberList;
	}
//	
	@Override
	public List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName) throws ServiceException {
		List<Subscriber> subscriberList;
		try {
			subscriberList = subscriberDao.findSubscriberListByFullName(firstName, middleName, lastName);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return subscriberList;
	}


	@Override
	public boolean isNewSubscriberUser(String passport) throws ServiceException {
		List<Subscriber> subscribers = findSubscriberListByPassport(passport);
		return subscribers.isEmpty();
	}

	@Override
	public boolean isDebtor(String passport) throws ServiceException {
		List<Subscriber> subscribersWithDebts = findSubscribersDebtors(passport);
		return subscribersWithDebts.size() > 0;
	}

	@Override
	public List<Subscriber> findSubscribersDebtors(String passport) throws ServiceException {
		List<Subscriber> subscribers = findSubscriberListByPassport(passport);
		List<Subscriber> subscribersWithDebts = new ArrayList<>();
		for(Subscriber subscriber : subscribers) {
			if (subscriber.getAccount() < 0) {
				subscribersWithDebts.add(subscriber);
			}
		}
		return subscribersWithDebts;
	}


	@Override
	public long addNewSubscriberToExistingUser(Subscriber subscriber) throws ServiceException {

		long resultId = ERROR_ID;
		
		if (InputDataValidator.isSubscriberValid(subscriber)) {

			try {
				resultId = subscriberDao.addNewSubscriberToExistingUser(subscriber);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		
		return resultId;

	}

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
