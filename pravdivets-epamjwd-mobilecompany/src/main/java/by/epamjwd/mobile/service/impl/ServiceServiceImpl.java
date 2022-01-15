package by.epamjwd.mobile.service.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.controller.repository.IndexRepository;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.ServiceDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.ServiceService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class ServiceServiceImpl implements ServiceService {
	ServiceDAO serviceDao = DAOProvider.getInstance().getServiceDAO();

	/**
	 * Provides all actual Services currently exists.
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
	 * Provides Service retrieved by it's ID.
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
	 * Checks the existence of Service by it's {@code name}.
	 * 
	 * @param name  the name of Service
	 * @throws ServiceException in the case when DaoException occurs while 
	 * getting Service from the data storage
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
	 * Builds new Service with empty ID.
	 * 
	 * @param name - the name of Service
	 * @param tariff - the price of Service
	 * @param description - a short text description of Service
	 * @return new Service
	 */
	@Override
	public Service buildService(String name, int tariff, String description) {
		return new Service(IndexRepository.EMPTY_ID, name, tariff, description);
	}

	/**
	 * Adds Service to data storage.
	 * 
	 * @param plan - Service to add
	 * @return the ID of Service in data storage
	 * @throws ServiceException in the case when DaoException occurs while saving Service 
	 * to the data storage
	 */
	@Override
	public long addService(Service service) throws ServiceException {
		long serviceId = IndexRepository.ERROR_ID;
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