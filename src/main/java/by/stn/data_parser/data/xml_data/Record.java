package by.stn.data_parser.data.xml_data;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {
	private String date;
	private Pairs pairs;
}