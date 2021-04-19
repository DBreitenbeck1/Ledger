package com.ledger.Ledger.View;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	public MainFrame(){
	this.setTitle("Ledger Program");
	this.setSize(1000,450);//x, y	
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.getContentPane().setBackground(Color.BLACK);
	
	this.add(new BalancePane());
	}
	
	public void addPane(JPanel pane) {
		this.add(pane);
	}
	
}
