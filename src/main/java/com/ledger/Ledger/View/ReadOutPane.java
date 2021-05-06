package com.ledger.Ledger.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ReadOutPane extends JPanel {
	ArrayList<ArrayList<String>> entries;
	ArrayList<JPanel> readOuts;
	JTable table;
	String [][] allEntries;
	String [] columnNames;
	
	
	public ReadOutPane(String[][] entries, String[]columnNames) {
		
		this.table=new JTable(entries, columnNames);
		table.setVisible(true);
		table.setPreferredScrollableViewportSize(new Dimension(1000, 200));
		this.setBackground(Color.darkGray);
	//	this.setBounds(0,0,1500,300);
		JScrollPane jsp = new JScrollPane(table);
		
		
		
		this.add(jsp);
	}
	
	/*
	public ReadOutPane(ArrayList<ArrayList<String>> entriesList){
	
	this.entries = entriesList;
	this.setBackground(Color.darkGray);
	this.setBounds(0,0,1500,300);
//	this.setLayout(new FlowLayout());
	
//	listEntries();
	
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
	
*/

	@Override
	public String toString() {
		return "ReadOutPane [entries=" + entries + "]";
	}
	
	

}
