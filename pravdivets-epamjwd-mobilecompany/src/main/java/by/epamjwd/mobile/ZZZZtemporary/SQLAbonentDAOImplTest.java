package by.epamjwd.mobile.ZZZZtemporary;

import java.util.List;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.impl.SQLAbonentDAOImpl;

public class SQLAbonentDAOImplTest {

	public static void main(String[] args) throws DaoException {
		SQLAbonentDAOImpl ab = new SQLAbonentDAOImpl();
		
		//System.out.println(ab.getAbonentByPhoneNumber(442532486));
		List<Abonent> abList = ab.getAbonentsByEmail("abonent9@mail.ru");
		
		for(Abonent abonent : abList) {
			System.out.println(abonent);
		}

	}

}
