package by.stn.data_parser.connection;

import java.sql.*;

public class ConnectionFactory {
	public static final String URL = "jdbc:derby:c:\\Users\\eugenkrasotkin\\Desktop\\demo;create=true";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL);
	}
}