package by.epamjwd.mobile.dao.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Service;
import by.epamjwd.mobile.dao.mapper.RowMapper;
import by.epamjwd.mobile.dao.repository.DBColumnName;

public class ServiceRowMapper implements RowMapper<Service>{

	@Override
	public Service map(ResultSet resultSet) throws SQLException {
		Service service = new Service(
			resultSet.getLong  (DBColumnName.SERVICES_ID),
			resultSet.getString(DBColumnName.SERVICES_NAME),
			resultSet.getInt   (DBColumnName.SERVICES_TARIF),
			resultSet.getString(DBColumnName.SERVICES_DESCRIPTION));
		return service;
	}

}
