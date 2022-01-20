package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Identifiable;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.mapper.RowMapper;

public abstract class AbstractDao<T extends Identifiable> extends AbstractQueryExecutor<T> implements Dao<T> {
    private final String tableName;

    public AbstractDao(RowMapper<T> rowMapper, String tableName) {
        super(rowMapper);
        this.tableName = tableName;
    }

	/**
	 * Retrieves all <T> entities 
	 * 
	 * @return list of all entities
	 * 
	 * @throws DaoException if SQLException occurs
	 */
    @Override
    public List<T> findAll() throws DaoException {
        String query = "SELECT * FROM " + tableName;
        return executeQuery(query);
    }

    
	/**
	 * Retrieves all entities from table  
     * where entities IDs are in descending order
	 * 
     * @return List of all entities in the table in descending order by ID
	 * 
	 * @throws DaoException if SQLException occurs
	 */
    @Override
    public List<T> findALLDescending() throws DaoException {
    	String query = "SELECT * FROM " + tableName + " ORDER BY id DESC";
    	return executeQuery(query);
    }

    /**
     * Retrieves entity object from table by ID
     *
     * @param id ID of entity to find
     * 
     * @return optional Entity object from table
     * 
     * @throws DaoException if SQLException occurs
     */
    @Override
    public Optional<T> findById(long id) throws DaoException {
        String query = "SELECT * FROM " + tableName + " WHERE id=?";
        return executeQueryForSingleResult(query, id);
    }

    /**
     * Removes entity from table by ID.
     *
     * @param id ID of entity to delete
     * 
	 * @throws DaoException if SQLException occurs
	 */
    @Override
    public void removeById(long id) throws DaoException {
        String deleteQuery = "DELETE FROM " + tableName + " WHERE id=?";
        executeUpdateQuery(deleteQuery, id);
    }
}
