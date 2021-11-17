package by.epamjwd.mobile.service.impl;

import java.util.List;

import by.epamjwd.mobile.bean.Bill;
import by.epamjwd.mobile.dao.BillDAO;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.BillService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class BillServiceImpl implements BillService{

	
	BillDAO billDao = DAOProvider.getInstance().getBillDAO();

	
	@Override
	public List<Bill> getBillListBySubscriberID(long subscriberID) throws ServiceException {
		
		List<Bill> billsList;
		
		try {
			billsList = billDao.getBillListBySubscriberID(subscriberID);
			if(billsList.isEmpty()) {
				billsList.add(new Bill());
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		} if (billsList.isEmpty()) {
			throw new ServiceException("Empty bills list for subscriber ID " + subscriberID);
		}
		return billsList;
	}

}
