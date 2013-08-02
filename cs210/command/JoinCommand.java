/*
 * Barry Martin
 * CS210 
 * JoinCommand.java
 */
package cs210.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Allows a user to display the combination of two queries.
 */
public class JoinCommand implements Command 
{
	private Pattern joinPattern=Pattern.compile("\\s*join\\s*(.*)\\s*and\\s+(\\S+)\\s*;\\s*",Pattern.CASE_INSENSITIVE);
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = joinPattern.matcher(input.trim());
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
		System.out.println("This is a correct join command");
	}

}
