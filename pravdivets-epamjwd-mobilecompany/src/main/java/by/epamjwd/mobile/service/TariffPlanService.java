package by.epamjwd.mobile.service;

import java.util.List;
import by.epamjwd.mobile.bean.TariffPlan;

public interface TariffPlanService {
	public List<TariffPlan> getAllTariffPlans();
	public TariffPlan getTariffPlanByID(int id);
}
