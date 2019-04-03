package by.stn.data_parser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class DataParserApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataParserApplication.class, args);
		try {
			HelloJavaDb.start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}