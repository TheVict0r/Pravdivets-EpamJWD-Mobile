package by.epamjwd.mobile.service.impl;

import java.util.List;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.PlanDAO;
import by.epamjwd.mobile.service.PlanService;

public class PlanServiceImpl implements PlanService{

	DAOProvider provider = DAOProvider.getInstance();
	PlanDAO tariffsDao = provider.getPlanDAO();
	
	
	@Override
	public List<Plan> getAllPlans() {
		return tariffsDao.getAllPlans();
	}

	@Override
	public Plan getTariffPlanByID(int id) {
		return tariffsDao.getPlanByID(id);
	}

}
