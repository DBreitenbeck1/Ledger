package com.ledger.Ledger.BalanceSheet;

import java.util.ArrayList;

public class BalanceSheet {
	ArrayList<Entry> entries;
	Double AssetBalance;
	
	
	public BalanceSheet() {
		entries=new ArrayList<>();
	}
	
	
	public void addEntry(Entry entry) {
		if(entry.getValid()) {
			entries.add(entry);
		}
	}
	
	public void calcBalance() {
		Double balance =0.0;
		for(Entry E: entries) {
			if(E.getDebitColumn().equals(COLUMN.ASSETS)){
				balance+=E.getDebitValue();
			} else if (E.getCreditColumn().equals(COLUMN.ASSETS)) {
				balance-=E.getCreditValue();
			}
		}
		this.AssetBalance=balance;
	}
	
	public Double getBalance() {
		return this.AssetBalance;
	}
	
	public int numberEntries() {
		return entries.size();
	}
	
}
