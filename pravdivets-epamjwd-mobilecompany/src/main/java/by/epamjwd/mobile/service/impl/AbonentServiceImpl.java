package by.epamjwd.mobile.service.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.dao.AbonentDAO;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.AbonentService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class AbonentServiceImpl implements AbonentService {

	DAOProvider provider = DAOProvider.getInstance();
	AbonentDAO abonentDao = provider.getAbonentDAO();

	
	@Override
	public List<Abonent> findAbonentsByEmail(String email) throws ServiceException {
		List<Abonent> result;
		try {
			result = abonentDao.findAbonentListByEmail(email);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public Optional<Abonent> findAbonentById(String id) throws ServiceException {
		Optional<Abonent> result;
		try {
			result = abonentDao.findAbonentById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;
	}
	
	@Override
	public List<Abonent> findAbonentListByUserId(String id) throws ServiceException {
		List<Abonent> result;
		try {
			result = abonentDao.findAbonentListByUserId(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;
	}
	
	@Override
	public Optional<Abonent> findAbonentByPhoneNumber(int phoneNumber) throws ServiceException {
		Optional<Abonent> result;
		try {
			result = abonentDao.findAbonentByPhoneNumber(phoneNumber);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;
	}


}
