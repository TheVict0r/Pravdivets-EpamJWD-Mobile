package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.bean.User;
import by.epamjwd.mobile.dao.impl.SQLUserDAOImpl;

public class SQLUserDAOtest {
public static void main(String[] args) {

	String email1 = "abonent7@mail.ru";
	String email2 = "consultant1@mail.ru";
	String email3 = "admin2@gmail.com";
	
	SQLUserDAOImpl sqlUserDao = new SQLUserDAOImpl();
	
	User user1 = sqlUserDao.getUserByEmail(email1);
	User user2 = sqlUserDao.getUserByEmail(email2);
	User user3 = sqlUserDao.getUserByEmail(email3);
	
	System.out.println(user1);
	System.out.println(user2);
	System.out.println(user3);
}
}
