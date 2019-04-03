package by.stn.data_parser.data_record;

import by.stn.data_parser.entity.AbstractEntityDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class DataRecordDaoImpl extends AbstractEntityDao<DataRecord> implements DataRecordDao {

	public DataRecordDaoImpl() throws ClassNotFoundException {
		super((Class<DataRecord>) Class.forName(DataRecord.class.getName()));
	}

	@Override
	protected DataRecord getEntity(ResultSet resultSet) throws Exception {
		DataRecord dataRecord = new DataRecord((long) resultSet.getInt("id"));
		dataRecord.setName(resultSet.getString("name"));
		dataRecord.setValue(resultSet.getString("value"));
		dataRecord.setCurrency(DataRecord.Currency.valueOf(resultSet.getString("currency")));
		dataRecord.setDescription(resultSet.getString("description"));
		dataRecord.setDate(resultSet.getDate("date"));
		dataRecord.setTimestamp(resultSet.getString("timestamp"));

		return dataRecord;
	}

	@Override
	protected void setUpdateQueryArguments(PreparedStatement preparedStatement, DataRecord entity) throws SQLException {
		setInsertQueryArguments(preparedStatement, entity);
		preparedStatement.setLong(1, entity.getId());
	}

	@Override
	protected void setInsertQueryArguments(PreparedStatement preparedStatement, DataRecord entity) throws SQLException {
		preparedStatement.setString(2, entity.getName());
		preparedStatement.setString(3, entity.getValue());
		preparedStatement.setString(4, entity.getCurrency().getSymbol());
		preparedStatement.setString(5, entity.getDescription());
		preparedStatement.setDate(6, new Date(entity.getDate().getTime()));
		preparedStatement.setString(7, entity.getTimestamp());
	}
}