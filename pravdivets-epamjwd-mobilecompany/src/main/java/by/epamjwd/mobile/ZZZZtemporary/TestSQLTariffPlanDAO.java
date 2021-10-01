package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.dao.impl.SQLPlanDAO;

public class TestSQLTariffPlanDAO {
	public static void main(String[] args) {

		SQLPlanDAO tPDao = new SQLPlanDAO();
		
		//System.out.println(tPDao.getAllTariffPlans());
		System.out.println(tPDao.getPlanByID(2));
		
		
		
	}
}
