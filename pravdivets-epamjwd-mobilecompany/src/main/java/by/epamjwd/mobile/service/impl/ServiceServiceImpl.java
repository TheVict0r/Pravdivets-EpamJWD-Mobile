package by.epamjwd.mobile.service.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Plan;
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

	@Override
	public List<Service> findAllServices() throws ServiceException {
		try {
			return serviceDao.getAllServices();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<Service> findServiceByID(long id) throws ServiceException {
		try {
			return serviceDao.getServiceByID(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean isServiceExist(String name) throws ServiceException {
		Optional<Service> serviceOptional = Optional.empty();
		try {
			serviceOptional = serviceDao.getServiceByName(name);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return serviceOptional.isPresent();
	}

	@Override
	public Service buildService(String name, int tariff, String description) {
		return new Service(EMPTY_ID, name, tariff, description);
	}

	@Override
	public long addNewService(Service service) throws ServiceException {
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