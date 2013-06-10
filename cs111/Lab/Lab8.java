/*
Barry Martin
CS111 Sec4
Corey Hinkle
*/

import java.util.*;
public class Lab8
{
   static Queue<Person> q=new LinkedList<Person>();
   static Scanner in=new Scanner(System.in);
    public static void main(String[] args)
    {

	boolean done=false;
	while(!done)
	    {
		System.out.println("Please enter a choice from the menu: ");
		System.out.println("1.Create a person and add to queue");
		System.out.println("2.Display queue");
		System.out.println("3.Remove a person from the queue and display it");
		System.out.println("4.Display the next person in the queue");
		System.out.println("5.Exit program");
		String c=in.next();
		
		if(c.equals("1"))
		    addPerson();
		else if(c.equals("2"))
		    displayQueue();
		else if (c.equals("3"))
		    removePerson();
		else if (c.equals("4"))
		    displayNextQueue();
		else if (c.equals("5"))
		    done=true;
	    }
    }

    public static void addPerson()
    {
	System.out.println("Please enter the person's first name");
	String fir=in.next();
	System.out.println("Please enter the persons's last name");
	String las=in.next();
	System.out.println("Please enter the persons age");
	int age=in.nextInt();

	q.add(new Person(fir,las,age));

	
    }
    
    public static void removePerson()
    {
	System.out.println("The person being removed from the queue is: "+q.poll());
	
    }

    public static void displayQueue()
    {
	Iterator it=q.iterator();
	while(it.hasNext())
	      System.out.println(it.next());
    }
    
    public static void displayNextQueue()
    {
		System.out.println("The current person at the top of the queue is: "+q.peek());
    }

}