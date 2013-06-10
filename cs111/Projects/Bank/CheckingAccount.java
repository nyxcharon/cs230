/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package Bank;

import java.util.ArrayList;

/**
 * @author Barry Martin
 * Corey Hinkle 
 * CS111 Section 4 
 * CheckingAccount.java
 */
public class CheckingAccount extends Account
{
   

    
    public CheckingAccount(int acctNumber,Double balance)
    {
        super(acctNumber,balance);
        
    }
    @Override
    public boolean withdraw(Double amount) 
    {
       super.setBalance(balance-amount);
       super.addTransaction(new Transaction(acctNumber,"Withdraw",amount));
        
        if(super.getBalance()>=amount)
        {
            super.setBalance(balance);
            return true;
        }
        else
        return false;
    }
   
    
}
