package by.epamjwd.mobile.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Subscriber;
import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.SubscriberDAO;
import by.epamjwd.mobile.dao.UserDAO;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLSubscriberDAOImpl extends AbstractDao<Subscriber> implements SubscriberDAO{

	public SQLSubscriberDAOImpl() {
        super(RowMapperFactory.getInstance().getSubscriberRowMapper(), DBTableName.SUBSCRIBERS);
	}


	@Override
	public Optional<Subscriber> findSubscriberByPhoneNumber(int phoneNumber) throws DaoException {
		String query = buildFindSubscriberQueryByParameter(DBColumnName.SUBSCRIBERS_PHONE);
		return executeQueryForSingleResult(query, phoneNumber);
	}

	@Override
	public Optional<Subscriber> findSubscriberById(String id) throws DaoException {
		String query = buildFindSubscriberQueryByParameter(DBColumnName.SUBSCRIBERS_ID);
		return executeQueryForSingleResult(query, id);
	}
	
	@Override
	public List<Subscriber> findSubscriberListByUserId(String userId) throws DaoException {
		String query = buildFindSubscriberQueryByParameter(DBColumnName.SUBSCRIBERS_USER_ID);
		return executeQuery(query, userId);
	}

	@Override
	public List<Subscriber> findSubscriberListByPassport(String passport) throws DaoException {
		String query = buildFindSubscriberQueryByUserParameter(DBColumnName.USERS_PASSPORT);
		return executeQuery(query, passport);
	}
	
	private String buildFindSubscriberQuery(){
		String query = new StringBuilder("SELECT * FROM ").append(DBTableName.SUBSCRIBERS)
				.toString();
	
				return query;
	}
	
	private String buildFindSubscriberQueryByParameter(String parameter){
		String query = new StringBuilder(buildFindSubscriberQuery())
				.append(" WHERE ")
				.append(DBTableName.SUBSCRIBERS).append(".").append(parameter).append(" = ?")
				.toString();
		return query;
	}

	private String buildFindSubscriberQueryByUserParameter(String userParameter){
		String query = new StringBuilder(buildFindSubscriberQuery())
				.append(" INNER JOIN ").append(DBTableName.USERS)
				.append(" ON ")
				.append(DBTableName.SUBSCRIBERS).append(".").append(DBColumnName.SUBSCRIBERS_USER_ID)
				.append(" = ")
				.append(DBTableName.USERS).append(".").append(DBColumnName.USERS_ID)
				.append(" WHERE ")
				.append(DBTableName.USERS).append(".").append(userParameter)
				.append(" = ?")
				.toString();
		return query;
	}
	
	@Override
	public List<Subscriber> findSubscriberListByFullName(String firstName, String middleName, String lastName) throws DaoException {
		//переделать на прямой запрос в БД - без привлечения UserDAO
		
		DAOProvider provider = DAOProvider.getInstance();
		UserDAO userDAO = provider.getUserDAO();
		List<User> userList = userDAO.findUsersListByFullName(firstName, middleName, lastName);
		
		List<Subscriber> subscriberList = new ArrayList<>();
		
		for(User user : userList) {
			subscriberList.addAll(findSubscriberListByUserId(String.valueOf(user.getId())));
		}
		return subscriberList;
	}
	

	
//	@Override
//	public void registration(Abonent newAbonent) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Abonent lookAbonent(int idAbonent) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void activateService(int serviceId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void deActivateService(int serviceId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void switchServiceParameters(String oldServiceId, String newServiceId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void changePhoneNumber(String oldPhoneNumber, String newPhoneNumber) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void pause() {
//		// TODO Auto-generated method stub
//		
//	}


	@Override
	public long save(Subscriber item) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}


}
