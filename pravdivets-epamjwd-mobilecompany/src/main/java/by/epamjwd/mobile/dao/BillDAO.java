package by.epamjwd.mobile.dao;

import java.util.Optional;

import by.epamjwd.mobile.bean.Bill;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface BillDAO {
	
	public Optional<Bill> getServiceBySubscriberID(int subscriberID) throws DaoException;

	
}
