package by.epamjwd.mobile.service.impl;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.PlanDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.exception.ServiceException;

public class PlanServiceImpl implements PlanService{

	DAOProvider provider = DAOProvider.getInstance();
	PlanDAO tariffsDao = provider.getPlanDAO();
	
	
	@Override
	public List<Plan> findAllPlans() throws ServiceException {
		try {
			return tariffsDao.getAllPlans();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<Plan> findTariffPlanByID(int id) throws ServiceException {
		try {
			return tariffsDao.getPlanByID(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
	}

}
