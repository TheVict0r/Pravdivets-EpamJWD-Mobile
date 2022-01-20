package by.epamjwd.mobile.dao.impl;

import java.util.List;

import by.epamjwd.mobile.bean.Bill;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.BillDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLBillDAOImpl extends AbstractDao<Bill> implements BillDAO{

	public final static String GET_BILL_LIST_BY_SUBSCRIBER_ID = "SELECT * FROM " + DBTableName.BILLS 
			+ " WHERE " + DBColumnName.BILLS_SUBSCRIBER_ID + " = ?";
	
	public SQLBillDAOImpl() {
		super(RowMapperFactory.getInstance().getBillRowMapper(), DBTableName.BILLS);
	}
	
	/**
	 * Retrieves a list of subscribers bills from the database. 
	 * 
	 * @param subscriberID - subscriber's ID
	 * 
	 * @return - a list with subscriber's bills
	 * 
	 * @throws DaoException if SQLException occurs
	 */
	@Override
	public List<Bill> getBillListBySubscriberID(long subscriberID) throws DaoException {
		return executeQuery(GET_BILL_LIST_BY_SUBSCRIBER_ID, subscriberID);
	}


}
