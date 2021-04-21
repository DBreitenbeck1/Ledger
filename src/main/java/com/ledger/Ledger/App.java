package com.ledger.Ledger;

import java.util.ArrayList;

import com.ledger.Ledger.BalanceSheet.COLUMN;
import com.ledger.Ledger.Database.BalanceSheetDao;
import com.ledger.Ledger.Database.DatabaseConnector;
import com.ledger.Ledger.View.MainFrame;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    //	new MainFrame();
    	
    FileManager FM = new FileManager("Test");
    FM.writeToFile("This is a test");
    FM.close();
    	
    	
    	
    }
}
