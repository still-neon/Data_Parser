package by.stn.data_parser.parser;

import by.stn.data_parser.data.TextNumberPair;
import by.stn.data_parser.data.json_data.Data;
import by.stn.data_parser.tokens.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonDataParser {
	private DataParserHelper dataParserHelper;

	public JsonDataParser() {
		dataParserHelper = new DataParserHelper();
	}

	public List<Token> getParsedData(String relativeFilePath) {
		Data[] dataArray = null;

		try (BufferedReader br = new BufferedReader(new FileReader(dataParserHelper.getFilePath(relativeFilePath)))) {
			Gson gson = new GsonBuilder().setDateFormat(DataParserHelper.getDATE_FORMAT()).create();
			dataArray = gson.fromJson(br, Data[].class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return createTokens(dataArray);
	}

	private List<Token> createTokens(Data[] dataArray) {
		List<Token> tokens = new ArrayList<>();

		for (Data data : dataArray) {
			tokens.add(dataParserHelper.createDateToken(data.getDate()));

			for (TextNumberPair pair : data.getPairs()) {
				tokens.add(dataParserHelper.createTextNumberPairToken(pair.getText(), pair.getValue()));
			}
		}

		return tokens;
	}
}