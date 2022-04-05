package com.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {

	final static String URL = "jdbc:mysql://localhost:3306/addressbook";
	final static String USER = "root";
	final static String PASSWORD = "Password@11";
	
	public static Connection connection;
	private static Config config = new Config();
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Config() {
		
	}
	
	public static Config getInstance() {
		return config;
	}
}
