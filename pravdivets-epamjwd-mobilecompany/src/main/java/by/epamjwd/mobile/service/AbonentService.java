package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface AbonentService {

	Abonent findAbonentByPhoneNumber(String phoneNumber) throws ServiceException;
	Abonent findAbonentById(String id) throws ServiceException;
	List<Abonent> findAbonentListByEmail(String email) throws ServiceException;
	List<Abonent> findAbonentListByUserId(String id) throws ServiceException;
	List<Abonent> findAbonentListByFullName(String firstName, String middleName, String lastName)
			throws ServiceException;

}
