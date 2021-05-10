package com.ledger.Ledger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Test;

import com.ledger.Ledger.BalanceSheet.BalanceSheet;
import com.ledger.Ledger.BalanceSheet.COLUMN;
import com.ledger.Ledger.BalanceSheet.Credit;
import com.ledger.Ledger.BalanceSheet.Debit;
import com.ledger.Ledger.BalanceSheet.Entry;
import com.ledger.Ledger.Database.BalanceSheetDao;
import com.ledger.Ledger.Database.DatabaseConnector;
import com.ledger.Ledger.IncomeStatement.IncomeEntry;

/**
 * Unit test for simple App.
 */
public class AppTest {
	DatabaseConnector DB = new DatabaseConnector("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/Ledger?useSSL=FALSE&serverTimezone=America/Detroit");
	Connection con; 
	BalanceSheetDao BSD; 
	
	public void createDatabaseConnection() {
		DB.setUsername("root");
		DB.setPassword("Perepiteia#");
		this.con=DB.getConnection();
		this.BSD=new BalanceSheetDao(con);
	}


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void connectionWorking() {
    	DatabaseConnector DB = new DatabaseConnector(
    			"com.mysql.cj.jdbc.Driver", 
    			"jdbc:mysql://localhost:3306/mysite?useSSL=FALSE&serverTimezone=America/Detroit"
    			);
    	
    	DB.setWorkingTrue();
    	assertTrue(DB.getWorking());
    }
    
    @Test
    public void getConnection() {
    	DatabaseConnector DB = new DatabaseConnector(
    			"com.mysql.cj.jdbc.Driver", 
    			"jdbc:mysql://localhost:3306/ledger?useSSL=FALSE&serverTimezone=America/Detroit"
    			);
    	DB.setPassword("Perepiteia#");
    	DB.setUsername("root");
    	DatabaseConnector.getConnection();
    	
    	assertTrue(DatabaseConnector.getWorking());
    }
    
   @Test
   public void testCreditAmount() {
	   COLUMN column = COLUMN.getColumn(2);
	   
	   Credit cr = new Credit(15.4, column);
	   
	   assertEquals((Double)15.4, cr.getAmount());
   }
    
   @Test
   public void testCreditColumn() {
	   COLUMN column = COLUMN.getColumn(2);
	   
	   Credit cr = new Credit(15.4, column);
	   
	   assertEquals(COLUMN.LIABILITY, cr.getColumn());
   }
   
   @Test
   public void testDebitAmount() {
	   COLUMN column = COLUMN.getColumn(2);
	   
	   Debit dr = new Debit(15.4, column);
	   
	   assertEquals((Double)15.4, dr.getAmount());
   }
    
   @Test
   public void testDebitColumn() {
	   COLUMN column = COLUMN.getColumn(1);   
	   Debit dr = new Debit(15.4, column);
	   assertEquals(COLUMN.ASSETS, dr.getColumn());
   }
    
   @Test
   public void entryTestisValid() {
	   COLUMN cold = COLUMN.getColumn(1);
	   COLUMN colc = COLUMN.getColumn(1);
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.4, colc);
	   Entry entry = new Entry(dr,cr);
	   assertTrue(entry.getValid());
   }
   
   @Test
   public void entryTestNotValid() {
	   COLUMN cold = COLUMN.getColumn(1);
	   COLUMN colc = COLUMN.getColumn(1);
	   
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.2, colc);
	   Entry entry = new Entry(dr,cr);
	   
	   assertTrue(!entry.getValid());
   }
    
   
   @Test
   public void entryBalanceFirstTest() {
	   COLUMN cold = COLUMN.getColumn(1);
	   COLUMN colc = COLUMN.getColumn(2);
	   
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.4, colc);
	   Entry entry = new Entry(dr,cr);
	 
	   assertEquals((Double)30.8, entry.getBalance());
   }
   
   @Test
   public void entryBalanceSecondTest() {
	   COLUMN cold = COLUMN.getColumn(1);
	   COLUMN colc = COLUMN.getColumn(1);
	   
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.4, colc);
	   Entry entry = new Entry(dr,cr);
	 
	   assertEquals((Double)0.0, entry.getBalance());
   }
   
   @Test public void testCreditValueAsset() {
	   COLUMN cold = COLUMN.getColumn(1);
	   COLUMN colc = COLUMN.getColumn(1);
	   Double balance = 100.0;
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.4, colc);
	   Entry entry = new Entry(dr,cr);
	   balance +=entry.getCreditValue();
	   assertEquals((Double) 84.6, balance);
   }
   
   @Test public void testDebitValueAsset() {
	   COLUMN cold = COLUMN.getColumn(1);
	   COLUMN colc = COLUMN.getColumn(1);
	   Double balance = 100.0;
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.4, colc);
	   Entry entry = new Entry(dr,cr);
	   balance +=entry.getDebitValue();
	   assertEquals((Double) 115.4, balance);
   }
   
   @Test public void testCreditValueLiability() {
	   COLUMN cold = COLUMN.getColumn(1);
	   COLUMN colc = COLUMN.getColumn(2);
	   Double balance = 100.0;
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.4, colc);
	   Entry entry = new Entry(dr,cr);
	   balance +=entry.getCreditValue();
	   assertEquals((Double) 115.4, balance);
   }
   
   
   @Test public void testDebitValueLiability() {
	   COLUMN cold = COLUMN.getColumn(2);
	   COLUMN colc = COLUMN.getColumn(1);
	   Double balance = 100.0;
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.4, colc);
	   Entry entry = new Entry(dr,cr);
	   balance +=entry.getDebitValue();
	   assertEquals((Double) 84.6, balance);
   }
   
   @Test public void testCreditValueEquity() {
	   COLUMN cold = COLUMN.getColumn(1);
	   COLUMN colc = COLUMN.getColumn(3);
	   Double balance = 100.0;
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.4, colc);
	   Entry entry = new Entry(dr,cr);
	   balance +=entry.getCreditValue();
	   assertEquals((Double) 115.4, balance);
   }
   
   
   @Test public void testDebitValueEquity() {
	   COLUMN cold = COLUMN.getColumn(3);
	   COLUMN colc = COLUMN.getColumn(1);
	   Double balance = 100.0;
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.4, colc);
	   Entry entry = new Entry(dr,cr);
	   balance +=entry.getDebitValue();
	   assertEquals((Double) 84.6, balance);
   }
   
   @Test
   public void BalanceSheetFirstTest() {
	   COLUMN cold = COLUMN.getColumn(1);
	   COLUMN colc = COLUMN.getColumn(3);
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.4, colc);
	   Entry entry1 = new Entry(dr,cr);
	  
	    BalanceSheet bs = new BalanceSheet();
	    bs.addEntry(entry1);
	    
	    assertEquals(1,bs.numberEntries());
   }
   
   @Test
   public void BalanceSheetSecondTest() {
	   COLUMN cold = COLUMN.getColumn(1);
	   COLUMN colc = COLUMN.getColumn(3);
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.4, colc);
	   Entry entry1 = new Entry(dr,cr);
	   cold = COLUMN.getColumn(1);
	   colc = COLUMN.getColumn(2);
	   dr = new Debit(25.75, cold);
	    cr = new Credit(22.75, colc);
	    Entry entry2 = new Entry(dr,cr);
	   
	   
	    BalanceSheet bs = new BalanceSheet();
	    bs.addEntry(entry1);
	    bs.addEntry(entry2);
	    
	    assertEquals(1,bs.numberEntries());
   }
   
   

   @Test
   public void BalanceSheetFirstBalanceTest() {
	   COLUMN cold = COLUMN.getColumn(1);
	   COLUMN colc = COLUMN.getColumn(3);
	   Debit dr = new Debit(15.4, cold);
	   Credit cr = new Credit(15.4, colc);
	   Entry entry1 = new Entry(dr,cr);
	   cold = COLUMN.getColumn(1);
	   colc = COLUMN.getColumn(2);
	   dr = new Debit(22.75, cold);
	    cr = new Credit(22.75, colc);
	    Entry entry2 = new Entry(dr,cr);
	    BalanceSheet bs = new BalanceSheet();
	    bs.addEntry(entry1);
	    bs.addEntry(entry2);
	    bs.calcBalance();
	    assertEquals((Double)38.15,bs.getBalance());
	    
   }
   
   
   @Test
   public void IncomeStatementFirstTest() {
	   IncomeEntry entry =new IncomeEntry(100.00, 20.00);
	   entry.calcNetIncome();
	   assertEquals((Double)80.00,entry.getNetIncome());
   }
   
   
   
   
   @Test
   public void ColumnTotalFirstTest() {
	   createDatabaseConnection();
	   Double equityDebit=0.0;
	   
	   equityDebit=BSD.getTotalDebit(COLUMN.EQUITY);
	   
	   assertEquals((Double)45.50, equityDebit);
   }
   
   /*
   @Test public void ColumnTotalSecondTest() {
	   createDatabaseConnection();
	   ArrayList<Double> amountList = new ArrayList<>();
	   amountList=BSD.getDebitColumnAmounts(COLUMN.ASSETS);
	  // System.out.println(amountList);
	   assertEquals(17, amountList.size());
	   
   }

   @Test
   public void ColumnTotalThirdTest() {
	   createDatabaseConnection();
	   Double equityDebit=0.0;
	   
	   equityDebit=BSD.getTotalCredit(COLUMN.EQUITY);
	   
	   assertEquals((Double)127.45, equityDebit);
   }
   
   @Test
   public void TotalsFirstTest() {
	   createDatabaseConnection();
	   String[][]totals=BSD.getTotals();
	   
	   assertEquals("115.7", totals[0][0]);
   }
   
   */
   
   
   
   @Test
   public void RoundingTest() {
	   double sum=54.4599999*1000;
	   int a = (int)(sum);
	   assertEquals(54459, a);
	   
   }
   
   @Test
   public void RoundingTestTwo() {
	   double sum=54.4599999*1000;
	   int a = (int)(sum);
	   int d = a%10;
	   assertEquals(9,d);
	   
   }
   
   @Test
   public void RoundingTestThree() {
	   double sum=54.4599999*1000;
	   double tot=54.4599999*100;
	   int b = (int)(tot);
	   int a = (int)(sum);
	   int d = a%10;
	   if(d>=5) {
		   b+=1;
	   } 
	   
	   assertEquals(5446,b);
	   
   }
   

   @Test
   public void RoundingTestFour() {
	   createDatabaseConnection();
	   double sum=54.4599999;
	   boolean should = BSD.shouldRoundUp(sum);
	   
	   assertTrue(should);
	   
   }
   

   @Test
   public void RoundingTestFive() {
	   createDatabaseConnection();
	   double sum=54.4539999;
	   boolean should = BSD.shouldRoundUp(sum);
	   
	   assertTrue(!should);
	   
   }
   
   @Test
   public void RoundingTestSix() {
	   createDatabaseConnection();
	   double sum=54.4599999;
	   Double a = BSD.round(sum);
	 
	  assertEquals((Double)54.46, a);
	   
   }
   
   
   
}
