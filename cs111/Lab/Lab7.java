
/*
Barry Martin
CS111 Sec4
Corey Hinkle
*/
import java.util.*;

public class Lab7 
{
	public static void main(String[] args)
	{
	   Stack<String> stack=new Stack<String>();
	   Scanner in=new Scanner(System.in);
	   
	   System.out.println("Please enter a string to be reversed");
 	   String org=in.nextLine();
	   in=new Scanner(org);

	   while(in.hasNext())
	 	stack.push(in.next());
		
	   String rev="";	   	
	   while(!stack.empty())
		rev+=stack.pop()+" ";
		
	    System.out.println(rev);
	}
}
