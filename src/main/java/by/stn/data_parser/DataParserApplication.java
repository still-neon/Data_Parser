package by.stn.data_parser;

import by.stn.data_parser.data_record.DataRecord;
import by.stn.data_parser.facade.Facade;
import by.stn.data_parser.parser.DataParserHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DataParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataParserApplication.class, args);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		DataRecord dataRecord = new DataRecord(1L);

		dataRecord.setTimestamp("AM");

		try {
			dataRecord.setDate(format.parse("27/01/2019"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		dataRecord.setDescription("dsfdsfs");
		dataRecord.setCurrency(DataRecord.Currency.USD);
		dataRecord.setValue("value new");
		dataRecord.setName("name new");

		List<DataRecord> dataRecords = new ArrayList<>();
		dataRecords.add(dataRecord);

		new Facade().save(dataRecords);
	}
}