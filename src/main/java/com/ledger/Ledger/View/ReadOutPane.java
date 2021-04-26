package com.ledger.Ledger.View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ReadOutPane extends JPanel {
	ArrayList<ArrayList<String>> entries;
	ArrayList<JPanel> readOuts;
	JList<JPanel> list;
	
	
	
	
	public ReadOutPane(ArrayList<ArrayList<String>> entriesList){
	
	this.entries = entriesList;
	this.setBackground(Color.darkGray);
	this.setBounds(0,0,1500,300);
//	this.setLayout(new FlowLayout());
	
	listEntries();
	this.add(list);
	}
	
	private void listEntries() {
	
		int rows = 0;
		ReadOutEntryPane ROP;
		for(int i = 0; i<entries.size();i++) {
			ROP = new ReadOutEntryPane(entries.get(i));			
	//		list.add(ROP);			
		}
		//list.setVisibleRowCount(rows);
	}
	


	@Override
	public String toString() {
		return "ReadOutPane [entries=" + entries + "]";
	}
	
	

}
