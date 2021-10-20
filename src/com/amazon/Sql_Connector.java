package com.amazon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Sql_Connector {
	private final static String url=("jdbc:mysql://localhost:3306/Amazon");
	private final static String username="root";
	private final static String password="1234567890";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url,username,password);
		return connection;
	}
}
