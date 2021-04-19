package com.ledger.Ledger.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ledger.Ledger.BalanceSheet.COLUMN;

public class EntryField extends JTextField implements ActionListener {
	boolean isCredit;
	COLUMN col;
	double value;
	
	
	
	public EntryField(COLUMN col, boolean credit) {
		this.setPreferredSize(new Dimension(70, 20));
		this.setFont(new Font("Consolas", Font.PLAIN, 15));
		this.setForeground(new Color(255,255,255));
		this.setBackground(Color.black);
		this.setCaretColor(Color.WHITE);
		this.addActionListener(this);
		this.col=col;
		this.isCredit=credit;
	}
	
	
	private boolean validateData() {
		try {
			this.value= Double.parseDouble(this.getText());
			return true;
		}catch(NumberFormatException e){
			System.out.println("Invalid entry");
			return false;
		}
		
	}
	
	public double getValue() {
		
		return value;
	}
	
	public COLUMN getColumn() {
		return col;
	}
	
	public boolean isCredit() {
		return isCredit;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(this.getText());
		System.out.println(validateData());
		
	}
	
	

}
