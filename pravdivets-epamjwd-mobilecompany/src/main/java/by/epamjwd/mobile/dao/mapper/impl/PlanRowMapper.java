package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.controller.repository.DBColumnName;
import by.epamjwd.mobile.dao.mapper.RowMapper;

public class PlanRowMapper implements RowMapper<Plan>{

	@Override
	public Plan map(ResultSet resultSet) throws SQLException {
		Plan plan = new Plan(
			resultSet.getInt(DBColumnName.TARIFF_PLANS_ID),
			resultSet.getString(DBColumnName.TARIFF_PLANS_NAME),
			resultSet.getInt(DBColumnName.TARIFF_PLANS_REGULAR_PAYMENT),
			resultSet.getString(DBColumnName.TARIFF_PLANS_DESCRIPTION),
			resultSet.getInt(DBColumnName.TARIFF_PLANS_WITHIN_NETWORK),
			resultSet.getInt(DBColumnName.TARIFF_PLANS_OTHER_NETWORKS),
			resultSet.getInt(DBColumnName.TARIFF_PLANS_ABROAD),
			resultSet.getInt(DBColumnName.TARIFF_PLANS_VIDEOCALL),
			resultSet.getInt(DBColumnName.TARIFF_PLANS_SMS),
			resultSet.getInt(DBColumnName.TARIFF_PLANS_MMS),
			resultSet.getInt(DBColumnName.TARIFF_PLANS_INTERNET));
		return plan;
	}

}
