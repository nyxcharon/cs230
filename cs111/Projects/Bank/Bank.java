//package Bank;

import java.util.Date;

/**
 * @author Barry Martin
 * Corey Hinkle 
 * CS111 Section 4 
 * Bank.java
 */
public class Bank
{
	Customer[] customers;
	int numCusts;

        /**
         * Makes an empty bank object with no customers
         */
	public Bank()
	{
            customers=new Customer[25];
            numCusts=0;
	}
        
        /**
         * Adds an account to a given customer
         * @param c the customer
         * @param a the account to add
         */
        public void addAccount(Customer c,Account a)
        {
           c.addAccount(a);
        }
        
        /**
         * Add a customer to the Bank
         * @param c 
         */
        public void addCustomer(Customer c)
        { 
            customers[numCusts]=c;
            numCusts++;
        }
	
        /**
         * Method to deposit money into an account
         * @param acctNum the Account Number
         * @param custNum the Customer Numner
         * @param amount the amount to deposit
         * @param date the date and t
         */
	public void makeDeposit(int acctNum, int custNum, Double amount, Date date)
	{
            Account t=getAccount(acctNum,custNum);
            t.deposit(amount);
	}

        /**
         * Withdraw money from a given account
         * @param accNum The account number
         * @param custNum The customer number
         * @param amount The amount to withdraw
         * @param date The current date
         */
	public void makeWithdraw(int accNum, int custNum, Double amount, Date date)
	{
            Account t=getAccount(accNum,custNum);
            Customer c=getCustomer(custNum);
            boolean success=t.withdraw(amount);
            if(!success)
                if(t instanceof CheckingAccount)
                    t.setBalance(t.getBalance()-c.getOverdraftPenalty());
                else
                    System.out.println("Withdraw amount exceeds current funds.");
            
	}

        /**
         * Returns a given account for the customer to modify
         * @param acctNum the Account Number
         * @param custNum the Customer Number
         * @return the Account to modify
         */
	public Account getAccount(int acctNum, int custNum)
	{
               return getCustomer(custNum).getAccount(acctNum);
	}
        
        /**
         * Method to search for a customer in the Bank
         * @param custNum the customer to search for
         * @return the Customer 
         */
	public Customer getCustomer(int custNum)
	{
            for(int i=0;i<numCusts;i++)
            {
                if(custNum==customers[i].getCustomerNumber())
                        return customers[i];
            }
            System.err.println("CUSTOMER ERROR: No customer with number "+custNum+" found");
            return null;
            
	}
        
        /**
         * Prints the list of customers currently in the bank.
         */
        public void printCustomerList()
        {
            for (int i=0;i<numCusts;i++)
                System.out.println(customers[i]);
        }
        
        /**
         * Returns the number of customers
         * @return the number of customers
         */
	public int getNumCusts()
	{
		return numCusts;
	}
}
