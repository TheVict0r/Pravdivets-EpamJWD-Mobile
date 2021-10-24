package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface SubscriberService {

	Optional<Subscriber> findSubscriberById(String id) throws ServiceException;
	Optional<Subscriber> findSubscriberByPhone(String phone) throws ServiceException;
	List<Subscriber> findSubscriberListByUserId(String id) throws ServiceException;
	List<Subscriber> findSubscriberListByPassport(String passport) throws ServiceException;
	List<Subscriber> findSubscriberListWithDebts(String passport) throws ServiceException;
	List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName)
			throws ServiceException;
	
	
//	List<Subscriber> findSubscriberListByEmail(String email) throws ServiceException;
	
	boolean isDebtor(String passport) throws ServiceException;
	boolean isPhoneAvailable(int phoneNumber) throws ServiceException;
	boolean isNewUserSubscriber(String passport) throws ServiceException;
	void addNewSubscriber(String passport, int phoneNumber, long plan_id, String firstName, String middleName,
			String lastName, String homeAddress, String email) throws ServiceException;
	void addOneMorePhoneToCurrentSubscriber(String passport, int phoneNumber, long plan_id)throws ServiceException;
	
	
}
