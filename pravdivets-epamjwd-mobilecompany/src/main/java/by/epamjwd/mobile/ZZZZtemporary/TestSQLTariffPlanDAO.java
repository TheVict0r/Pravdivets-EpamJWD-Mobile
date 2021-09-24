package by.epamjwd.mobile.ZZZZtemporary;

import by.epamjwd.mobile.dao.impl.SQLTariffPlanDAO;

public class TestSQLTariffPlanDAO {
	public static void main(String[] args) {

		SQLTariffPlanDAO tPDao = new SQLTariffPlanDAO();
		
		System.out.println(tPDao.getAllTariffPlans());
		
		
		
	}
}
