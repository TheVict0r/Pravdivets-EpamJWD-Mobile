package by.epamjwd.mobile.service;

import java.util.Optional;

import by.epamjwd.mobile.bean.Customer;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface CustomerService {

	Optional<Customer> findCustomerByPassport(String passport) throws ServiceException;

	
}
