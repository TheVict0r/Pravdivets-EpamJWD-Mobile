package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface SubscriberService {

	Optional<Subscriber> findSubscriberByPhoneNumber(String phoneNumber) throws ServiceException;
	Optional<Subscriber> findSubscriberById(String id) throws ServiceException;
	List<Subscriber> findSubscriberListByEmail(String email) throws ServiceException;
	List<Subscriber> findSubscriberListByUserId(String id) throws ServiceException;
	List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName)
			throws ServiceException;
	List<Subscriber> findSubscriberListByPassport(String passport) throws ServiceException;
	boolean isNewCustomer(String passport) throws ServiceException;
	boolean isDebtor(String passport) throws ServiceException;
	List<Subscriber> findSubscribersListWithDebts(String passport) throws ServiceException;
	boolean isPhoneNumberAvailable(int phoneNumber) throws ServiceException;
	void addNewSubscriber(String passport, int phoneNumber, long plan_id, String firstName, String middleName,
			String lastName, String homeAddress, String email) throws ServiceException;
	void addNewSubscriberToExistingCustomer(String passport, int phoneNumber, long plan_id)throws ServiceException;
	
	
}
