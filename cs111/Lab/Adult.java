/*
Barry Martin
CS111-04
TA Corey Hinkle
Adult.java
*/

public class Adult extends Person
{
	String job;
	public Adult()
	{
		super();
		job="Unemployed";
	}

	public Adult(String first, String last, int age, String job)
	{
		super(first,last,age);	
		this.job=job;
	}

	public void setJob(String job)
	{
		this.job=job;
	}

	public String getJob()
	{
		return job;
	}
	
	public void printPerson()
	{
		System.out.println("Name: "+first+" "+last+" Age: "+age+" Job: "+job);
	}

}
