/*
 * Barry Martin
 * CS210 
 * ProjectionCommand.java
 */
package cs210.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Allows a user to project a query give a set of fields.
 */
public class ProjectionCommand implements Command 
{
	private Pattern projectionPattern=Pattern.compile("\\s*project\\s+(.+)\\s+over\\s+(.+)\\s*;\\s*");
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		input=input.toLowerCase();
		Matcher match = projectionPattern.matcher(input);
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
		System.out.println("This is a correct projection command");
	}

}
