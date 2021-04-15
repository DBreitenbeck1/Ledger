package com.ledger.Ledger.Entities;

public class Debit {
	Double amount;
	COLUMN column;
	
	
	public Debit(double amount, COLUMN col) {
		this.amount=amount;
		this.column=col;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public COLUMN getColumn() {
		return column;
	}

}
