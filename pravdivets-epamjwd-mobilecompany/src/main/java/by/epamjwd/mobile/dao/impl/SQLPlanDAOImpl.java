package by.epamjwd.mobile.dao.impl;

import java.util.List;
import java.util.Optional;


import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.dao.AbstractDao;
import by.epamjwd.mobile.dao.PlanDAO;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapperFactory;
import by.epamjwd.mobile.dao.repository.DBTableName;

public class SQLPlanDAOImpl extends AbstractDao<Plan> implements PlanDAO{

	public SQLPlanDAOImpl() {
		super(RowMapperFactory.getInstance().getPlanRowMapper(), DBTableName.PLANS);
	}


	@Override
	public List<Plan> getAllPlans() throws DaoException {
		return findAll();
	}

	@Override
	public Optional<Plan> getPlanByID(int id) throws DaoException {
		return findById(id);

	}


	@Override
	public long save(Plan item) throws DaoException {
		// TODO Auto-generated method stub
		return 0;
	}

}
