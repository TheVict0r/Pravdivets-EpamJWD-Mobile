package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;

public class PlanRowMapper implements RowMapper<Plan>{

	@Override
	public Plan map(ResultSet resultSet) throws SQLException {
		Plan plan = new Plan(
			resultSet.getLong(DBColumnName.PLANS_ID),
			resultSet.getString(DBColumnName.PLANS_NAME),
			resultSet.getInt(DBColumnName.PLANS_REGULAR_PAYMENT),
			resultSet.getInt(DBColumnName.PLANS_UPFRONT_PAYMENT),
			resultSet.getString(DBColumnName.PLANS_DESCRIPTION),
			resultSet.getInt(DBColumnName.PLANS_WITHIN_NETWORK),
			resultSet.getInt(DBColumnName.PLANS_OTHER_NETWORKS),
			resultSet.getInt(DBColumnName.PLANS_ABROAD),
			resultSet.getInt(DBColumnName.PLANS_VIDEOCALL),
			resultSet.getInt(DBColumnName.PLANS_SMS),
			resultSet.getInt(DBColumnName.PLANS_MMS),
			resultSet.getInt(DBColumnName.PLANS_INTERNET));
		return plan;
	}

}
