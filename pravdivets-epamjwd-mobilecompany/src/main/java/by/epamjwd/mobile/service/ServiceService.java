package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface ServiceService {

	public List<Service> getAllServices() throws ServiceException;
	
	public Optional<Service> getServiceByID(int id) throws ServiceException;

	
}
