/*
 * Barry Martin
 * CS210 
 * UnionCommand.java
 */
package cs210.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Allows a user to combine two query into a single result
 */
public class UnionCommand implements Command 
{
	
	private Pattern unionPattern=Pattern.compile("\\s*union\\s*(\\S+)\\s+and\\s+(\\S+)\\s*;\\s*",Pattern.CASE_INSENSITIVE);
	private String query1, query2;
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = unionPattern.matcher(input.trim());
		if (match.matches())
		{
			query1=match.group(1);
			query2=match.group(2);
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
		System.out.println("This is a correct union command");
	}

}
