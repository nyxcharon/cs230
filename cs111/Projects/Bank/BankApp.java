
//package Bank;

import java.util.Scanner;

/**
 * @author Barry Martin
 * Corey Hinkle 
 * CS111 Section 4 
 * BankApp.java
 */
public class BankApp 
{
    
   static Customer current;
   static Bank bank = new Bank();
   static Scanner in=new Scanner(System.in);
   static int custnum=1000;
   static boolean bankMode=true;
   public static void main(String[] args)
   {
       
     
       boolean run=true;
       while(run)
       {
           if(bankMode)
               printBankMenu();
           else
               printCustomerMenu();
           
       }
   }
   
   public static void printCustomerMenu()
   {
       System.out.println("Currently editing customer: "+current.toString());
       System.out.println("Choices are:");
       System.out.println("1.Deposit money");
       System.out.println("2.Withdraw money");
       System.out.println("3.Open an account");
       System.out.println("4.Determine Balance");
       System.out.println("5.Display all transactions for an account");
       System.out.println("6.Display all transactions on all accounts");
       System.out.println("7.Quit to Bank menu");
       System.out.println("8.Quit Application");
       System.out.println("Please enter the number of your choice");
       int choice=in.nextInt();
       
       switch(choice)
        {
            case 1: 
            {
                System.out.println("Current accounts:");
                current.displayAccounts();
                System.out.println("Enter account number");
                int accntNum=in.nextInt();
                System.out.println("Enter ammount to deposit");
                double val=in.nextDouble();
                if(current.getAccount(accntNum)!=null)
                {
                current.getAccount(accntNum).deposit(val);
                System.out.println("Current Balance "
                        +current.getAccount(accntNum).balance);
                }
            } break;
            case 2:
            {
                System.out.println("Current accounts:");
                current.displayAccounts();
                System.out.println("Enter account number");
                int accntNum=in.nextInt();
                System.out.println("Enter ammount to withdraw");
                double val=in.nextDouble();
                  if(current.getAccount(accntNum)!=null)
                current.getAccount(accntNum).withdraw(val);
                System.out.println("Current Balance "
                        +current.getAccount(accntNum).balance);
            } break;
            case 3:
            {
                System.out.println("Enter 1 to open a savings account or 2 to "
                        + "open an checkings account");
                int c=in.nextInt();
                if(c==1)
                {
                    bank.addAccount(current, new SavingsAccount(current.getAccountNumber(),0.0));
                    System.out.println("New savings account created: "+current.getAccount(current.getAccountNumber()));
                    current.setAccountNumber();
                }
                if(c==2)
                {
                    bank.addAccount(current, new CheckingAccount(current.getAccountNumber(),0.0));
                    System.out.println("New savings account created: "+current.getAccount(current.getAccountNumber()));
                    current.setAccountNumber();
                }
                
            } break;
            case 4:
            {
                System.out.println("Current accounts:");
                current.displayAccounts();
                System.out.println("Enter account number");
                int accntNum=in.nextInt();
                System.out.println("Current Balance"
                        +current.getAccount(accntNum).balance);
            } break;
            case 5:
            {
                System.out.println("Current accounts:");
                current.displayAccounts();
                System.out.println("Enter account number");
                int accntNum=in.nextInt();
                current.getAccount(accntNum).displayTransactions();
            } break;
            case 6:
            {
                System.out.println("All account transactions:");
                for(int i=0;i<current.getNumAccounts();i++)
                    current.getAccount(1000+i).displayTransactions();
            } break;
            case 7:
            {
                bankMode=true;
                
            } break;
            case 8:
            {
                System.exit(0);
            }
        
       
       }
               
       
   }
   
   public static void printBankMenu()
   {
      if(bank.getNumCusts()==0)
      {
          System.out.println("There are no customers currently, create one? "
                  + "(y/n)");
          String choice=in.nextLine();
          if(choice.equalsIgnoreCase("y"))
          {
              addAccount();
          }
      }
      else
      {
          System.out.println("List of all current customers:");
          bank.printCustomerList();
          System.out.println();
          System.out.println("To modify one of these customers please "
                  + "enter there number, to create one press m, else enter q"
                  + " to quit");
          String choice=in.next();
          int num=-1;
          if(choice.equalsIgnoreCase("q"))
              System.exit(0);
          else if(choice.equalsIgnoreCase("m"))
                addAccount();
          else
               num=Integer.parseInt(choice);
          current=bank.getCustomer(num);
          if(current!=null)
          bankMode=false;
          
      }
          
          
      
   }
   
   public static void addAccount()
   {
       System.out.println("We will now prompt you for the information needed"
                      + "to make your new account with the bank");
      System.out.println("Enter your name");
      String name=in.nextLine();
      System.out.println("Enter your address");
      String address=in.nextLine();
      System.out.println("Enter your telephone number");
      String tel=in.nextLine();
      System.out.println("Enter your age");
      int age=in.nextInt();
      if(age<=24)
        bank.addCustomer(new Student(custnum,name,tel,address,age));
      else if(age>24 && age <60)
          bank.addCustomer(new Adult(custnum,name,tel,address,age));
      else
          bank.addCustomer(new Senior(custnum,name,tel,address,age));
      
      current=bank.getCustomer(custnum);
      custnum++;
   }
}
