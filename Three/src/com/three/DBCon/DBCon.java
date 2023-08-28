package com.three.DBCon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCon {
	private Connection con;
	public Connection getConnection() {
		return con;
	}
	
	//»ý¼ºÀÚ
	public DBCon() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "three", "three");
	}
}
