package by.stn.data_parser.tokens;

import by.stn.data_parser.data_record.DataRecord;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TextNumberPairToken implements Token {
	private String text;
	private Double number;
	private DataRecord.Currency currency;
}