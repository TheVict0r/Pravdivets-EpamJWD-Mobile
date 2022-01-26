package by.epamjwd.mobile.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamjwd.mobile.bean.Identifiable;

public interface RowMapper<T extends Identifiable> {

    /**
     * Method to create entity object from ResultSet.
     *
     * @param resultSet {@link ResultSet} pointer that are set to row data to be mapped on entity object
     * 
     * @return Entity object with fields set from row data
     * 
     * @throws SQLException
     */
    T map(ResultSet resultSet) throws SQLException;
}