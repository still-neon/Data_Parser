package by.stn.data_parser.entity;

import by.stn.data_parser.connection.ConnectionFactory;
import by.stn.data_parser.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractEntityDao<T extends by.stn.data_parser.entity.Entity> implements EntityDao<T> {
	private static final String GET_ENTITY_QUERY_FORMAT = "SELECT * FROM %s WHERE id=%d";
	private static final String GET_ALL_ENTITIES_QUERY_FORMAT = "SELECT * FROM %s";
	private static final String DELETE_ENTITY_QUERY_FORMAT = "DELETE FROM %s WHERE id=%d";
	private static final String UPDATE_ENTITY_QUERY_FORMAT = "UPDATE %s SET %s WHERE id=?";
	private static final String INSERT_ENTITY_QUERY_FORMAT = "INSERT INTO %s (id,%s) VALUES (DEFAULT,%s) RETURNING id";
	private static final String PREPARED_QUERY_PARAMETER_SIGN = "?";
	private static final String PREPARED_QUERY_EQUAL_SIGN = "=";
	private static final String PREPARED_QUERY_SEPARATOR_SIGN = ",";
	private static final int ID_COLUMN_INDEX = 1;

	protected abstract T getEntity(ResultSet resultSet) throws Exception;

	protected abstract void setUpdateQueryArguments(PreparedStatement preparedStatement, T entity) throws SQLException;

	protected abstract void setInsertQueryArguments(PreparedStatement preparedStatement, T entity) throws SQLException;

	private final Class<T> type;

	public AbstractEntityDao(Class<T> type) {
		this.type = type;
	}

	@Override
	public T getEntity(long id) throws Exception {
		ResultSet resultSet = getResultSet(GET_ENTITY_QUERY_FORMAT, id);
		return resultSet.next() ? getEntity(resultSet) : null;
	}

	@Override
	public List<T> getAllEntities() throws Exception {
		return getEntities(getResultSet(GET_ALL_ENTITIES_QUERY_FORMAT));
	}

	@Override
	public long saveOrUpdateEntity(T entity) throws SQLException {
		Long id = entity.getId();
		PreparedStatement preparedStatement;
		try (Connection connection = ConnectionFactory.getConnection()) {
			if (id == null) {
				preparedStatement = connection.prepareStatement(getPreparedQueryForInsert());
				setInsertQueryArguments(preparedStatement, entity);
				ResultSet resultSet = preparedStatement.executeQuery();
				resultSet.next();
				id = resultSet.getLong(ID_COLUMN_INDEX);
			} else {
				preparedStatement = connection.prepareStatement(getPreparedQueryForUpdate());
				setUpdateQueryArguments(preparedStatement, entity);
				preparedStatement.executeUpdate();
			}
		}
		return id;
	}

	@Override
	public boolean deleteEntity(long id) throws SQLException {
		int result;
		try (Connection con = ConnectionFactory.getConnection()) {
			result = con.createStatement().executeUpdate(String.format(DELETE_ENTITY_QUERY_FORMAT, getTableName(), id));
		}
		return result > 0;
	}

	private String getTableName() {
		return type.getAnnotation(Entity.class).tableName();
	}

	private String[] getColumnsNames() {
		return type.getAnnotation(Entity.class).columnsNames();
	}

	private ResultSet getResultSet(String queryFormat, long... id) throws SQLException {
		ResultSet rs;
		try (Connection connection = ConnectionFactory.getConnection()) {
			Statement statement = connection.createStatement();
			if (id.length > 0) {
				rs = statement.executeQuery(String.format(queryFormat, getTableName(), id[0]));
			} else {
				rs = statement.executeQuery(String.format(queryFormat, getTableName()));
			}
		}
		return rs;
	}

	private List<T> getEntities(ResultSet resultSet) throws Exception {
		List<T> result = new ArrayList<>();
		while (resultSet.next()) {
			T entity = getEntity(resultSet);
			result.add(entity);
		}
		return result;
	}

	private String getPreparedQueryForUpdate() {
		StringBuilder stringBuilder = new StringBuilder();
		for (String column : getColumnsNames()) {
			stringBuilder.append(column).append(PREPARED_QUERY_EQUAL_SIGN).append(PREPARED_QUERY_SEPARATOR_SIGN);
		}
		return String.format(UPDATE_ENTITY_QUERY_FORMAT, getTableName(), stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
	}

	private String getPreparedQueryForInsert() {
		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();

		for (String column : getColumnsNames()) {
			columns.append(column).append(PREPARED_QUERY_SEPARATOR_SIGN);
			values.append(PREPARED_QUERY_PARAMETER_SIGN).append(PREPARED_QUERY_SEPARATOR_SIGN);
		}
		return String.format(INSERT_ENTITY_QUERY_FORMAT, getTableName(), columns.deleteCharAt(columns.length() - 1).toString(), values.deleteCharAt(values.length() - 1).toString());
	}
}