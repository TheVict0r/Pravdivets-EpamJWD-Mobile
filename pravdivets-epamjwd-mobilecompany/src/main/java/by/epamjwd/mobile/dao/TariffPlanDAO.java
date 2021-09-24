package by.epamjwd.mobile.dao;

import java.util.List;

import by.epamjwd.mobile.bean.TariffPlan;

public interface TariffPlanDAO {
	public List<TariffPlan> getAllTariffPlans();
	
	public TariffPlan getTariffPlanByID(int id);

}
