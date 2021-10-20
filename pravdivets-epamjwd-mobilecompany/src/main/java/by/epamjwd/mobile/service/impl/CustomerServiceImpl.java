package by.epamjwd.mobile.service.impl;

import java.util.Optional;

import by.epamjwd.mobile.bean.Customer;
import by.epamjwd.mobile.dao.CustomerDAO;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.CustomerService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class CustomerServiceImpl implements CustomerService {
	DAOProvider provider = DAOProvider.getInstance();
	CustomerDAO customerDao = provider.getCustomerDAO();

	@Override
	public Optional<Customer> findCustomerByPassport(String passport) throws ServiceException {
		Optional<Customer> customer = Optional.empty();

		try {
			customer = customerDao.findCustomerByPassport(passport);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

		return customer;

	}

}
