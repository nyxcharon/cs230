//package Bank;
/**
 * @author Barry Martin
 * Corey Hinkle 
 * CS111 Section 4 
 * Customer.java
 */
public abstract class Customer 
{
	private String name,teleNum,address;
	private int numAccts,custNum,age;
        private Account[] accounts;
        private int accountNum=1000;
        


	public Customer(int custNum,String name,String teleNum,String address, int age)
	{
            this.custNum=custNum;
            this.name=name;
            this.teleNum=teleNum;
            this.address=address;
            this.age=age;
            accounts=new Account[20];
	}
	

	public void addAccount(Account act)
	{
            accounts[numAccts]=act;
            numAccts++;
        }
		

	public void displayAccounts()
	{
            for (int i=0;i<getNumAccounts();i++)
                System.out.println(accounts[i]);
	}
        
        public Account getAccount(int acctNumber)
        {
            for(int i=0;i<numAccts;i++)
            {
                if(acctNumber==accounts[i].getAccountNumber())
                        return accounts[i];
            }
            System.err.println("ACCOUNT ERROR: No account with number "+acctNumber+" found");
            return null;
        }
	public int getNumAccounts()
	{
		return numAccts;
	}
        
        public int getCustomerNumber()
        {
            return custNum;
        }
        
        public String toString()
        {
            return name+": "+custNum;
        }
        
        public int getAccountNumber()
        {
            return accountNum;
        }
        
        public void setAccountNumber()
        {
           accountNum++;
        }
        
        public abstract double getSavingsInterest();
        public abstract double getCheckInterest();
        public abstract double getCheckCharge();
        public abstract double getOverdraftPenalty();
        
      
	
}
