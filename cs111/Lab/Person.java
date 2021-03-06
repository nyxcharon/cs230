/*
Barry Martin
CS111-04
TA Corey Hinkle 
Person.java
*/
import java.io.IOException;

public class Person
{
    String last,first;
    int age;

    public Person()
    {
	first="Guy";
	last="Person";
	int age=4;
    }
    public Person(String first, String last, int age)
    {
	this.first=first;
	this.last=last;
	this.age=age;

    }

    public void setAge(int age) throws IOException 
    {
	if (age<0)
	    throw new IOException();
	else this.age=age;
    }
    public void setFirst(String first) throws IOException
    {
       	
	if(Character.isUpperCase(last.charAt(0)))
	    this.first=first; 
	else
	    throw new IOException();
    }
    public void setLast(String last) throws IOException
    {
	
        if (Character.isUpperCase(last.charAt(0)))
	this.last=last;
	else
	    throw new IOException();
    }

    public int getAge() 
    {
	return age;
    }
    public String getFirst()
    {
	return first;
    }
    public String getLast()
    {
	return last;
    }

    public void printPerson()
    {
	System.out.println("Name: "+first+" "+last+" Age: "+age);
    }

    public int compareTo(Object p)
    {
	if(p instanceof Person)
	    {
		Person anotherPerson=(Person)p;
		if (this.getAge()==anotherPerson.getAge())
		    return 0;

		return this.getAge()>anotherPerson.getAge()?1:-1;
	    }
	return -1;
    }

    public boolean equals(Object a)
    {
	if(a instanceof Person)
	{
	Person p=(Person)a;
	return (this.getAge()==p.getAge() && this.getLast().equals(p.getLast()) && this.getFirst().equals(p.getFirst()));
	}
	else return false;
    }

    public Person clone()
    {
	return new Person(this.getFirst(),this.getLast(),this.getAge());
    }
    public String toString()
    {
	return first+" "+last+" "+age;
	}
}
