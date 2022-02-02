package by.epamjwd.mobile.dao;

import java.util.List;

import by.epamjwd.mobile.bean.Bill;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface BillDAO {
	
	List<Bill> getBillListBySubscriberID(long subscriberID) throws DaoException;
	
}
