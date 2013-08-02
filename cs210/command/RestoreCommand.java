/*
 * Barry Martin
 * CS210 
 * RestoreCommand.java
 */
package cs210.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Allows a user to restore a database from a specified file.
 */
public class RestoreCommand implements Command 
{
	private Pattern restoreCommand= Pattern.compile("\\s*restore\\s*from\\s*'(\\S+)'\\s*;*",Pattern.CASE_INSENSITIVE);
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = restoreCommand.matcher(input.trim());
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
		System.out.println("This is a correct restore command");

	}

}
