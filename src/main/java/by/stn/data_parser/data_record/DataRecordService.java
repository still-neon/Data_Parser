package by.stn.data_parser.data_record;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class DataRecordService {
	@Setter
	private DataRecordDao dataRecordDao;

	public DataRecordService() {
		try {
			dataRecordDao = new DataRecordDaoImpl();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<DataRecord> getDataRecords() throws Exception {
		return dataRecordDao.getAllEntities();
	}

	public void save(List<DataRecord> updatedData) throws Exception {
//		List<DataRecord> dbData = dataRecordDao.getAllEntities();
//
//		for (DataRecord callsLogEntry : getDeletedData(updatedData, dbData)) {
//			dataRecordDao.deleteEntity(callsLogEntry.getId());
//		}

		for (DataRecord callsLogEntry : updatedData) {
			dataRecordDao.saveOrUpdateEntity(callsLogEntry);
		}
	}

	private List<DataRecord> getSavedData(List<DataRecord> updatedData, List<DataRecord> dbData) {
		for (DataRecord dataRecord : dbData) {
			if (updatedData.contains(dataRecord)) {
				updatedData.remove(dataRecord);
			}
		}
		return updatedData;
	}

	private List<DataRecord> getDeletedData(List<DataRecord> updatedData, List<DataRecord> dbData) {
		List<DataRecord> deletedData = new ArrayList<>();
		for (DataRecord dataRecord : dbData) {
			if (!updatedData.contains(dataRecord)) {
				deletedData.add(dataRecord);
			}
		}
		return deletedData;
	}
}