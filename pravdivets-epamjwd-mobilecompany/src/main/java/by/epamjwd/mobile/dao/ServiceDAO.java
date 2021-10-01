package by.epamjwd.mobile.dao;

import java.util.List;

import by.epamjwd.mobile.bean.Service;

public interface ServiceDAO {
	public List<Service> getAllServices();
	
	public Service getServiceByID(int id);

}
