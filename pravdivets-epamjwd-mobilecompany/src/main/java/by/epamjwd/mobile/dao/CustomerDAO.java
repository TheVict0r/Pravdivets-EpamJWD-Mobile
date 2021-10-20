package by.epamjwd.mobile.dao;

import java.util.Optional;

import by.epamjwd.mobile.bean.Customer;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface CustomerDAO {

	Optional<Customer> findCustomerByPassport(String passport)throws DaoException;
	
}
