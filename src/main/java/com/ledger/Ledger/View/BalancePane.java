package com.ledger.Ledger.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.ledger.Ledger.BalanceSheet.COLUMN;
import com.ledger.Ledger.Database.BalanceSheetDao;
import com.ledger.Ledger.Database.DatabaseConnector;

public class BalancePane extends JPanel implements ActionListener {
	JFrame main; 
	BalanceSheetDao BSD;
	ReadOutPane RP;
	TotalPane TP;
	EntryPane EP;
	String[]columnNames = {"Assets Debit",
			"Assets Credit", "Liability Debit",
			"Liability Credit", 
			"Equity Debit",
			"Equity Credit",
			"Notes",
			"Date"};
	
	String[]totalNames = {"Assets", 
			"Liability",
			"Equity",
			"Notes",
			"Date"};
	

	
	
	public BalancePane(){

		this.setBackground(Color.GRAY);
		this.setBounds(0,0,1000,450);
		COLUMN c = COLUMN.ASSETS;
		DCHookup();
		ArrayList<ArrayList<String>> allEntries = BSD.getEntries();
		//System.out.println(allEntries.get(0));
		//System.out.println(allEntries);
		String[] entry = BSD.getEntryArray(1);
		
		String[][] entries = BSD.getEntriesArray();
		String[][]totals=BSD.getTotals();
		for(int i = 0; i<entry.length;i++) {
		//	System.out.println(entries[8][i]);
		}
		
	//	System.out.println("Entry [0] = " + allEntries.get(0));
		
		try {
		//System.out.println(allEntries);
	
	//	RT = new ReadOutTable(entries, columnNames);
		RP = new ReadOutPane(entries,columnNames);
		TP = new TotalPane(totals,totalNames);
		this.add(RP);
		this.add(TP);
		//ReadOutEntryPane ROP = new ReadOutEntryPane(allEntries.get(0));
		
		//System.out.println(ROP.toString());
		//this.add(ROP);
		
	//	JList<JPanel> list = new JList<JPanel>();
	//	list.add(new ReadOutEntryPane(allEntries.get(0)));
		
		//this.add(list);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("BalancePane");
		}
		EP = new EntryPane(BSD, this);
		
		this.add(EP);

		
	}
	
	private void listEntries() {
		ArrayList<ArrayList<String>> allEntries = new ArrayList<>();
		try {
			
		allEntries = BSD.getEntries();
		} catch (Exception e) {
			System.out.println(e);
		//	System.out.println("listentries");
			
		}
		
		int count =1;
		for(ArrayList a: allEntries) {
			System.out.print(count + " ");
			System.out.println(a);
			count++;
		}

		
	}
	
	//New method to print from database in a list
	
	
	private void DCHookup() {
		try {
		DatabaseConnector dcb = new DatabaseConnector("com.mysql.cj.jdbc.Driver",
				"jdbc:mysql://localhost:3306/Ledger?useSSL=FALSE&serverTimezone=America/Detroit");
		dcb.setUsername("root");
		dcb.setPassword("Perepiteia#");
		this.BSD = new BalanceSheetDao(dcb.getConnection());
		} catch(Exception e) {
			System.out.println(e);
		}

	}
	
	public void setRP() {
		this.remove(RP);
		this.remove(TP);
		this.remove(EP);
		String[][]totals=BSD.getTotals();
		String[][] entries = BSD.getEntriesArray();
		TP = new TotalPane(totals,totalNames);
		RP = new ReadOutPane(entries,columnNames);
		EP = new EntryPane(BSD, this);
		this.add(RP);
		this.add(TP);
		this.add(EP);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	
	public void resetReadAndTotals() {
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		// TODO Auto-generated method stub
		
	}
	
	
	
}
