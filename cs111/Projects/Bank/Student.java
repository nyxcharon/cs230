
//package Bank;

/**
 * @author Barry Martin
 * Corey Hinkle 
 * CS111 Section 4 
 * Student.java
 */
public class Student extends Customer
{
    public Double SAVINGS_INTEREST=0.03;
    public Double CHECKINGS_INTEREST=0.01;
    public Double CHECK_CHARGE=0.0;
    public Double OVERDRAFT_PENALTY=15.0;
    
    public Student(int custNum,String name,String teleNum,String address, int age)
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
