package by.stn.trainingproject.data_parser;

import by.stn.data_parser.Launcher;
import by.stn.data_parser.parser.JsonDataParser;
import by.stn.data_parser.tokens.DateToken;
import by.stn.data_parser.tokens.TextNumberPairToken;
import by.stn.data_parser.tokens.Token;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

public class JsonDataParserTest {
	private static JsonDataParser dataParser;
	private static DataParserTestHelper dataParserTestHelper;

	@BeforeClass
	public static void crateObject() {
		dataParser = new JsonDataParser();
		dataParserTestHelper = new DataParserTestHelper();
	}

	@Test
	public void checkJsonDataParser() {
		List<Token> tokens = dataParser.getParsedData(Launcher.getJSON_RELATIVE_FILE_PATH());

		try {
			dataParserTestHelper.checkDateParsing((DateToken) tokens.get(0), "August 2018");
			dataParserTestHelper.checkDateParsing((DateToken) tokens.get(4), "September 2018");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		dataParserTestHelper.checkNumberPairParsing((TextNumberPairToken) tokens.get(1), "любая, хрень", 275d, "");
		dataParserTestHelper.checkNumberPairParsing((TextNumberPairToken) tokens.get(2), "очень хорошо", 646.56, "");
		dataParserTestHelper.checkNumberPairParsing((TextNumberPairToken) tokens.get(3), "жрачка", 37.33, "");
		dataParserTestHelper.checkNumberPairParsing((TextNumberPairToken) tokens.get(5), "любая хрень 123", 275d, "$");
		dataParserTestHelper.checkNumberPairParsing((TextNumberPairToken) tokens.get(6), "ни о чём,", 646.56, "$");
		dataParserTestHelper.checkNumberPairParsing((TextNumberPairToken) tokens.get(7), "жрачка2", 37.33, "$");
	}
}