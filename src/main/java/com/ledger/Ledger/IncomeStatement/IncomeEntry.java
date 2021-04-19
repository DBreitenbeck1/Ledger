package com.ledger.Ledger.IncomeStatement;

public class IncomeEntry {
	Double Revenue;
	Double Expenses;
	Double NetIncome;
	
	public IncomeEntry(double revenue, double expenses) {
		this.Revenue=revenue;
		this.Expenses=expenses;
	}
	
	public void calcNetIncome() {
		this.NetIncome=Revenue-Expenses;
	}
	
	public Double getNetIncome() {
		return NetIncome;
	}

}
