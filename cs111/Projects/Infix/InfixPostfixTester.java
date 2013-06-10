
//package infixpostfix;

import java.util.Scanner;

/**
 *
 * @author Barry Martin
 * CS111-4
 * InfixPostfixTester.java
 * Tester for the InfixPostfixConverter class
 * 
 */
public class InfixPostfixTester 
{
    public static void main(String[] args) 
    {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter a infix exspression to evaluate otherwise"
                + " press enter q to quit");
        String input=in.nextLine();
        while(!input.equalsIgnoreCase("q"))
        {
            InfixPostfixConverter c=new InfixPostfixConverter(input);
            try 
            {
                c.convert();
                c.showPostfix();
            } 
            catch (Exception ex) 
            {
               System.out.println(ex.getMessage()+" on "+c.getInfix());
            }
             System.out.println("Enter a infix exspression to evaluate otherwise"
                + " press enter q to quit");
             input=in.nextLine();
        }
    }
}
