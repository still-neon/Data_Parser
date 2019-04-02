package by.stn.data_parser.data.json_data;

import by.stn.data_parser.data.TextNumberPair;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class Data {
	private Date date;
	private List<TextNumberPair> pairs;
}