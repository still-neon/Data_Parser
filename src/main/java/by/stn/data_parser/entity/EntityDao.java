package by.stn.data_parser.entity;

import java.sql.SQLException;
import java.util.List;

public interface EntityDao<T extends Entity> {

	T getEntity(long id) throws Exception;

	List<T> getAllEntities() throws Exception;

	long saveOrUpdateEntity(T entity) throws SQLException;

	boolean deleteEntity(long id) throws SQLException;
}