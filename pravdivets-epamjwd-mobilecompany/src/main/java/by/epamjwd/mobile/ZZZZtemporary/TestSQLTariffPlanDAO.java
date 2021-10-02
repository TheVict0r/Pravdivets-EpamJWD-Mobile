package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.dao.impl.SQLPlanDAOImpl;

public class TestSQLTariffPlanDAO {
	public static void main(String[] args) {

		SQLPlanDAOImpl tPDao = new SQLPlanDAOImpl();
		
		//System.out.println(tPDao.getAllTariffPlans());
		System.out.println(tPDao.getPlanByID(2));
		
		
		
	}
}
