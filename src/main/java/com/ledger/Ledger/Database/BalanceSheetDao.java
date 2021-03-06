package com.ledger.Ledger.Database;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ledger.Ledger.BalanceSheet.COLUMN;

public class BalanceSheetDao {
	static private Connection conn;
	static private String[] fields = {
			"Assets_Debit", "Assets_Credit", 
			"Liab_Debit", "Liab_Credit",
			"Equity_Debit", "Equity_Credit", 
			"Notes", "Date"};
	
	static private java.util.Date date = new java.util.Date();
	static private java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	
	
	
	public BalanceSheetDao(Connection connection) {
		BalanceSheetDao.conn=connection;
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
			System.out.println("createTable");
		}
		
	}
	
	public static void addEntry(
			double debit, COLUMN deb, 
			double credit, COLUMN cred
			) throws Exception{	
		String debCol = selectDebitColumn(deb);
		String credCol = selectCreditColumn(cred);
		try {
			PreparedStatement add =
			conn.prepareStatement("INSERT INTO Balancesheet ("
			+ debCol + ", " + credCol 
			+ ", Date) VALUES (" + debit +", " + credit + ", ?)");
			add.setDate(1, sqlDate);
			add.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	//Overloaded for Notes
	public static void addEntry(
			double debit, COLUMN deb, 
			double credit, COLUMN cred,
			String n 
			//String date
			) throws Exception{	
		String debCol = selectDebitColumn(deb);
		String credCol = selectCreditColumn(cred);
		try {
			PreparedStatement add =
			conn.prepareStatement("INSERT INTO Balancesheet ("
			+ debCol + ", " + credCol 
			+ ", Notes, Date) VALUES (" + debit +", " + credit + ", '"
			+ n +"', ?" + 
			//sqlDate +
			")");
			add.setDate(1, sqlDate);
			add.executeUpdate();
		}catch (Exception e) {
			System.out.println(e);
		}

	}
	
	
	public static void addNotes(int id, String n) {
		try {
			PreparedStatement add =
			conn.prepareStatement("UPDATE Balancesheet "
					+ "SET Notes = '"+ n  
					  //WHERE ID="+id
					+ "' WHERE ID="+id);
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
			System.out.println("getAll");
		}
		
		return r;
	}
	
	
	private ArrayList<Integer> getId(){
		ArrayList<Integer> idList = new ArrayList<>();
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement("SELECT ID FROM Balancesheet");
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				idList.add(results.getInt("ID"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			System.out.println("getId");
				
		}
//		System.out.println(idList);		
		
		return idList;
	}
	
	
	public ArrayList<ArrayList<String>> getEntries(){
		ArrayList<Integer> idList = getId();
		ArrayList<ArrayList<String>> entries =  new ArrayList<>();
		for(Integer id: idList) {
			entries.add(getEntry(id));
		}
		
		return entries;
	}
	

	public String[][] getEntriesArray(){
		ArrayList<Integer> idList = getId();
		String[][] entries = new String[idList.size()][8];
		int idIndex;
		for(Integer id: idList) {
			idIndex=idList.indexOf(id);
			
			entries[idIndex]=getEntryArray(id);
		}
		
		return entries;
		
	}
	
	
	
	public static ArrayList<String> getEntry(int id){
		ArrayList<String> entry = new ArrayList<>();
		String ent ="";
		try {
			PreparedStatement statement =
			conn.prepareStatement("SELECT * FROM Balancesheet WHERE ID="+id);
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				for(int i =0; i<fields.length;i++) {
					
					if (i<6) {
						ent = (String.valueOf(results.getDouble(fields[i])));
					} else {
						if(results.getString(fields[i])!=null) {
						ent = (results.getString(fields[i]));
						}else {
							ent = ("null");
						}
					}
					entry.add(ent);
				//System.out.println(ent);
				
				}
				
				
				
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return entry;
	}
	
	public static String[] getEntryArray(int id) {
		String[] entry = new String[8];
		String ent ="";
		try {
			PreparedStatement statement =
			conn.prepareStatement("SELECT * FROM Balancesheet WHERE ID="+id);
			ResultSet results = statement.executeQuery();
			
			while(results.next()) {
				for(int i =0; i<fields.length;i++) {
					
					if (i<6) {
						
						ent = (String.valueOf(results.getDouble(fields[i])));
					//	System.out.println(ent);
						entry[i]=ent;
					} else {
						if(results.getString(fields[i])!=null) {
						ent = (results.getString(fields[i]));
						
						}else {
							ent = ("null");
						}
					}
					entry[i]=ent;
				//System.out.println(ent);
				
				}
				
				
				
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return entry;
		
		
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
	
	
	public static void clearEntry(int id) {
		
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

	private static String getStringResult(String field, int id) {
		String result = "";
		try {
	
				PreparedStatement statement =
						conn.prepareStatement("SELECT "+ field +" FROM Balancesheet "
								+ "WHERE ID="+id);
				ResultSet results = statement.executeQuery();
				results.next();
				result = results.getString(1);
			}catch(Exception e) {
				System.out.println(e);
			}
		
		
		return result;
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
	
	
	public double getTotalValue(COLUMN c) {
		double total = 0.0;
		
		double debsum= getTotalDebit(c);
		double credsum = getTotalCredit(c);
		
		if(c.equals(COLUMN.ASSETS)) {
			total=debsum-credsum;
		} else {
			total = credsum-debsum;
		}
		
		
		return total;
	}
	
	
	public double getTotalDebit(COLUMN c) {
		String col = selectDebitColumn(c);
		
		double sum = 0.0;
		try {
		PreparedStatement statement =
				conn.prepareStatement("SELECT SUM("+ col +") FROM Balancesheet;");
		ResultSet results = statement.executeQuery();
		if (results.next()) {
		
		sum = results.getDouble(1);
		
		}
		} catch (Exception e) {
			System.out.print(e);
		}
		
		
		return sum;
	}
	
	
	public double getTotalCredit(COLUMN c) {
		String col = selectCreditColumn(c);
		double sum = 0.0;
		try {
		PreparedStatement statement =
				conn.prepareStatement("SELECT SUM("+ col +") FROM Balancesheet;");
		ResultSet results = statement.executeQuery();
		if (results.next()) {
			sum = results.getDouble(1);
		}
		} catch (Exception e) {
			System.out.print(e);
		}	
		return sum;
	}
	
	
	public String[][] getTotals(){
		String[][] totals = new String[1][5];
		COLUMN col;
		int count = 0;
		
		for(int i =1; i<4;i++ ) {
			double total = 0.0;
			col=COLUMN.getColumn(i);
			total=round(getTotalValue(col));
			totals[0][count] = String.valueOf(total);
			count++;
			
		}
		
		/*
		
		
		for(int i = 0; i<5;i+=2) {
			col=fields[i];
		//	System.out.println(col);
			double sum = 0.0;
			try {
			PreparedStatement statement =
					conn.prepareStatement("SELECT SUM("+ col +") FROM Balancesheet;");
			ResultSet results = statement.executeQuery();
			if (results.next()) {
			
			sum = results.getDouble(1);
			//System.out.println(sum);
			totals[0][i]=String.valueOf(sum);
			
			}
			} catch (Exception e) {
				System.out.print(e);
			}
			
		}
		
		for(int i = 1; i<=5;i+=2) {
			col=fields[i];
			double sum = 0.0;
			try {
			PreparedStatement statement =
					conn.prepareStatement("SELECT SUM("+ col +") FROM Balancesheet;");
			ResultSet results = statement.executeQuery();
			if (results.next()) {
			
			sum = results.getDouble(1);
			totals[0][i]=String.valueOf(sum);
			
			}
			} catch (Exception e) {
				System.out.print(e);
			}
			
		}
		
		*/
		totals[0][3]="";
		totals[0][4]="";
		
		
		for(int i = 0; i<5;i++) {
		//	System.out.println(totals[0][i]);
		}
		
		
		return totals;
	}
	

	public ArrayList<Double> getDebitColumnAmounts(COLUMN c){
		ArrayList<Double> amountList = new ArrayList<>();
		String col = selectDebitColumn(c);
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement("SELECT "+ col +" FROM Balancesheet");
			ResultSet results = statement.executeQuery();
			System.out.println(results.getFetchSize());
			while(results.next()) {
				System.out.println(results.getDouble(1));
				amountList.add(results.getDouble(1));
			}
			/*
			statement = conn.prepareStatement("SELECT ID FROM Balancesheet");
			ResultSet results = statement.executeQuery();
			while(results.next()) {
				idList.add(results.getInt("ID"));
			}
			*/
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			System.out.println("getColAmount");
				
		}
		//System.out.println(amountList);		
		
		return amountList;
	}
	
	
	private double decimalLimit(double d) {
		
		double sum = round(d);
		
		
		return sum;
	}
	
	
	public boolean shouldRoundUp(double d) {
		double sum = d*1000;
	
		int a = (int)(sum);
		int lastDec = a%10;
		return (lastDec>=5);
	}
	
	
	public double round(double d) {
		
		double sum = d*100;
		int a = (int)(sum);
		if(shouldRoundUp(d)) {
			a+=1;
		}
		sum=(double)(a);
		sum=sum/100;
		
		return sum;
	}
	
}
