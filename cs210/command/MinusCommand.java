/*
 * Barry Martin
 * CS210 
 * MinusCommand.java
 */
package cs210.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Allows a user to create a diff of two tables and display the result.
 */
public class MinusCommand implements Command 
{
	private Pattern minusCommand=Pattern.compile("\\s*minus\\s*(\\S+)\\s*and\\s+(\\S+)\\s*;\\s*",Pattern.CASE_INSENSITIVE);
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = minusCommand.matcher(input.trim());
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
		System.out.println("This is a correct minus command");
	}

}
