package com.restaurant.foodItems;

import java.sql.Connection;

public class DataCollector {

	public static void main(String... args) {
		Connection con = JDBCConnector.getConnection();
		System.out.println(con);
		JsoupParser.parse3(con);
		
	}

}
