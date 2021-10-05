package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.impl.SQLAbonentDAOImpl;

public class SQLAbonentDAOImplTest {

	public static void main(String[] args) throws DaoException {
		SQLAbonentDAOImpl ab = new SQLAbonentDAOImpl();
		
		System.out.println(ab.getAbonentByPhoneNumber(442532486));

	}

}
