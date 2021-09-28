package by.epamjwd.mobile.service;

import java.util.List;
import by.epamjwd.mobile.bean.TariffPlan;

public interface PlanService {
	public List<TariffPlan> getAllPlans();
	public TariffPlan getTariffPlanByID(int id);
}
