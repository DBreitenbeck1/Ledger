package com.ledger.Ledger.View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ReadOutPane extends JPanel {
	ArrayList<ArrayList<String>> entries;
	
	public ReadOutPane(ArrayList<ArrayList<String>> entriesList){
	this.entries = entriesList;
	this.setBackground(Color.darkGray);
	this.setBounds(0,0,1500,300);
	this.setLayout(new FlowLayout());
	listEntries();
	}
	
	private void listEntries() {
		for(int i = 0; i<entries.size();i++) {
			addLabels(i);	
		}
	}
	
	private ArrayList<JLabel> createLabels(int ent) {
		ArrayList<String> entry= entries.get(ent);
		System.out.println(entry);
		ArrayList<JLabel> labels = new ArrayList<>();
	
		
		
		for(String e: entry) {
			try {
			if(e != null && !e.equals("0.0")){
			JLabel label = new JLabel();
			label.setText(e);
			labels.add(label);
			} else {
				JLabel label = new JLabel();
				label.setText("-");
				labels.add(label);
			}
		} catch(NullPointerException n) {
			System.out.println(n);
			System.out.println(e);
			
		}
		}
		JLabel linebreak = new JLabel();
		linebreak.setText("\n");
		labels.add(linebreak);
		return labels;
	}
	
	private void addLabels(int entry) {
		ArrayList<JLabel> labels = createLabels(entry);
		
		for(JLabel L: labels) {
			this.add(L);
		}
	
	}



	@Override
	public String toString() {
		return "ReadOutPane [entries=" + entries + "]";
	}
	
	

}
