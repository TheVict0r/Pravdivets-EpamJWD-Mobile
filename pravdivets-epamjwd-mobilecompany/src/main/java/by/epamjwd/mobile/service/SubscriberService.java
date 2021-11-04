package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface SubscriberService {

	Optional<Subscriber> findSubscriberById(long id) throws ServiceException;
	Optional<Subscriber> findSubscriberByPhone(int phone) throws ServiceException;
	Optional<Subscriber> findSubscriberByPhoneString(String phoneString) throws ServiceException;
	List<Subscriber> findSubscriberListByUserId(long id) throws ServiceException;
	List<Subscriber> findSubscriberListByPassport(String passport) throws ServiceException;
	List<Subscriber> findSubscriberListWithDebts(String passport) throws ServiceException;
	List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName)
			throws ServiceException;
	
	
//	List<Subscriber> findSubscriberListByEmail(String email) throws ServiceException;
	
	boolean isDebtor(String passport) throws ServiceException;
	boolean isPhoneAvailable(int phone) throws ServiceException;
	boolean isNewSubscriberUser(String passport) throws ServiceException;
	void addNewSubscriber(String firstName, String middleName, String lastName, String passport, String email,
			int phone, long plan_id)throws ServiceException;
	void addNewSubscriberToExistingUser(int phone, long planId, long userId) throws ServiceException;
	
	
}
