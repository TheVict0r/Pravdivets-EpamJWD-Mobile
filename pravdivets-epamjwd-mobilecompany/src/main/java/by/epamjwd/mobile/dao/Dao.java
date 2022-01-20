package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.dao.exception.DaoException;

public interface Dao<T> {

    /**
     * Retrieves all entities from table.
     *
     * @return List of all entities in the table
     * 
     * @throws DaoException
     */
    List<T> findAll() throws DaoException;

    /**
     * Retrieves all entities from table  
     * where entities IDs are in descending order.
     *
     * @return List of all entities in the table in descending order by ID
     * 
     * @throws DaoException
     */
    List<T> findALLDescending() throws DaoException;
    
    /**
     * Retrieves entity object from table by ID.
     *
     * @param id ID of entity to find
     * 
     * @return optional Entity object from table
     * 
     * @throws DaoException
     */
    Optional<T> findById(long id) throws DaoException;


    /**
     * Removes entity from table by ID.
     *
     * @param id ID of entity to delete
     * 
     * @throws DaoException
     */
    void removeById(long id) throws DaoException;


	
}
