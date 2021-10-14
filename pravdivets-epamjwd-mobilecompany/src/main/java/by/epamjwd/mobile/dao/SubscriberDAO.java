package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface SubscriberDAO {

	public Optional<Subscriber> findSubscriberByPhoneNumber(int phoneNumber) throws DaoException;
	public List<Subscriber> findSubscriberListByEmail(String email) throws DaoException;
	public Optional<Subscriber> findSubscriberById(String id) throws DaoException;
	List<Subscriber> findSubscriberListByUserId(String id) throws DaoException;
	public List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName) throws DaoException;

	
}
