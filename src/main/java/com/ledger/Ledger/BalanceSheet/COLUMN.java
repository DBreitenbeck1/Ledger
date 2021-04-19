package com.ledger.Ledger.BalanceSheet;

public enum COLUMN {
	ASSETS,
	LIABILITY,
	EQUITY;


	public static COLUMN getColumn(int c) {
		COLUMN col = ASSETS;
		switch(c) {
		case 1:
			return ASSETS;
		case 2:
			return LIABILITY;
		case 3:
			return EQUITY;
		default:
			return ASSETS;
		}	
	}

}
