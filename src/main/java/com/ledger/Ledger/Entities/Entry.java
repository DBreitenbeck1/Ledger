package com.ledger.Ledger.Entities;

public class Entry {
	
	Debit debit;
	Credit credit;
	Double balance=0.0;
	Integer account; 
	Boolean isValid;
	
	public Entry(){
		
	}
	
	public Entry(Debit debit,Credit credit){
			this.isValid=isValidEntry(debit, credit);
			this.credit=credit;
			this.debit=debit;	
			this.setBalance();
	}
		
	public boolean isValidEntry(Debit debit,Credit credit) {
		if(credit.getAmount()==debit.getAmount()) {
			return true;
		}else {
			return false;
		}
		
	}
	
	private void setBalance() {
		if(debit.getColumn().equals(COLUMN.ASSETS)) {
			balance+=debit.getAmount();
		} else {
			balance-=debit.getAmount();
		}
		if(credit.getColumn().equals(COLUMN.ASSETS)) {
			balance-=credit.getAmount();
		} else {
			balance+=credit.getAmount();
		}
	}
	
	public Double getCreditValue() {
		if(credit.getColumn().equals(COLUMN.ASSETS)) {
			return -credit.getAmount();
		} else {
			return credit.getAmount();
		}
	}
	
	public Double getDebitValue() {
		if(debit.getColumn().equals(COLUMN.ASSETS)) {
			return debit.getAmount();
		} else {
			return -debit.getAmount();
		}
	}
	
	
	
	public Double getBalance() {
		return this.balance;
	}
	
	
	public boolean getValid() {
		return this.isValid;
	}
	
	
	public double getCredit() {
		return this.credit.getAmount();
	}
	
	public double getDebit() {
		return this.debit.getAmount();
	}
	
	
}
