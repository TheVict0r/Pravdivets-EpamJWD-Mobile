package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface PlanService {
	List<Plan> findAllPlans() throws ServiceException;
	Plan findTariffPlanByID(long id) throws ServiceException;
	Plan suggestPlan(int withinNetwork, int otherNetworks, int abroad, int videocall, int sms, int mms,
			int internet) throws ServiceException;
}
