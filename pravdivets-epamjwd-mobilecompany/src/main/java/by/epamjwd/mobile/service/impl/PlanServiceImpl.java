package by.epamjwd.mobile.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.dao.DAOProvider;
import by.epamjwd.mobile.dao.PlanDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.service.PlanService;
import by.epamjwd.mobile.service.exception.ServiceException;
import by.epamjwd.mobile.service.validation.InputDataValidator;

/**
 * Class provides the operations with tariff plans
 */
public class PlanServiceImpl implements PlanService{
	private final static long EMPTY_ID = 0L;
	private final static long ERROR_ID = -1L;
	private final static Logger LOGGER = LogManager.getLogger(PlanServiceImpl.class);

	PlanDAO planDao = DAOProvider.getInstance().getPlanDAO();

	/**
	 * Provides all actual tariff plans currently exists.
	 * 
	 * @return Array List containing all current tariff plans
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting all tariff plans from the data storage
	 */
	@Override
	public List<Plan> findAllPlans() throws ServiceException {
		try {
			return planDao.getAllPlans();
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Provides tariff plan retrieved by it's ID.
	 * 
	 * @param id - ID of tariff plan
	 * @return tariff plan as an Optional value
	 * @throws ServiceException in the case when DaoException 
	 * occurs while getting tariff plan from the data storage
	 */
	@Override
	public Optional<Plan> findPlanByID(long id) throws ServiceException {
		try {
			return planDao.getPlanByID(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Finds the most profitable tariff plan (with the lowest monthly costs) 
	 * based on the traffic on different directions as well as other components of the tariff.
	 * 
	 * @param withinNetwork - monthly traffic in minutes for calls within the network
	 * @param otherNetworks - monthly traffic in minutes for calls to other phone networks
	 * @param abroad - monthly traffic in minutes for calls abroad 
	 * @param videocall - monthly traffic in minutes for videocalls
	 * @param sms - monthly number of SMS
	 * @param mms - monthly number of MMS
	 * @param internet - monthly traffic in GB
	 * 
	 * @throws ServiceException in the case when DaoException occurs while getting 
	 * all plans list from data storage or getting tariff plan by ID
	 *  
	 * @return most profitable tariff plan as an Optional value
	 */
	@Override
	public Optional<Plan> suggestPlan(int withinNetwork, int otherNetworks, int abroad, int videocall, int sms, int mms,
			int internet) throws ServiceException {
		Optional<Plan> planOptional = Optional.empty();
		long planId = EMPTY_ID;
		long minExpences = Long.MAX_VALUE;
		try {
			List<Plan> allPlans = planDao.getAllPlans();
			for (Plan planTmp : allPlans) {
				long planExpences = calculateMonthlyExpences(planTmp, withinNetwork, otherNetworks, abroad, videocall,
						sms, mms, internet);
				if (planExpences <= minExpences) {
					minExpences = planExpences;
					planId = planTmp.getId();
				}
			}
		} catch (DaoException e) {
			LOGGER.error("Error while getting all plans list from database.", e);
			throw new ServiceException(e);
		}

		try {
			planOptional = planDao.getPlanByID(planId);
		} catch (DaoException e) {
			LOGGER.error("Error while getting plan by ID  - " + planId, e);
			throw new ServiceException(e);
		}

		return planOptional;
	}

	/**
	 * Calculates monthly expenses in the case of using the {@code plan}.
	 * 
	 * @param plan - tariff plan (bunch of tariffs for different services of mobile operator)
	 * @param withinNetwork - monthly traffic in minutes for calls within the network
	 * @param otherNetworks - monthly traffic in minutes for calls to other phone networks
	 * @param abroad - monthly traffic in minutes for calls abroad 
	 * @param videocall - monthly traffic in minutes for videocalls
	 * @param sms - monthly number of SMS
	 * @param mms - monthly number of MMS
	 * @param internet - monthly traffic in GB
	 * @return ARPU (Average Revenue Per User) - monthly expenses in the case of using the {@code plan}
	 */
	private long calculateMonthlyExpences(Plan plan, int withinNetwork, int otherNetworks, 
			int abroad, int videocall, int sms, int mms, int internet) {
		
		long arpu = plan.getRegularPayment() 
				+ plan.getPriceWithinNetwork() * withinNetwork
				+ plan.getPriceOtherNetworks() * otherNetworks
				+ plan.getPriceAbroad() * abroad
				+ plan.getPriceVideocall() * videocall
				+ plan.getPriceSMS() * sms
				+ plan.getPriceMMS() * mms
				+ plan.getPriceInternet() * internet;
		
		return arpu;
	}

	/**
	 * Checks the existence of tariff plan by it's {@code name}.
	 * 
	 * @param name  the name of tariff plan
	 * @throws ServiceException in the case when DaoException occurs while 
	 * getting the tariff plan from the data storage
	 */
	@Override
	public boolean doesPlanExist(String name) throws ServiceException  {
		Optional<Plan> planOptional = Optional.empty();
		try {
			planOptional = planDao.getPlanByName(name);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return planOptional.isPresent();
	}

	
	
	/**
	 * Builds new tariff plan with empty ID.
	 * 
	 * @param name - the name of tariff plan
	 * @param description - a short text description of tariff plan
	 * @param regularPayment - monthly regular payment
	 * @param upfrontPayment - amount of money payed by subscriber at the signing of a contract
	 * @param withinNetwork - monthly traffic in minutes for calls within the network
	 * @param otherNetworks - monthly traffic in minutes for calls to other phone networks
	 * @param abroad - monthly traffic in minutes for calls abroad 
	 * @param videocall - monthly traffic in minutes for videocalls
	 * @param sms - monthly number of SMS
	 * @param mms - monthly number of MMS
	 * @param internet - monthly traffic in GB
	 * @return new tariff plan
	 */
	@Override
	public Plan buildPlan(String name, String description, int regularPayment, 
			int upfrontPayment, int withinNetwork, int otherNetworks, int abroad, 
			int videocall, int sms, int mms, int internet) {
		return new Plan(EMPTY_ID, name,  regularPayment, upfrontPayment, description, 
				withinNetwork, otherNetworks, abroad, videocall, sms, mms, internet);
	}

	/**
	 * Adds plan to data storage.
	 * 
	 * @param plan - tariff plan to add
	 * @return the ID of tariff plan in data storage
	 * @throws ServiceException in the case when DaoException occurs while saving plan 
	 * to the data storage
	 */
	@Override
	public long addPlan(Plan plan) throws ServiceException {
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