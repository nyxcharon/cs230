
//package Bank;

/**
 * @author Barry Martin
 * Corey Hinkle 
 * CS111 Section 4 
 * Senior.java
 */
public class Senior extends Customer
{
    public Double SAVINGS_INTEREST=0.08;
    public Double CHECKINGS_INTEREST=0.04;
    public Double CHECK_CHARGE=0.6;
    public Double OVERDRAFT_PENALTY=35.0;
    
    public Senior(int custNum,String name,String teleNum,String address, int age)
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
