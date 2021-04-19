package com.ledger.Ledger.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ledger.Ledger.BalanceSheet.COLUMN;

public class ColumnPane extends JPanel {
	COLUMN col;
	JLabel label;
	EntryField debitField;
	EntryField creditField;
	
	ColumnPane(EntryField df, EntryField cf){
		this.setBackground(Color.darkGray);
		this.setBounds(0,0,1000,300);
		this.setLayout(new BorderLayout());
		this.debitField=df;
		this.creditField=cf;
		this.col=df.getColumn();	
		addFields();
		setLabel();
	}
	
	private void addFields() {
		this.add(debitField, BorderLayout.WEST);
		this.add(creditField, BorderLayout.EAST);
	}
	
	private void setLabel() {
		this.label=new JLabel();
		switch(col) {
		case ASSETS: 
			label.setText("Assets");
			break;
		case LIABILITY: 
			label.setText("Liability");
			break;
		case EQUITY: 
			label.setText("Equity");
			break;
		}
		label.setHorizontalAlignment(JLabel.CENTER);
		this.add(label,BorderLayout.NORTH);
	}
	
	

}
