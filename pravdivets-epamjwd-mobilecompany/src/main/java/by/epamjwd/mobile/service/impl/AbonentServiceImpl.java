package by.epamjwd.mobile.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.dao.AbonentDAO;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.AbonentService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.util.LoginChecker;

public class AbonentServiceImpl implements AbonentService {
	DAOProvider provider = DAOProvider.getInstance();
	AbonentDAO abonentDao = provider.getAbonentDAO();

	@Override
	public Abonent findAbonentByLogin(String login) throws ServiceException {
		Abonent abonent = null;
		if(LoginChecker.isPhoneNumber(login)) {
			int phoneNumber = Integer.parseInt(login);
			abonent = findAbonentByPhoneNumber(phoneNumber);
		}
		return abonent;
	}

	@Override
	public Abonent findAbonentByPhoneNumber(int phoneNumber) throws ServiceException {
		Abonent abonent;
		try {
			abonent = abonentDao.findAbonentByPhoneNumber(phoneNumber).get();
		} catch (DaoException e) {
			throw new ServiceException(e);
		} catch (NoSuchElementException e) {
			throw new ServiceException("The Optional<Abonent> contains null for phone number - " + phoneNumber, e);
		}
		return abonent;
	}
	
	@Override
	public Abonent findAbonentById(String id) throws ServiceException {
		Abonent abonent;
		try {
			abonent = abonentDao.findAbonentById(id).get();
		} catch (DaoException e) {
			throw new ServiceException(e);
		} catch (NoSuchElementException e) {
			throw new ServiceException("The Optional<Abonent> contains null for ID - " + id, e);
		}
		return abonent;
	}
	
	@Override
	public List<Abonent> findAbonentListByEmail(String email) throws ServiceException {
		List<Abonent> abonentList;
		try {
			abonentList = abonentDao.findAbonentListByEmail(email);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return abonentList;
	}

	@Override
	public List<Abonent> findAbonentListByUserId(String id) throws ServiceException {
		List<Abonent> abonentList;
		try {
			abonentList = abonentDao.findAbonentListByUserId(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return abonentList;
	}
	
}
