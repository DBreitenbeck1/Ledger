package com.ledger.Ledger.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ledger.Ledger.BalanceSheet.COLUMN;
import com.ledger.Ledger.Database.BalanceSheetDao;
import com.ledger.Ledger.Database.DatabaseConnector;

public class BalancePane extends JPanel implements ActionListener {
	BalanceSheetDao BSD;
	
	public BalancePane(){
		this.setBackground(Color.GRAY);
		this.setBounds(0,0,1000,450);
		COLUMN c = COLUMN.ASSETS;
		DCHookup();
		ReadOutPane RP;
	//	ReadOutEntryPane ROP;
		ArrayList<ArrayList<String>> allEntries = BSD.getEntries();
		//System.out.println(allEntries.get(0));
		//System.out.println(allEntries);
		
		
		System.out.println("Entry [0] = " + allEntries.get(0));
		
		try {
		//System.out.println(allEntries);
		String[]columnNames = {"Assets Debit",
				"Assets Credit", "Liability Debit",
				"Liability Credit", 
				"Equity Debit",
				"Equity Credit",
				"Notes",
				"Date"};
		
	//	RP = new ReadOutPane(allEntries);
		ReadOutEntryPane ROP = new ReadOutEntryPane(allEntries.get(0));
		
		//System.out.println(ROP.toString());
		this.add(ROP);
		
	//	JList<JPanel> list = new JList<JPanel>();
	//	list.add(new ReadOutEntryPane(allEntries.get(0)));
		
		//this.add(list);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("BalancePane");
		}
		EntryPane EP = new EntryPane(BSD);
		
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
		DatabaseConnector dcb = new DatabaseConnector("com.mysql.cj.jdbc.Driver",
				"jdbc:mysql://localhost:3306/Ledger?useSSL=FALSE&serverTimezone=America/Detroit");
		dcb.setUsername("root");
		dcb.setPassword("Perepiteia#");
		this.BSD = new BalanceSheetDao(dcb.getConnection());

	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
