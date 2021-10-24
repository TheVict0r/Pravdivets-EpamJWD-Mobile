package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface SubscriberDAO {

	Optional<Subscriber> findSubscriberByPhoneNumber(int phone) throws DaoException;
	Optional<Subscriber> findSubscriberById(long id) throws DaoException;
	List<Subscriber> findSubscriberListByUserId(long id) throws DaoException;
	List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName) throws DaoException;
	List<Subscriber> findSubscriberListByPassport(String passport)throws DaoException;
	
}
