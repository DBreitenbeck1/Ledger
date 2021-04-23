package com.ledger.Ledger.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
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
		ArrayList<ArrayList<String>> allEntries = BSD.getEntries();
		//System.out.println(allEntries);
		try {
		//System.out.println(allEntries);
		RP = new ReadOutPane(allEntries);
		//System.out.println(RP.toString());
		this.add(RP);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("BalancePane");
		}
		EntryPane EP = new EntryPane(BSD);
		
		this.add(EP);
	//	listEntries();
		
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
