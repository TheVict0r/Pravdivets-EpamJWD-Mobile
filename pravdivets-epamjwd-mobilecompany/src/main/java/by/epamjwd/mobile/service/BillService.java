package by.epamjwd.mobile.service;

import java.util.List;

import by.epamjwd.mobile.bean.Bill;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface BillService {

	public List<Bill> getBillListBySubscriberID(long subscriberID) throws ServiceException;
	
}
