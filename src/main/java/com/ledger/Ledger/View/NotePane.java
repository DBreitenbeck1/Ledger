package com.ledger.Ledger.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NotePane extends JPanel {
	JTextField noteField;
	JLabel label;
	
	NotePane(){
		this.setBackground(Color.darkGray);
		this.setBounds(0,0,500,300);
		this.setLayout(new BorderLayout());
		this.label = createLabel();

		this.noteField=createNoteField();
		this.add(label, BorderLayout.NORTH);
		this.add(noteField,BorderLayout.CENTER);
	}
	
	private JTextField createNoteField() {
		JTextField nf = new JTextField();
		nf.setPreferredSize(new Dimension(100, 20));
		nf.setFont(new Font("Consolas", Font.PLAIN, 15));
		nf.setForeground(new Color(255,255,255));
		nf.setBackground(Color.black);
		nf.setCaretColor(Color.WHITE);
		
		return nf;
	}
	
	private JLabel createLabel() {
		JLabel l = new JLabel();
		l.setText("Notes");
		l.setHorizontalAlignment(JLabel.CENTER);
		return l;
	}
	

	public String getValue() {
		return noteField.getText();
	}

	
}
