/*
 * Barry Martin
 * CS210 
 * ExitCommand.java
 */
package cs210.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This command causes the program to exit when run.
 */
public class ExitCommand implements Command 
{
	private Pattern exitPattern= Pattern.compile("\\s*exit\\s*;*",Pattern.CASE_INSENSITIVE);
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = exitPattern.matcher(input.trim());
		if(match.matches())
		{
			return true;
		}
		return false;
	}

	/**
	 * Informs the user that the program is exiting and then terminates
	 * the command line interface
	 */
	@Override
	public void run() 
	{
		System.out.println("Exiting now...");
		System.exit(0);
	}

}
