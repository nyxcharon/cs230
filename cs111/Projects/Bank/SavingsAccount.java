
//package Bank;

import java.util.ArrayList;

/**
 * @author Barry Martin
 * Corey Hinkle 
 * CS111 Section 4 
 * SavingsAccount.java
 */
public class SavingsAccount extends Account
{
   
    
    public SavingsAccount(int acctNumber,Double balance)
    {
        super(acctNumber,balance);
        
    }
    @Override
    public boolean withdraw(Double amount) 
    {
        if(super.getBalance()>=amount)
        {
            super.setBalance(balance-amount);
            super.addTransaction(new Transaction(acctNumber,"Withdraw",amount));
            return true;
        }
        return false;
    }
   
    
}
