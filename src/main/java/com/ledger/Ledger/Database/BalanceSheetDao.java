package com.ledger.Ledger.Database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ledger.Ledger.BalanceSheet.COLUMN;

public class BalanceSheetDao {
	static private Connection conn;
	static private String[] fields = {
			"Assets_Debit", "Assets_Credit", 
			"Liab_Debit", "Liab_Credit",
			"Equity_Debit", "Equity_Credit", 
			"Notes", "Date"};
	
	
	public BalanceSheetDao(Connection connection) {
		this.conn=connection;
	}
	
	public static void createTable() throws Exception{
		try {
			PreparedStatement create =
			conn.prepareStatement("CREATE TABLE IF NOT EXISTS Balancesheet("
					+ "ID int NOT NULL AUTO_INCREMENT, "
					+ fields[0] + " double, " + fields[1] + " double, "
					+ fields[2] + " double, " + fields[3] + " double, "
					+ fields[4] + " double, " + fields[5] + " double, "
					+ fields[6] + " varchar(255), "
					+ fields[7] + " date, PRIMARY KEY(id))");
			create.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static void addEntry(
			double debit, COLUMN deb, 
			double credit, COLUMN cred) throws Exception{	
		String debCol = selectDebitColumn(deb);
		String credCol = selectCreditColumn(cred);
		try {
			PreparedStatement add =
			conn.prepareStatement("INSERT INTO Balancesheet ("
			+ debCol + ", " + credCol + ") VALUES (" + debit +", " + credit + ")");
			add.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static double getDebit(int id)throws Exception{
		double debit = 0.0;
		double selection =-1;
		try {
		for (int i = 0; i<5;i+=2) {
			selection = getAmountResult(fields[i], id);
			if(selection != 0.0) {
				debit = selection;
			}
		}
		}catch(Exception e) {
			System.out.println(e);
		}
		return debit; 
	}
	
	public static double getCredit(int id)throws Exception{
		double credit = 0.0;
		double selection =-1;
		try {
		for (int i = 1; i<6;i+=2) {
			selection = getAmountResult(fields[i], id);
			if(selection != 0.0) {
				credit = selection;
			}
		}
		}catch(Exception e) {
			System.out.println(e);
		}
		return credit; 
	}
	
	
	public static ArrayList<String> getAll()throws Exception{
		ArrayList<String> r = new ArrayList<>();
		try {
			PreparedStatement statement =
			conn.prepareStatement("SELECT * FROM Balancesheet");
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				for(int i =0; i<fields.length;i++) {
			
				String ent = (String.valueOf(fields[i])+ ", " 
				+ String.valueOf(results.getDouble(fields[i]))); 
				System.out.println(ent);
				r.add(ent);
				}
				
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return r;
	}
	

	public static void editEntry(int id,
			double debit, COLUMN deb, 
			double credit, COLUMN cred) throws Exception{	
		String debCol = selectDebitColumn(deb);
		String credCol = selectCreditColumn(cred);
		try {
			PreparedStatement update =
			conn.prepareStatement("UPDATE Balancesheet SET "
			+ debCol + " = " + debit + ", " 
			+ credCol + " = " + credit + " WHERE ID=" + id);
			clearEntry(id);
			update.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	private static void clearEntry(int id) {
		
		for (int i = 0; i<6;i++) {
				try {
					PreparedStatement update =
					conn.prepareStatement("UPDATE Balancesheet SET "
					+ fields[i] + " = NULL" + " WHERE ID=" + id);
						update.executeUpdate();
					}catch (Exception e) {
						System.out.println(e);
					}
			}
	}
	
	private static String selectDebitColumn(COLUMN d) {
		switch(d) {
		case ASSETS: 
			return fields[0];
		case LIABILITY: 
			return fields[2];
		case EQUITY: 
			return fields[4];
		default:
			return fields[0];
		}
	}
	
	private static String selectCreditColumn(COLUMN c) {
		switch(c) {
		case ASSETS: 
			return fields[1];
		case LIABILITY: 
			return fields[3];
		case EQUITY: 
			return fields[5];
		default:
			return fields[1];
		}
	}

	
	private static double getAmountResult(String field, int id) {
		double result = -1;
		double selection;
		try {
	
				PreparedStatement statement =
						conn.prepareStatement("SELECT "+ field +" FROM Balancesheet "
								+ "WHERE ID="+id);
				ResultSet results = statement.executeQuery();
				results.next();
				result = results.getDouble(1);
			}catch(Exception e) {
				System.out.println(e);
			}
		
		
		return result;
	}
	

}