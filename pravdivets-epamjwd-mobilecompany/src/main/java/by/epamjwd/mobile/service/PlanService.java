package by.epamjwd.mobile.service;

import java.util.List;
import by.epamjwd.mobile.bean.Plan;

public interface PlanService {
	public List<Plan> getAllPlans();
	public Plan getTariffPlanByID(int id);
}
