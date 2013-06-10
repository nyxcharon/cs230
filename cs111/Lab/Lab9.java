import java.util.*;
/* Barry Martin
 * CS111-04
 * Corey Hinkle
 * Lab9.java
 */

public class Lab9
{
   
    public static void main(String[] args)
    { 
	LinkedList<LinkedList<String>> datebook=new LinkedList<LinkedList<String>>();
	Scanner in=new Scanner(System.in);
	//Add List for days 1-10
	for(int i=0;i<10;i++)
	    {
		datebook.add(new LinkedList<String>());
	    }
	
	boolean done=false;
	while(!done)
	    {
		System.out.println("1.Add an event to the datebook");
		System.out.println("2.Clear a day of all events");
		System.out.println("3.Display all days and events");
		System.out.println("4.Exit the application");
		System.out.println("Please enter your choice (1-4)");
		int input =in.nextInt();
		if(input==1)
		    {
			System.out.println("Please select a day (1-10)");
			int day=in.nextInt();
			in.nextLine();
			System.out.println("Please enter the task to add");
			String task=in.nextLine();
			System.out.println("Task Added");
			datebook.get(day-1).add(task);
		    }
		if(input==2)
		    {
			System.out.println("Please select a day (1-10)");
			int day=in.nextInt();
			in.nextLine();
			datebook.get(day-1).clear();
			System.out.println("All entries for "+day+" cleared");
		    }
		if(input==3)
		    {
			for(int i=0;i<datebook.size();i++)
			    for(int j=0;j<datebook.get(i).size();j++)
				System.out.println("Day "+i+": "+datebook.get(i).get(j));
		    }
		if(input==4)
		    done=true;

		System.out.println("------");
	    }
    }
}