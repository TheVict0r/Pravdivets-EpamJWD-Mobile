package by.epamjwd.mobile.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamjwd.mobile.bean.Identifiable;
import by.epamjwd.mobile.dao.connectionpool.ConnectionPool;
import by.epamjwd.mobile.dao.connectionpool.exception.ConnectionPoolException;
import by.epamjwd.mobile.dao.exception.DaoException;
import by.epamjwd.mobile.dao.impl.SQLUserDAOImpl;
import by.epamjwd.mobile.dao.mapper.RowMapper;

public abstract class AbstractQueryExecutor<T extends Identifiable> {
	private final static Logger LOGGER = LogManager.getLogger(AbstractQueryExecutor.class);

	public final static String BASE_NAME = "db";
	// CHECK CODE DUPLICATION FOR "BASE_NAME" WITH LISTENER

	
    private final RowMapper<T> rowMapper;

    public AbstractQueryExecutor(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> entities = new ArrayList<>();
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            entities = createEntitiesList(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Unable to execute query", e);
            throw new DaoException("Unable to execute query", e);
        }
        
        return entities;
    }

    protected Optional<T> executeQueryForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = executeQuery(query, params);
        if (items.isEmpty()) {
            return Optional.empty();
        }

        if (!(items.size() == 1)) {
            return Optional.empty();
        }

        return Optional.of(items.get(0));
    }

    protected long executeInsertQuery(String query, Object... params) throws DaoException {
        long result = 0;
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                result = generatedKey.getLong(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to execute insert query", e);
            throw new DaoException("Unable to execute insert query", e);
        }
        return result;
    }

    protected void executeUpdateQuery(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Unable to execute update query", e);
            throw new DaoException("Unable to execute update query", e);
        }
    }

	private PreparedStatement createStatement(String query, Object... params) throws DaoException {
		ConnectionPool pool = null;
		Connection connection = null;
		try {
			pool = ConnectionPool.getInstance();
			pool.initPoolData(BASE_NAME);
			connection = pool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < params.length; i++) {
				preparedStatement.setObject(i + 1, params[i]);
			}
			
			return preparedStatement;
		} catch (ConnectionPoolException e) {
			LOGGER.error("Unable to retrieve connection.", e);
            throw new DaoException("Unable to retrieve connection.", e);
            } catch (SQLException e) {
			LOGGER.error("Unable to create statement.", e);
			throw new DaoException("Unable to create statement.", e);
		} catch (NullPointerException e) {
			LOGGER.error("NullPointerException in ConnectionPool", e);
			throw new DaoException("NullPointerException in ConnectionPool", e);
		} finally {
			try {
				pool.releaseConnection(connection);
			} catch (ConnectionPoolException e) {
				LOGGER.error("Unable to release connection", e);
				throw new DaoException("Unable to release connection", e);
			}
		}
	}

    private List<T> createEntitiesList(ResultSet resultSet) throws DaoException {
        List<T> entities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T entity = rowMapper.map(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            LOGGER.error("Unable to create entity list.", e);
            throw new DaoException("Unable to create entity list.", e);
        }
        return entities;
    }
}