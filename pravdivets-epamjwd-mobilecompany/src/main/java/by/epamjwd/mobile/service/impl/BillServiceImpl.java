package by.epamjwd.mobile.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epamjwd.mobile.bean.Bill;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.BillService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class BillServiceImpl implements BillService {

	/**
	 * Retrieves a list of subscribers bills from data storage.
	 * 
	 * <p>
	 * If there is no any bills yet (for example, in the case if this is a new
	 * subscriber who just signed the contract ), adds an empty bill entity just to
	 * avoid empty table for a new subscriber and to show him the real structure
	 * 
	 * @param subscriberID - subscriber's ID
	 * 
	 * @return - a list with subscriber's bills
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting
	 *                          all bills list from the data storage
	 */
	@Override
	public List<Bill> getBillListBySubscriberID(long subscriberID) throws ServiceException {
		List<Bill> billsList = new ArrayList<>();
		try {
			billsList = DAOProvider.getInstance().getBillDAO().
					getBillListBySubscriberID(subscriberID);
			if (billsList.isEmpty()) {
				billsList.add(new Bill());
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return billsList;
	}

}
