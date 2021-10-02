package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface PlanService {
	public List<Plan> getAllPlans() throws ServiceException;
	public Optional<Plan> getTariffPlanByID(int id) throws ServiceException;
}
