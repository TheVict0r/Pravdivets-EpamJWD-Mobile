package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface SubscriberService {

	Optional<Subscriber> findSubscriberById(long id) throws ServiceException;
	Optional<Subscriber> findSubscriberByPhone(String phone) throws ServiceException;
	List<Subscriber> findSubscriberListByUserId(long id) throws ServiceException;
	List<Subscriber> findSubscriberListByPassport(String passport) throws ServiceException;
	List<Subscriber> findSubscribersDebtors(String passport) throws ServiceException;
	List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName)
			throws ServiceException;
	
	boolean isDebtor(String passport) throws ServiceException;
	boolean isPhoneAvailable(String phone) throws ServiceException;
	boolean isNewSubscriberUser(String passport) throws ServiceException;
	long addNewSubscriberToExistingUser(Subscriber subscriber) throws ServiceException;
	void updateSubscriber(Subscriber subscriber) throws ServiceException;
	
	
}
