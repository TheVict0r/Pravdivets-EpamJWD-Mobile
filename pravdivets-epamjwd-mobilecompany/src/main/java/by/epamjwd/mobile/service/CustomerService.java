package by.epamjwd.mobile.service;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface CustomerService {

	long addNewCustomer(User user, Subscriber subscriber) throws ServiceException;

	
}
