package by.epamjwd.mobile.service.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.ServiceDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.ServiceService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class ServiceServiceImpl implements ServiceService {

	DAOProvider provider = DAOProvider.getInstance();
	ServiceDAO serviceDao = provider.getServiceDAO();

	@Override
	public List<Service> getAllServices() throws ServiceException {
		try {
			return serviceDao.getAllServices();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<Service> getServiceByID(int id) throws ServiceException {
		try {
			return serviceDao.getServiceByID(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
