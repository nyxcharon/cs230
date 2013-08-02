/*
 * Barry Martin
 * CS210 
 * DefineIndexCommand.java
 */
package cs210.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Allows a user to create an index within a table.
 */
public class DefineIndexCommand implements Command 
{
	private Pattern definePattern=Pattern.compile("\\s*define\\s+index\\s+on\\s+(\\S+)\\s*\\(\\s*(.+)\\s*\\)\\s*;\\s*",Pattern.CASE_INSENSITIVE);
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = definePattern.matcher(input.trim());
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
		System.out.println("This is a correct DefineIndex command");
	}
}
