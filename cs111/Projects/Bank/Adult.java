
//package Bank;

/**
 * @author Barry Martin
 * Corey Hinkle 
 * CS111 Section 4 
 * Adult.java
 */
public class Adult extends Customer
{
    public Double SAVINGS_INTEREST=0.06;
    public Double CHECKINGS_INTEREST=0.02;
    public Double CHECK_CHARGE=0.5;
    public Double OVERDRAFT_PENALTY=25.0;
    
    public Adult(int custNum,String name,String teleNum,String address, int age)
    {
      super(custNum,name,teleNum,address,age);
    }
    

    @Override
    public double getSavingsInterest() 
    {
        return this.SAVINGS_INTEREST;
    }

    @Override
    public double getCheckInterest() 
    {
       return this.CHECKINGS_INTEREST;
    }

    @Override
    public double getCheckCharge() 
    {
       return this.CHECK_CHARGE;
    }

    @Override
    public double getOverdraftPenalty() 
    {
        return this.OVERDRAFT_PENALTY;
    }
    
}
