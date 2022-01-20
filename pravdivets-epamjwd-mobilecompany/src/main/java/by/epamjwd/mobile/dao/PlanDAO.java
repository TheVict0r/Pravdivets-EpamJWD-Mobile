package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface PlanDAO {
	
	List<Plan> findAllPlans() throws DaoException;
	
	Optional<Plan> findPlanByID(long id) throws DaoException;

	Optional<Plan> findPlanByName(String name) throws DaoException;

	long addPlan(Plan plan) throws DaoException;

}
