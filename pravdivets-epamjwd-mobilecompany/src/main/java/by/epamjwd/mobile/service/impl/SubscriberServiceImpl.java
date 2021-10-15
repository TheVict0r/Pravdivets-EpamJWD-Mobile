package by.epamjwd.mobile.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.dao.SubscriberDAO;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.SubscriberService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.InputValueChecker;

public class SubscriberServiceImpl implements SubscriberService {
	DAOProvider provider = DAOProvider.getInstance();
	SubscriberDAO subscriberDao = provider.getSubscriberDAO();

	@Override
	public Subscriber findSubscriberByPhoneNumber(String phoneString) throws ServiceException {
		Subscriber subscriber = null;
		if (InputValueChecker.isPhoneNumber(phoneString)) {
			int phoneNumber = Integer.parseInt(phoneString);
			try {
				subscriber = subscriberDao.findSubscriberByPhoneNumber(phoneNumber).get();
			} catch (DaoException e) {
				throw new ServiceException(e);
			} catch (NoSuchElementException e) {
				throw new ServiceException("The Optional<Subscriber> contains null for phone number - " + phoneNumber, e);
			}
		}
		return subscriber;
	}
	
	@Override
	public Subscriber findSubscriberById(String id) throws ServiceException {
		Subscriber subscriber;
		try {
			subscriber = subscriberDao.findSubscriberById(id).get();
		} catch (DaoException e) {
			throw new ServiceException(e);
		} catch (NoSuchElementException e) {
			throw new ServiceException("The Optional<Subscriber> contains null for ID - " + id, e);
		}
		return subscriber;
	}
	
	@Override
	public List<Subscriber> findSubscriberListByEmail(String email) throws ServiceException {
		List<Subscriber> subscriberList;
		try {
			subscriberList = subscriberDao.findSubscriberListByEmail(email);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} if (subscriberList.isEmpty()) {
			throw new ServiceException("Empty subscriber list for e-mail " + email);
		}
		return subscriberList;
	}

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
	
	@Override
	public List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName) throws ServiceException {
		List<Subscriber> subscriberList;
		try {
			subscriberList = subscriberDao.findSubscriberListByFullName(firstName, middleName, lastName);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} if (subscriberList.isEmpty()) {
			throw new ServiceException("Empty subscriber list for " + firstName + " " + middleName + " " + lastName);
		}

		return subscriberList;
	}

	@Override
	public List<Subscriber> findSubscriberListByUserId(String id) throws ServiceException {
		List<Subscriber> subscriberList;
		try {
			subscriberList = subscriberDao.findSubscriberListByUserId(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		} if (subscriberList.isEmpty()) {
			throw new ServiceException("Empty subscriber list for ID " + id);
		}
		return subscriberList;
	}

	@Override
	public boolean isNewSubscriber(String passport) throws ServiceException {
		List<Subscriber> result = findSubscriberListByPassport(passport);
		return result.isEmpty();
	}

	@Override
	public boolean isNoDebt(String passport) throws ServiceException {
		List<Subscriber> subscribersWithDebts = findSubscribersListWithDebts(passport);
		return subscribersWithDebts.isEmpty();
	}

	@Override
	public List<Subscriber> findSubscribersListWithDebts(String passport) throws ServiceException {
		List<Subscriber> subscribersList = findSubscriberListByPassport(passport);
		List<Subscriber> subscribersWithDebts = new ArrayList<>();
		for(Subscriber subscriber : subscribersList) {
			if (subscriber.getCheckingAccountAmount() < 0) {
				subscribersWithDebts.add(subscriber);
			}
		}
		return subscribersWithDebts;
	}
	
}
