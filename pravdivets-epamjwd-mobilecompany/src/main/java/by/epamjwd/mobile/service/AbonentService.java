package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface AbonentService {

	public List<Abonent> getAbonentsByEmail(String email) throws ServiceException;
	public Optional<Abonent> getAbonentByPhoneNumber(int phoneNumber) throws ServiceException;
	
}
