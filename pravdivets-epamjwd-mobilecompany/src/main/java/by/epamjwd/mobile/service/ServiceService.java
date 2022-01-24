package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface ServiceService {
	
	List<Service> findAllServices() throws ServiceException;
	Optional<Service> findServiceByID(long id) throws ServiceException;
	boolean isServiceExists(String name) throws ServiceException;
	Service buildService(String name, int tariff, String description);
	long addService(Service service) throws ServiceException;
	
}
