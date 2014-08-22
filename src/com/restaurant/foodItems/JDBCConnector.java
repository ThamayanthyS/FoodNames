package com.restaurant.foodItems;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnector {
	private static Connection con = null;

	private static Connection doConnection() {

		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "foodnames";
		String userName = "root";
		String password = "";
		// Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url + dbName, userName, password);

		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		return con;
	}

	public static Connection getConnection() {
		if (con == null) {
			con = doConnection();
		}
		return con;
	}

}
