package com.ledger.Ledger.View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ledger.Ledger.BalanceSheet.COLUMN;
import com.ledger.Ledger.BalanceSheet.Credit;
import com.ledger.Ledger.BalanceSheet.Debit;
import com.ledger.Ledger.BalanceSheet.Entry;

public class EntryPane extends JPanel implements ActionListener {
	COLUMN colA=COLUMN.ASSETS;
	COLUMN colL=COLUMN.LIABILITY;
	COLUMN colE=COLUMN.EQUITY;
	EntryField assetDebit = new EntryField(colA, false);
	EntryField assetCredit = new EntryField(colA, true);
	JLabel assetLabel = new JLabel("Assets");
	
	
	EntryField liabDebit = new EntryField(colL, false);
	EntryField liabCredit = new EntryField(colL, true);
	JLabel liabLabel = new JLabel("Liabilities");
	
	EntryField equityDebit = new EntryField(colE, false);
	EntryField equityCredit = new EntryField(colE, true);
	JLabel equityLabel = new JLabel("Equity");
	
	ColumnPane assetPane = new ColumnPane(assetDebit, assetCredit);
	ColumnPane liabPane = new ColumnPane(liabDebit, liabCredit);
	ColumnPane equityPane = new ColumnPane(equityDebit, equityCredit);
	EntryField[] entries = {
			assetDebit,
			assetCredit,
			liabDebit,
			liabCredit,
			equityDebit,
			equityCredit};

	JButton submit = new JButton();
	
	EntryPane(){
		this.setBackground(Color.darkGray);
		this.setBounds(0,0,1000,300);
		this.setLayout(new FlowLayout());
	//	addFields();
	//	addAssetLabel();
		this.add(assetPane);
		this.add(liabPane);
		this.add(equityPane);
		addSubmit();
		
	}
	
	private void addFields() {
		this.add(assetDebit);
		this.add(assetCredit);
	
		
		this.add(liabDebit);
		this.add(liabCredit);
		
		this.add(equityDebit);
		this.add(equityCredit);
	
	}
	
	private void addSubmit(){
		
		submit.setFont(new Font("Comic Sans", Font.BOLD,20));
		
		submit.setOpaque(true);
		
		submit.addActionListener(this);
		submit.setText("Submit");
		submit.setFocusable(false);
		submit.setBounds(200,100,100,50);
		this.add(submit);
	}
	
	private boolean checkValid(){
		int count=0;
		for(int i =0; i<entries.length;i++) {
			if(entries[i].getValue()!=0.0) {
				count++;
			}
		}
		if(count>2) {
			return false;
		}else {
			return true;
		}
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Credit credit =null;
		Debit debit =null;
		int count=0;
		if(!checkValid()) {
			System.out.println("Invalid Entry");
		} else {
			for(int i =0; i<entries.length;i++) {
				double value = entries[i].getValue();
				if(value!=0.0) {
					count++; 
					if(count>2) {
						System.out.println("Invalid Entry");
						break;
					}
					if(entries[i].isCredit) {
						credit = new Credit(value, entries[i].getColumn());
						
					} else {
						debit = new Debit(value, entries[i].getColumn());
						
					}
					
				}
				
			}
			
			Entry entry =new Entry(debit,credit);
		
		
		}
		
	}
	
	

	
	
}
