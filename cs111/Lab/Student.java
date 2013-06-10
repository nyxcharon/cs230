/*
Barry Martin
CS111-04
TA Corey Hinkle
Student.java
*/

public class Student extends Person
{	
	double gpa;
	int grade;
	
	public Student()
	{
		super();
		gpa=0.0;
		grade=1;
	}
	public Student(String first, String last, int age, Double gpa, int grade)
	{
		super(first,last,age);
		this.gpa=gpa;
		this.grade=grade;
	}

	public Double getGPA()
	{
		return gpa;
	}
	
	public int getGrade()
	{
		return grade;
	}

	public void setGPA(double gpa)
	{
		this.gpa=gpa;
	}

	public void setGrade(int grade)
	{
		this.grade=grade;
	}

	public void printPerson()
	{
		System.out.println("Name: "+first+" "+last+" Age: "+age
					+" GPA: "+gpa+" Grade: "+grade);
	}
}
	
