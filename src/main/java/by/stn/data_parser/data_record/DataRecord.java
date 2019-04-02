package by.stn.data_parser.data_record;

import by.stn.data_parser.entity.AbstractEntity;
import lombok.Getter;

import java.util.Date;

public class DataRecord extends AbstractEntity {
	@Getter
	private long id;
	private String name;
	private String value;
	private Currency currency;
	private String description;
	private Date date;
	private String timestamp;

	public DataRecord(long id) {
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