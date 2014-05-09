/*                                                                        
Barry Martin                                                                
Baseball.java                                                                  
CS 440                                                                     
*/


public class Baseball 
{
	private String name,city,position,league;
	private int year;
	
	public Baseball(String name, String city, String position, String league, int year)
	{
		this.name=name;
		this.city=city;
		this.position=position;
		this.league=league;
		this.year=year;
	}
	
	public String toString()
	{
		return "Name: "+name+"\nCity: "+city+"\nPosition: "+position+"\nLeague: "+league+"\nYear: "+year+"\n";
	}
}
