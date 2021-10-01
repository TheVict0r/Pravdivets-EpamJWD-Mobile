package by.epamjwd.mobile.dao;

import java.util.List;

import by.epamjwd.mobile.bean.Plan;

public interface PlanDAO {
	public List<Plan> getAllPlans();
	
	public Plan getPlanByID(int id);

}
