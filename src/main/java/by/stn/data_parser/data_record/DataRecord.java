package by.stn.data_parser.data_record;

import by.stn.data_parser.Entity;
import by.stn.data_parser.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(tableName = "records", columnsNames = {"name", "value", "currency", "description", "date", "timestamp"})
@Getter
@Setter
public class DataRecord extends AbstractEntity {
	private String name;
	private String value;
	private Currency currency;
	private String description;
	private Date date;
	private String timestamp;

	public DataRecord(Long id) {
		super(id);
	}

	public enum Currency {
		BYN("Br"), USD("$"), EURO("€"), UNDEFINED("");

		@Getter
		private String symbol;

		Currency(String symbol) {
			this.symbol = symbol;
		}
	}
}