package com.ledger.Ledger.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static Boolean working = false;
	
	public DatabaseConnector(String driver, String url) {
		this.driver=driver;
		this.url=url;
	/*	
		String driver =  "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/mysite?useSSL=FALSE&serverTimezone=America/Detroit";
		String username = "root";
		String password = "Perepiteia#";
		*/
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public static void setWorkingTrue() {
		working=true;
	}
	
	public static void setWorkingFalse() {
		working=false;
	}
	
	public static boolean getWorking() {
		return working;
	}

	public static Connection getConnection() {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			setWorkingTrue();
			return conn;
		} catch (Exception e){
			System.out.println(e);
			System.out.println("getConnection");
		
		}
		
		return null;
		
	}
	
}
