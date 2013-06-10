import java.util.Scanner;
import java.io.IOException;
public class MartinBarry5
{
    public static void main(String[] args)
    {
	Person test=new Person();
	boolean correct=false;
	Scanner in=new Scanner(System.in);
	while(!correct)
	{
		System.out.println("Enter the persons first name");
		String fn=in.next();
		System.out.println("Enter the persons last name");
		String ln=in.next();
		System.out.println("Enter the persons age");
		int a=in.nextInt();
		in.nextLine();
		try
		    {
			test.setFirst(fn);
			test.setLast(ln);
			test.setAge(a);
			correct=true;
		    }
		catch (IOException e)
		    {
			System.out.println("An entry is incorrect");
			correct=false;
		    }
	}   
    }
}
		
	
