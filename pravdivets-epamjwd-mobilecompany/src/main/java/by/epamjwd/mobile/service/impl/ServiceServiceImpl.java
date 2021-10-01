package by.epamjwd.mobile.service.impl;

import java.util.List;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.ServiceDAO;
import by.epamjwd.mobile.service.ServiceService;

public class ServiceServiceImpl implements ServiceService{

	DAOProvider provider = DAOProvider.getInstance();
	ServiceDAO serviceDao = provider.getServiceDAO();
	
	
	@Override
	public List<Service> getAllServices() {
		return serviceDao.getAllServices();
	}

	@Override
	public Service getServiceByID(int id) {
		return serviceDao.getServiceByID(id);
	}

}
