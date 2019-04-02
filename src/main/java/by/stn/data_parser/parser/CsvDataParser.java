package by.stn.data_parser.parser;

import by.stn.data_parser.tokens.Token;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvDataParser {
	private static final String PAIR_SEPARATOR = ",";
	private static final int PAIR_PARTS_NUMBER = 2;
	private static final String QUOTE = "\"";
	private static final String EMPTY = "";
	private SimpleDateFormat format;
	private DataParserHelper dataParserHelper;

	public CsvDataParser() {
		format = new SimpleDateFormat(DataParserHelper.getDATE_FORMAT());
		dataParserHelper = new DataParserHelper();
	}

	public List<Token> getParsedData(String relativeFilePath) {
		List<String> records = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(dataParserHelper.getFilePath(relativeFilePath)))) {
			String line;
			while ((line = br.readLine()) != null) {
				records.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return createTokens(records);
	}

	private List<Token> createTokens(List<String> lines) {
		List<Token> tokens = new ArrayList<>();

		for (String line : lines) {
			line = deleteQuotes(line);
			if (!line.contains(PAIR_SEPARATOR)) {
				try {
					tokens.add(dataParserHelper.createDateToken(format.parse(line)));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else {
				String[] pair = line.split(PAIR_SEPARATOR, PAIR_PARTS_NUMBER);
				tokens.add(dataParserHelper.createTextNumberPairToken(pair[0], pair[1]));
			}
		}

		return tokens;
	}

	private String deleteQuotes(String line) {
		return line.replace(QUOTE, EMPTY);
	}
}