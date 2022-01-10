package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface PlanDAO {
	List<Plan> getAllPlans() throws DaoException;
	
	Optional<Plan> getPlanByID(long id) throws DaoException;

	Optional<Plan> getPlanByName(String name) throws DaoException;

	long addPlan(Plan plan) throws DaoException;

}
