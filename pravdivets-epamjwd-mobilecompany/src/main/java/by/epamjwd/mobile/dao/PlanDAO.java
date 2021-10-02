package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface PlanDAO {
	public List<Plan> getAllPlans() throws DaoException;
	
	public Optional<Plan> getPlanByID(int id) throws DaoException;

}
