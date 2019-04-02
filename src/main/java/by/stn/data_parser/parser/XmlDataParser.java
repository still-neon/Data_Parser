package by.stn.data_parser.parser;

import by.stn.data_parser.data.TextNumberPair;
import by.stn.data_parser.data.xml_data.Record;
import by.stn.data_parser.data.xml_data.Records;
import by.stn.data_parser.tokens.Token;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class XmlDataParser {
	private DataParserHelper dataParserHelper;
	private SimpleDateFormat format;

	public XmlDataParser() {
		format = new SimpleDateFormat(DataParserHelper.getDATE_FORMAT());
		dataParserHelper = new DataParserHelper();
	}

	public List<Token> getParsedData(String relativeFilePath) {
		File file = new File(dataParserHelper.getFilePath(relativeFilePath));

		Records records = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			records = (Records) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return createTokens(records);
	}

	private List<Token> createTokens(Records records) {
		List<Token> tokens = new ArrayList<>();

		for (Record record : records.getRecords()) {
			try {
				tokens.add(dataParserHelper.createDateToken(format.parse(record.getDate())));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for (TextNumberPair pair : record.getPairs().getTextNumberPairs()) {
				tokens.add(dataParserHelper.createTextNumberPairToken(pair.getText(), pair.getValue()));
			}
		}

		return tokens;
	}
}