package by.epamjwd.mobile.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.PlanDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

public class PlanServiceImpl implements PlanService{
	public final static long EMPTY_ID = 0L;
	public final static long ERROR_ID = -1L;
	PlanDAO planDao = DAOProvider.getInstance().getPlanDAO();

	@Override
	public List<Plan> findAllPlans() throws ServiceException {
		try {
			return planDao.getAllPlans();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Optional<Plan> findPlanByID(long id) throws ServiceException {
		try {
			return planDao.getPlanByID(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Plan suggestPlan(int withinNetwork, int otherNetworks, 
			int abroad, int videocall, int sms, int mms, int internet) throws ServiceException {
		Plan plan = null;
		long planId = 0;
		long minExpences = Long.MAX_VALUE;
		try {
			List<Plan> allPlans = planDao.getAllPlans();
			
			for(Plan planTmp : allPlans) {
				long planExpences = calculateMonthlyExpences(planTmp, withinNetwork, otherNetworks, 
						abroad, videocall, sms, mms, internet);
				if(planExpences <= minExpences) {
					minExpences = planExpences;
					planId = planTmp.getId();
				}
			}
			plan = planDao.getPlanByID(planId).get();
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

	@Override
	public boolean isPlanExist(String name) throws ServiceException  {
		Optional<Plan> planOptional = Optional.empty();
		try {
			planOptional = planDao.getPlanByName(name);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return planOptional.isPresent();
	}

	@Override
	public Plan buildPlan(String name, String description, int regularPayment, 
			int upfrontPayment, int withinNetwork, int otherNetworks, int abroad, 
			int videocall, int sms, int mms, int internet) {
		return new Plan(EMPTY_ID, name,  regularPayment, upfrontPayment, description, 
				withinNetwork, otherNetworks, abroad, videocall, sms, mms, internet);
	}

	@Override
	public long addNewPlan(Plan plan) throws ServiceException {
		long planId = ERROR_ID;
		if (InputDataValidator.isPlanValid(plan)) {
			try {
				planId = planDao.addPlan(plan);
			} catch (DaoException e) {
				throw new ServiceException(e);
			}
		}
		return planId;
}
	
}