package com.ledger.Ledger.View;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;

public class ReadOutTable extends JTable {
	
	//ArrayList<ArrayList<String>> entries;
	String[][] entries;
	ArrayList<JPanel> readOuts;
	String[] columnNames; 

	ReadOutTable(String[][] entries, String[] columnNames){
		this.entries=entries;
		this.columnNames=columnNames;
		this.setBackground(Color.darkGray);
		this.setForeground(Color.white);
		
		
	}
}
