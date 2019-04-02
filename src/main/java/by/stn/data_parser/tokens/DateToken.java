package by.stn.data_parser.tokens;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
public class DateToken implements Token {
	@Getter
	private Date date;
}