package by.stn.data_parser.data;

import lombok.Getter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@XmlRootElement(name = "pair")
@XmlAccessorType(XmlAccessType.FIELD)
public class TextNumberPair {
	private String text;
	private String value;
}