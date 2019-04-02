package by.stn.data_parser.data.xml_data;

import lombok.Getter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Records {
	@XmlElement(name = "record")
	@Getter
	private List<Record> records;
}