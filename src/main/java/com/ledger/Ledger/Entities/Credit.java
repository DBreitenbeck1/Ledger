package com.ledger.Ledger.Entities;

public class Credit {
	Double amount;
	COLUMN column;
	
	public Credit(double amount, COLUMN col) {
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
