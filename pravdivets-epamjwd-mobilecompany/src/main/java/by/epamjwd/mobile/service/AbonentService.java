package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface AbonentService {

	List<Abonent> findAbonentsByEmail(String email) throws ServiceException;
	Abonent findAbonentByPhoneNumber(int phoneNumber) throws ServiceException;
	List<Abonent> findAbonentListByUserId(String id) throws ServiceException;
	Abonent findAbonentById(String id) throws ServiceException;
	boolean isPhoneNumber(String anyString);

}
