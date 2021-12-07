package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface ServiceDAO {
	List<Service> getAllServices() throws DaoException;
	Optional<Service> getServiceByID(long id) throws DaoException;

}
