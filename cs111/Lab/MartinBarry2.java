/*
 *Barry Martin
 *CS111-04
 *TA Corey Hinkle
 *BarryMartin2.java
 */

import java.util.Scanner;

public class MartinBarry2
{
    public static void main(String[] args)
    {
	Scanner sc=new Scanner(System.in);
	boolean cont=true;
	String menu="Please make a selection \n"+
	            "A) Add values \n"+
	            "B) Subtract values \n"+
	            "C) Multiply values \n"+
	            "D) Divide values \n"+
	            "Type 'exit' to exit";
	while(cont)
        {
	    System.out.println(menu);
	    String option=sc.next();
	    if (option.equalsIgnoreCase("exit"))
		cont=false;
	    else if(option.equalsIgnoreCase("A"))
		{
		    System.out.println("Please enter the first integer value");
		    int n1=sc.nextInt();
		    System.out.println("Please enter the second integer value");
		    int n2=sc.nextInt();
		    System.out.println("The answer is "+(add(n1,n2)));
		    System.out.println();
		}
	    else if(option.equalsIgnoreCase("B"))
		{
		     System.out.println("Please enter the first integer value");
		    int n1=sc.nextInt();
		    System.out.println("Please enter the second integer value");
		    int n2=sc.nextInt();
		    System.out.println("The answer is "+(sub(n1,n2)));
		    System.out.println();
		}
	    else if(option.equalsIgnoreCase("C"))
		{
		     System.out.println("Please enter the first integer value");
		    int n1=sc.nextInt();
		    System.out.println("Please enter the second integer value");
		    int n2=sc.nextInt();
		    System.out.println("The answer is "+(mult(n1,n2)));
		    System.out.println();
		}
	    else if(option.equalsIgnoreCase("D"))
		{
		     System.out.println("Please enter the first integer value");
		    int n1=sc.nextInt();
		    System.out.println("Please enter the second integer value");
		    int n2=sc.nextInt();
		    System.out.println("The answer is "+(div(n1,n2)));
		    System.out.println();
		}
	    else 
		{
		    System.out.println("Invalid Selection");
		}

        }

    }
    public static int add(int in1,int in2)
    {
	return in1+in2;
    }
    public static int sub(int in1,int in2)
    {
	return in1-in2;
    }
    public static int mult(int in1,int in2)
    {
	return in1*in2;
    }
    public static double div(int in1, int in2)
    {
	return (double)(in1/in2);
    }
}