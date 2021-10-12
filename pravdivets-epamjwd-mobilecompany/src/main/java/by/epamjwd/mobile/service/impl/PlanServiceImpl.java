package by.epamjwd.mobile.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

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
	public Plan findTariffPlanByID(long id) throws ServiceException {
		try {
			return tariffsDao.getPlanByID(id).get();
		} catch (DaoException e) {
			throw new ServiceException(e);
		} catch (NoSuchElementException e) {
			throw new ServiceException("The Optional<Plan> contains null for ID - " + id + e);
		}
	}

	@Override
	public Plan suggestPlan(int withinNetwork, int otherNetworks, 
			int abroad, int videocall, int sms, int mms, int internet) throws ServiceException {
		Plan plan = null;
		long planId = 0;
		long minExpences = Long.MAX_VALUE;
		try {
			List<Plan> allPlans = tariffsDao.getAllPlans();
			
			for(Plan planTmp : allPlans) {
				long planExpences = calculateMonthlyExpences(planTmp, withinNetwork, otherNetworks, 
						abroad, videocall, sms, mms, internet);
				if(planExpences <= minExpences) {
					minExpences = planExpences;
					planId = planTmp.getId();
				}
			}
			plan = tariffsDao.getPlanByID(planId).get();
		} catch (DaoException e) {
			throw new ServiceException(e);
		} catch (NoSuchElementException e) {
			throw new ServiceException("The Optional<Plan> contains null for ID - " + planId + e);
		}
		return plan;
	}

	private long calculateMonthlyExpences(Plan plan, int withinNetwork, int otherNetworks, 
			int abroad, int videocall, int sms, int mms, int internet) {
		
		int monthlyExpences = plan.getRegularPayment() 
				+ plan.getPriceWithinNetwork() * withinNetwork
				+ plan.getPriceOtherNetworks() * otherNetworks
				+ plan.getPriceAbroad() * abroad
				+ plan.getPriceVideocall() * videocall
				+ plan.getPriceSMS() * sms
				+ plan.getPriceMMS() * mms
				+ plan.getPriceInternet() * internet;
		
		return monthlyExpences;
	}
	
}
