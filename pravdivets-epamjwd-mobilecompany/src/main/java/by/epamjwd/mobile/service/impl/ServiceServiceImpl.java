package by.epamjwd.mobile.service.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.ServiceDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.ServiceService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class ServiceServiceImpl implements ServiceService {
	public final static long EMPTY_ID = 0L;
	public final static long ERROR_ID = -1L;

	ServiceDAO serviceDao = DAOProvider.getInstance().getServiceDAO();

	/**
	 * Provides all actual services currently exists.
	 * 
	 * @return Array List containing all current services
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting all services from the data storage
	 */
	@Override
	public List<Service> findAllServices() throws ServiceException {
		try {
			return serviceDao.getAllServices();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	
	/**
	 * Provides service retrieved by it's ID.
	 * 
	 * @param id - ID of service
	 * @return service as an Optional value
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting service from the data storage
	 */
	@Override
	public Optional<Service> findServiceByID(long id) throws ServiceException {
		try {
			return serviceDao.getServiceByID(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Checks the existence of service by it's {@code name}.
	 * 
	 * @param name  the name of service
	 * @throws ServiceException in the case when DaoException occurs while 
	 * getting service from the data storage
	 */
	@Override
	public boolean doesServiceExists(String name) throws ServiceException {
		Optional<Service> serviceOptional = Optional.empty();
		try {
			serviceOptional = serviceDao.getServiceByName(name);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return serviceOptional.isPresent();
	}

	/**
	 * Builds new service with empty ID.
	 * 
	 * @param name - the name of service
	 * @param tariff - the price of service
	 * @param description - a short text description of service
	 * @return new service
	 */
	@Override
	public Service buildService(String name, int tariff, String description) {
		return new Service(EMPTY_ID, name, tariff, description);
	}

	/**
	 * Adds service to data storage.
	 * 
	 * @param plan - service to add
	 * @return the ID of service in data storage
	 * @throws ServiceException in the case when DaoException occurs while saving service 
	 * to the data storage
	 */
	@Override
	public long addService(Service service) throws ServiceException {
		long serviceId = ERROR_ID;
		if (InputDataValidator.isServiceValid(service)) {
			try {
				serviceId = serviceDao.addService(service);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return serviceId;
	}	
	
}