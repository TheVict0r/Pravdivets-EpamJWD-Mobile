package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface SubscriberService {

	Subscriber findSubscriberByPhoneNumber(String phoneNumber) throws ServiceException;
	Subscriber findSubscriberById(String id) throws ServiceException;
	List<Subscriber> findSubscriberListByEmail(String email) throws ServiceException;
	List<Subscriber> findSubscriberListByUserId(String id) throws ServiceException;
	List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName)
			throws ServiceException;

}
