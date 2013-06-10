//package Bank;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
/**
 * @author Barry Martin
 * Corey Hinkle 
 * CS111 Section 4 
 * Transaction.java
 */
public class Transaction
{
    int acctNum;
    String transType;
    Double amount;
    String date;
    
    
    public Transaction(int acctNum,String transType,Double amount)
    {
	this.acctNum=acctNum;
	this.transType=transType;
	this.amount=amount;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        date=dateFormat.format(cal.getTime());
        
	//I'm using the calender class since the Date class has been deprecated 
        //for a while 
    }

    public int getAcctNum()
    {
	return acctNum;
    }

    public String getTransType()
    {
	return transType;
    }
    
    public Double getAmount()
    {
	return amount;
    }

    public String getDate()
    {
	return date;
    }
    
    public String toString()
    {
	return "Account Number "+acctNum+" "+transType+" "+date+" Amount:"+amount;
    }
}
