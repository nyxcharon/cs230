
//package infixpostfix;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Barry Martin
 * CS111-4
 * InfixPostfixTester.java
 * Converts a given infix expression to postfix
 * 
 */
public class InfixPostfixConverter 
{
    String infix="";
    Queue<Character> q;
    
    public InfixPostfixConverter(String infix)
    {
        this.infix=infix;
        q=new LinkedList<Character>();
    }
    
    public void convert() throws Exception
    {
        //String postfix="";
        Stack<Character> stack=new Stack<Character>();
        int paren=0;
        int vars=0;
        int ops=0;
        boolean lastWasVar=false;
        for(int i=0;i<infix.length();i++)
        {
            char c=infix.charAt(i);
            
            if (Character.isLetter(c))
            {
                vars++;
                q.add(c);             
                if(lastWasVar==true)
                    throw new Exception("Missing Operator Error");
                lastWasVar=true;
            }
            else
            {
                lastWasVar=false;
                switch (c)
                {
                    case '(':
                        paren++;
                        stack.push(c);
                        break;
                    case ')':
                        paren--;
                        if(paren>=0)
                        {
                            while(stack.peek()!='(')
                            {    
                                q.add(stack.pop());
                            }
                        }
                        stack.pop();//remove open parenthesis
                        break;
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        ops++;
                        while(!stack.isEmpty() && stack.peek()!='(' && 
                                getPrecedence(c)<=getPrecedence(stack.peek()))
                        {
                            q.add(stack.pop());
                        }
                        stack.push(c); //save operator
                        break;
                }
            }
           
        }
         if(paren!=0)
               throw new Exception("Missing Parentheses Error");
         if(vars<=ops)
                throw new Exception("Missing Operand Error");
         while(!stack.isEmpty())
             q.add(stack.pop());
     
         
    }
     
     

    
    public void showPostfix()
    {
      Iterator it=q.iterator();
      while(it.hasNext())
          System.out.print(it.next());
      System.out.println();
    }
    
    public String getInfix()
    {
        return infix;
    }
    
    
    public void setInfix(String infix)
    {
        this.infix=infix;
        q=new LinkedList<Character>();
    }
    
    private int getPrecedence (char operator)
    {
        switch (operator)
        {
            case '(':
            case ')':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        } 
        return -1;
    } 
}
