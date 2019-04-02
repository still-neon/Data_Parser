package by.stn.trainingproject.data_parser;

import by.stn.data_parser.parser.DataParserHelper;
import by.stn.data_parser.tokens.DateToken;
import by.stn.data_parser.tokens.TextNumberPairToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class DataParserTestHelper {
	private static final String ERROR_MESSAGE_TEMPLATE = "Parsed %s value doesn't equal expected %s value. Actual: %s, Expected: %s";

	public void checkDateParsing(DateToken dateToken, String dateValue) throws ParseException {
		Date date = new SimpleDateFormat(DataParserHelper.getDATE_FORMAT()).parse(dateValue);
		assertTrue(String.format(ERROR_MESSAGE_TEMPLATE, "date", "date", dateToken.getDate(), date), dateToken.getDate().equals(date));
	}

	public void checkNumberPairParsing(TextNumberPairToken textNumberPairToken, String text, Double number, String currency) {
		assertTrue(String.format(ERROR_MESSAGE_TEMPLATE, "text", "text", textNumberPairToken.getText(), text), textNumberPairToken.getText().equals(text));
		assertTrue(String.format(ERROR_MESSAGE_TEMPLATE, "number", "number", textNumberPairToken.getNumber(), number), textNumberPairToken.getNumber().equals(number));
		assertTrue(String.format(ERROR_MESSAGE_TEMPLATE, "currency", "currency", textNumberPairToken.getCurrency(), currency), textNumberPairToken.getCurrency().equals(currency));
	}
}