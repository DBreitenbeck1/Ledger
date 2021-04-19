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

public class BalancePane extends JPanel implements ActionListener {
	
	
	public BalancePane(){
		this.setBackground(Color.GRAY);
		this.setBounds(0,0,1000,450);
		COLUMN c = COLUMN.ASSETS;
		EntryPane EP = new EntryPane();
		this.add(EP);

	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
