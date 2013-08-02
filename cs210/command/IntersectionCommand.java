/*
 * Barry Martin
 * CS210 
 * IntersectionCommand.java
 */
package cs210.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Allows a user to perform a inner join of two tables.
 */
public class IntersectionCommand implements Command 
{
	private Pattern intersectionPattern=Pattern.compile("\\s*intersect\\s*(\\S+)\\s*and\\s+(\\S+)\\s*;\\s*",Pattern.CASE_INSENSITIVE);
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = intersectionPattern.matcher(input);
		if(match.matches())
		{
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see cs210.command.Command#run()
	 */
	@Override
	public void run() 
	{
		System.out.println("This is a correct intersection command");
	}

}
