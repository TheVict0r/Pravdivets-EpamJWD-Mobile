package by.epamjwd.mobile.service.impl;

import java.util.List;

import by.epamjwd.mobile.bean.TariffPlan;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.TariffPlanDAO;
import by.epamjwd.mobile.service.TariffPlanService;

public class TariffPlanServiceImpl implements TariffPlanService{

	DAOProvider provider = DAOProvider.getInstance();
	TariffPlanDAO tariffsDao = provider.getTariffPlanDAO();
	
	
	@Override
	public List<TariffPlan> getAllTariffPlans() {
		return tariffsDao.getAllTariffPlans();
	}

	@Override
	public TariffPlan getTariffPlanByID(int id) {
		return tariffsDao.getTariffPlanByID(id);
	}

}
