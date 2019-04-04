package by.stn.data_parser.facade;

import by.stn.data_parser.data_record.DataRecord;
import by.stn.data_parser.data_record.DataRecordService;

import java.util.List;

public class Facade {
	DataRecordService dataRecordService;

	public Facade() {
		dataRecordService = new DataRecordService();
	}

	public void save(List<DataRecord> dataRecords) {
		try {
			dataRecordService.save(dataRecords);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}