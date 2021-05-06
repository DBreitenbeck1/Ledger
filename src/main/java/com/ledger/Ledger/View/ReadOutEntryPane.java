package com.ledger.Ledger.View;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


//NOT NEEDED??---------
public class ReadOutEntryPane extends JPanel {
	
	ArrayList<String> entries;
	JTable table;
	String[][] ents;
	
	String[]columnNames = {"Assets Debit",
			"Assets Credit", "Liability Debit",
			"Liability Credit", 
			"Equity Debit",
			"Equity Credit",
			"Notes",
			"Date"};
	
	ReadOutEntryPane(ArrayList<String> entries){
		this.entries=entries;
		setEnts();
		
		System.out.println(entries.get(0));
		table = new JTable(ents,columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(1500, 800));
		table.setVisible(true);
		JScrollPane jps = new JScrollPane(table);
		jps.add(table.getTableHeader());
		this.setBackground(Color.darkGray);
		//this.setBounds(0,0,1700,800);
		this.setLayout(new BorderLayout());
		
	//	addLabels();
	//	setTable();
//		this.add(jps);
//		this.add(table.getTableHeader(),BorderLayout.NORTH);
	}
	
	private void setEnts() {
		ents = new String[1][8];
		this.ents[0][0] = entries.get(0);
		this.ents[0][1] = entries.get(1);
		this.ents[0][2] = entries.get(2);
		this.ents[0][3] = entries.get(3);
		this.ents[0][4] = entries.get(4);
		this.ents[0][5] = entries.get(5);
		this.ents[0][6] = entries.get(6);
		this.ents[0][7] = entries.get(7);
	
		
	}
	
	
	private void setTable() {
		this.table = new JTable(8,1);
		int row;
		for(String e: entries) {
			try {
				if(e != null && !e.equals("0.0")){
				//	JLabel label = new JLabel();
					row = entries.indexOf(e);
					System.out.println("Row is " + row);
					System.out.println("e is " + e);
					table.setValueAt(e, row, 1);
				//	label.setText(e);
				//	labels.add(label);
				} else {
					row = entries.indexOf(e);
					System.out.println("e is " + e);
					System.out.println("Row is " + row);
				//	table.setValueAt("-", row, 1);
				//	JLabel label = new JLabel();
				//	label.setText("-");
				//	labels.add(label);
				}
			} catch(NullPointerException n) {
				System.out.println(n);
				System.out.println(e);
				
			}
		}
		
		
	}
	
	
	private ArrayList<JLabel> createLabels() {
		table = new JTable(8,1);
		
		ArrayList<JLabel> labels = new ArrayList<>();
		int row;
	//	table.setValueAt("Test", 1, 1);
		for(String e: entries) {
			try {
			if(e != null && !e.equals("0.0")){
			//	JLabel label = new JLabel();
				row = entries.indexOf(e);
				System.out.println("Row is " + row);
				System.out.println("e is " + e);
				table.setValueAt(e, row, 1);
			//	label.setText(e);
			//	labels.add(label);
			} else {
				row = entries.indexOf(e);
				System.out.println("e is " + e);
				System.out.println("Row is " + row);
			//	table.setValueAt("-", row, 1);
			//	JLabel label = new JLabel();
			//	label.setText("-");
			//	labels.add(label);
			}
		} catch(NullPointerException n) {
			System.out.println(n);
			System.out.println(e);
			
		}
		}
		JLabel linebreak = new JLabel();
		linebreak.setText("\n");
	//	labels.add(linebreak);
		return labels;
	}
	
	
	private void addLabels() {
		ArrayList<JLabel> labels = createLabels();
		
		for(JLabel L: labels) {
			this.add(L);
		}
	
	}

	
	
	
	
	@Override
	public String toString() {
		return "ReadOutEntryPane [entries=" + entries + "]";
	}

	
	
}
