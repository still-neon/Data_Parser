package by.stn.data_parser.entity;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractEntityDao implements EntityDao {
	@Override
	public Entity get(long id) throws Exception {
		return null;
	}

	@Override
	public List<Entity> getAll() throws Exception {
		return null;
	}

	@Override
	public long saveOrUpdate(Entity entity) throws SQLException {
		return 0;
	}

	@Override
	public boolean delete(long id) throws SQLException {
		return false;
	}
}