package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface AbonentService {

	Abonent findAbonentByLogin(String login) throws ServiceException;
	Abonent findAbonentByPhoneNumber(int phoneNumber) throws ServiceException;
	Abonent findAbonentById(String id) throws ServiceException;
	List<Abonent> findAbonentListByEmail(String email) throws ServiceException;
	List<Abonent> findAbonentListByUserId(String id) throws ServiceException;

}
