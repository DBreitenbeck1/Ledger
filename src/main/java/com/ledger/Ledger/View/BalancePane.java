package com.ledger.Ledger.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ledger.Ledger.BalanceSheet.COLUMN;
import com.ledger.Ledger.Database.DatabaseConnector;

public class BalancePane extends JPanel implements ActionListener {
	DatabaseConnector DC;
	
	public BalancePane(){
		this.setBackground(Color.GRAY);
		this.setBounds(0,0,1000,450);
		COLUMN c = COLUMN.ASSETS;
		DCHookup();
		EntryPane EP = new EntryPane(DC);
		this.add(EP);
		
	}
	
	
	private void DCHookup() {
		DatabaseConnector dcb = new DatabaseConnector("com.mysql.cj.jdbc.Driver",
				"jdbc:mysql://localhost:3306/Ledger?useSSL=FALSE&serverTimezone=America/Detroit");
		dcb.setUsername("root");
		dcb.setPassword("Perepiteia#");
		
		this.DC= dcb;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
