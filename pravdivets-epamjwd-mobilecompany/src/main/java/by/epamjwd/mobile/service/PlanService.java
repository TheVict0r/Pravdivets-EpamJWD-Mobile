package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface PlanService {
	
	List<Plan> findAllPlans() throws ServiceException;
	
	Optional<Plan> findPlanByID(long id) throws ServiceException;
	
	Optional<Plan> suggestPlan(int withinNetwork, int otherNetworks, int abroad, int videocall, 
			int sms, int mms, int internet) throws ServiceException;
	
	boolean isPlanExist(String name) throws ServiceException;
	
	Plan buildPlan(String name, String description, int regularPayment, int upfrontPayment, 
			int withinNetwork, int otherNetworks, int abroad, int videocall, int sms, int mms, 
			int internet);
	
	long addPlan(Plan plan) throws ServiceException;
	
}
