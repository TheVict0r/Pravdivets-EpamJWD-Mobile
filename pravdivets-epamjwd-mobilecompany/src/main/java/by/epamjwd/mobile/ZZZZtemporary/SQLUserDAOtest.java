package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.impl.SQLUserDAOImpl;

public class SQLUserDAOtest {
public static void main(String[] args) throws DaoException {

	String email1 = "abonent7@mail.ru";
	String email2 = "consultant1@mail.ru";
	String email3 = "admin2@gmail.com";
	
	SQLUserDAOImpl sqlUserDao = new SQLUserDAOImpl();
	
	User user1 = sqlUserDao.findUserByEmail(email1).get();
	User user2 = sqlUserDao.findUserByEmail(email2).get();
	User user3 = sqlUserDao.findUserByEmail(email3).get();
	
}
}
