package by.epamjwd.mobile.service;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.service.exception.ServiceException;

public interface PlanService {
	public List<Plan> findAllPlans() throws ServiceException;
	public Optional<Plan> findTariffPlanByID(int id) throws ServiceException;
}
