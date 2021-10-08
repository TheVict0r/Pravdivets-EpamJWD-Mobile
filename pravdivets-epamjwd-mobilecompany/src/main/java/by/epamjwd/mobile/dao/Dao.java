package by.epamjwd.mobile.dao;

import java.util.List;
import java.util.Optional;

import by.epamjwd.mobile.bean.Abonent;
import by.epamjwd.mobile.bean.Plan;
import by.epamjwd.mobile.dao.exception.DaoException;

public interface Dao<T> {

    /**
     * Method to get all entities from table
     *
     * @return List of all entities in the table
     * @throws DaoException
     */
    List<T> findAll() throws DaoException;

    /**
     * Method to get entity object from table by ID
     *
     * @param id ID of entity to find
     * @return optional Entity object from table
     * @throws DaoException
     */
    Optional<T> findById(int id) throws DaoException;

    /**
     * Method to save entity into table
     *
     * @param item entity object to save
     * @throws DaoException
     */
    long save(T item) throws DaoException;

    /**
     * Method to remove entity from table by ID
     *
     * @param id ID of entity to delete
     * @throws DaoException
     */
    void removeById(int id) throws DaoException;

	
}
