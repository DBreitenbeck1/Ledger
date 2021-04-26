package com.ledger.Ledger.View;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTable;

public class ReadOutTable extends JTable {
	
	//ArrayList<ArrayList<String>> entries;

	ReadOutTable(String[][] entries, String[] columnNames){
		this.setBackground(Color.darkGray);
		this.setForeground(Color.white);
		
	}
}
