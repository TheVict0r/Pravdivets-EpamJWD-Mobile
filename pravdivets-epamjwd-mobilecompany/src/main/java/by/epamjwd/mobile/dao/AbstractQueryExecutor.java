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
import by.epamjwd.mobile.dao.mapper.RowMapper;

public abstract class AbstractQueryExecutor<T extends Identifiable> {
	private final static Logger LOGGER = LogManager.getLogger(AbstractQueryExecutor.class);

	public final static String BASE_NAME = "db";
	public final static int PREPARED_STATEMENT_PARAMETER_FIRST_INDEX = 1;
	
    private final RowMapper<T> rowMapper;

    public AbstractQueryExecutor(RowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
    }

	protected List<T> executeQuery(String query, Object... params) throws DaoException {
		List<T> entities = new ArrayList<>();

		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			PreparedStatement statement = createStatement(connection, query, params);
			ResultSet resultSet = statement.executeQuery();
			entities = createEntitiesList(resultSet);
		} catch (ConnectionPoolException e) {
			LOGGER.error("Unable to take connection.", e);
			throw new RuntimeException("Unable to to take connection", e);
		} catch (SQLException e) {
			LOGGER.error("Unable to execute query", e);
			throw new DaoException("Unable to execute query", e);
		} finally {
			try {
				ConnectionPool.getInstance().releaseConnection(connection);
			} catch (ConnectionPoolException e) {
				LOGGER.error("Unable to release connection to pool", e);
				throw new RuntimeException("Unable to release connection to pool", e);
			}
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
		Connection connection = null;
        try {
			connection = ConnectionPool.getInstance().takeConnection();
    		PreparedStatement statement = createStatement(connection, query, params); 
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                result = generatedKey.getLong(1);
            }
    	} catch (ConnectionPoolException e) {
			LOGGER.error("Unable to take connection.", e);
			throw new RuntimeException("Unable to to take connection", e);
        } catch (SQLException e) {
            LOGGER.error("Unable to execute insert query", e);
            throw new DaoException("Unable to execute insert query", e);
		} finally {
			try {
				ConnectionPool.getInstance().releaseConnection(connection);
			} catch (ConnectionPoolException e) {
				LOGGER.error("Unable to release connection to pool", e);
				throw new RuntimeException("Unable to release connection to pool", e);
			}
		}
        return result;
    }

    
    protected void executeUpdateQuery(String query, Object... params) throws DaoException {
		Connection connection = null;
    	try {
			connection = ConnectionPool.getInstance().takeConnection();
    		PreparedStatement statement = createStatement(connection, query, params); 
            statement.executeUpdate();
    	} catch (ConnectionPoolException e) {
			LOGGER.error("Unable to take connection.", e);
			throw new RuntimeException("Unable to to take connection", e);
			} catch (SQLException e) {
            LOGGER.error("Unable to execute update query", e);
            throw new DaoException("Unable to execute update query", e);
		} finally {
			try {
				ConnectionPool.getInstance().releaseConnection(connection);
			} catch (ConnectionPoolException e) {
				LOGGER.error("Unable to release connection to pool", e);
				throw new RuntimeException("Unable to release connection to pool", e);
			}
		}
    }

    
	private PreparedStatement createStatement(Connection connection, String query, Object... params) throws DaoException {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < params.length; i++) {
				preparedStatement.setObject(i + PREPARED_STATEMENT_PARAMETER_FIRST_INDEX, params[i]);
			}
			return preparedStatement;
		} catch (SQLException e) {
			LOGGER.error("Unable to create statement.", e);
			throw new DaoException("Unable to create statement.", e);
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