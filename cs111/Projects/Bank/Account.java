
//package Bank;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Barry Martin
 * Corey Hinkle 
 * CS111 Section 4 
 * Bank.java
 */
public abstract class Account 
{
    int acctNumber;
    Double balance;
    ArrayList<Transaction> trans;
    int numTrans;
    
    public Account(int acctNumber,Double balance)
    {
        this.acctNumber=acctNumber;
        this.balance=balance;
        trans=new ArrayList<Transaction>();
        numTrans=0;
    }
    public abstract boolean withdraw(Double amount);
    
    public void addInterest(Double interest)
    {
        balance+=balance*interest;
    }
    
    public void deposit(double amount)
    {
        balance+=amount;
        addTransaction(new Transaction(this.acctNumber,"Deposit",amount));
    }
    
    public void addTransaction(Transaction t)
    {
        numTrans++;
        trans.add(t);
    }
    
    public void displayTransactions()
    {
        for (int i=0;i<trans.size();i++)
            System.out.println(trans.get(i));
    }
    
    public Double getBalance()
    {
        return balance;
    }
    
    public int getAccountNumber()
    {
        return acctNumber;
    }
    public void setBalance(Double balance)
    {
        this.balance=balance;
    }
    
    public String toString()
    {
        return "Account number: "+ acctNumber+ " Balance: "+balance;
    }
    
    
    
}
