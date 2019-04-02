package by.stn.data_parser.data.xml_data;

import by.stn.data_parser.data.TextNumberPair;
import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@XmlRootElement
public class Pairs {
	@XmlElement(name = "pair")
	private List<TextNumberPair> textNumberPairs;
}