package by.stn.data_parser.parser;

import by.stn.data_parser.data_record.DataRecord;
import by.stn.data_parser.tokens.DateToken;
import by.stn.data_parser.tokens.TextNumberPairToken;
import lombok.Getter;

import java.util.Date;

public class DataParserHelper {
	@Getter
	private static final String DATE_FORMAT = "MMMM yyyy";
	private static final String INVALID_NUMBER_SEPARATOR = ",";
	private static final String VALID_NUMBER_SEPARATOR = ".";
	private final static String EMPTY = "";

	public TextNumberPairToken createTextNumberPairToken(String text, String numberValue) {
		DataRecord.Currency currency = getCurrency(numberValue);
		Double number = getNumber(numberValue, currency);

		return new TextNumberPairToken(text, number, currency);
	}

	public DateToken createDateToken(Date date) {
		return new DateToken(date);
	}

	public String getFilePath(String relativeFilePath) {
		ClassLoader classLoader = getClass().getClassLoader();
		return classLoader.getResource(relativeFilePath).getFile();
	}

	private DataRecord.Currency getCurrency(String numberValue) {
		for (DataRecord.Currency currency : DataRecord.Currency.values()) {
			if (numberValue.contains(currency.getSymbol())) {
				return currency;
			}
		}
		return DataRecord.Currency.UNDEFINED;
	}

	private Double getNumber(String numberValue, DataRecord.Currency currency) {
		if (!currency.equals(DataRecord.Currency.UNDEFINED)) {
			numberValue = numberValue.replace(currency.getSymbol(), EMPTY);
		}
		if (numberValue.contains(INVALID_NUMBER_SEPARATOR)) {
			numberValue = numberValue.replace(INVALID_NUMBER_SEPARATOR, VALID_NUMBER_SEPARATOR);
		}
		return Double.valueOf(numberValue);
	}
}