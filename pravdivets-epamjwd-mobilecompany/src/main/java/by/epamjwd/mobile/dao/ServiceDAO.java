package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface ServiceDAO {
	public List<Service> getAllServices() throws DaoException;
	
	public Optional<Service> getServiceByID(int id) throws DaoException;

}
