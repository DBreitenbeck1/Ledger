package com.ledger.Ledger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ledger.Ledger.Entities.COLUMN;
import com.ledger.Ledger.Entities.Credit;
import com.ledger.Ledger.Entities.Debit;
import com.ledger.Ledger.Entities.Entry;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
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
   
   
   
}
