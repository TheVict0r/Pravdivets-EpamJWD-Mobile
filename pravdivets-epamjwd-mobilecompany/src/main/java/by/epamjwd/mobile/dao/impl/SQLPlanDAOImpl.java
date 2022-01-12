package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;


import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.PlanDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBColumnName;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLPlanDAOImpl extends AbstractDao<Plan> implements PlanDAO{
	public final static String COMMA = ", ";
	public final static String QUESTION_MARK = "=?, ";
	
	public final static String ADD_NEW_PLAN = "INSERT INTO " +
			DBTableName.PLANS + "(" + 
			DBColumnName.PLANS_NAME + COMMA + 
			DBColumnName.PLANS_REGULAR_PAYMENT + COMMA +
			DBColumnName.PLANS_WITHIN_NETWORK + COMMA +
			DBColumnName.PLANS_OTHER_NETWORKS + COMMA +
			DBColumnName.PLANS_ABROAD + COMMA +
			DBColumnName.PLANS_VIDEOCALL + COMMA +
			DBColumnName.PLANS_SMS + COMMA +
			DBColumnName.PLANS_MMS + COMMA +
			DBColumnName.PLANS_INTERNET + COMMA +
			DBColumnName.PLANS_DESCRIPTION + COMMA +
			DBColumnName.PLANS_UPFRONT_PAYMENT + 
			") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public final static String GET_PLAN_BY_NAME = "SELECT * FROM " + 
			DBTableName.PLANS + " WHERE " + 
			DBColumnName.PLANS_NAME + "= ?";
	
	
	public SQLPlanDAOImpl() {
		super(RowMapperFactory.getInstance().getPlanRowMapper(), DBTableName.PLANS);
	}


	@Override
	public List<Plan> getAllPlans() throws DaoException {
		return findAll();
	}

	@Override
	public Optional<Plan> getPlanByID(long id) throws DaoException {
		return findById(id);

	}
	
	@Override
	public Optional<Plan> getPlanByName(String name) throws DaoException {
		return executeQueryForSingleResult(GET_PLAN_BY_NAME, name);
	}


	@Override
	public long addPlan(Plan plan) throws DaoException {
		long planId;
		Object[] params = SQLParametersHelper.provideNewPlanParameters(plan);
		planId = executeInsertQuery(ADD_NEW_PLAN, params);
		return planId;
	}
}
