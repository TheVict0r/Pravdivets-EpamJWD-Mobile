package by.epamjwd.mobile.service;

import java.util.List;


import by.epamjwd.mobile.bean.Service;

public interface ServiceService {

	public List<Service> getAllServices();
	
	public Service getServiceByID(int id);

	
}
