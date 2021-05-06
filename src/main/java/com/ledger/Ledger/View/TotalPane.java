package com.ledger.Ledger.View;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class TotalPane extends JPanel {
	JTable table;
	String [][] allEntries;
	String [] columnNames;
	
	
	public TotalPane(String[][] entries, String[]columnNames) {
		
		this.table=new JTable(entries, columnNames);
		table.setVisible(true);
		table.setPreferredScrollableViewportSize(new Dimension(1015, 20));
		this.setBackground(Color.darkGray);

		JScrollPane jsp = new JScrollPane(table);
				
		this.add(jsp);
	}
}
