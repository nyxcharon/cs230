/*
 * Barry Martin
 * CS210 
 * OrderCommand.java
 */
package cs210.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Allows a user to display a expression result in descending or ascending order.
 */
public class OrderCommand implements Command 
{
	private Pattern orderCommand=Pattern.compile("\\s*order\\s*(\\S+)\\s*by\\s+(\\S+)+\\s*(?:\\s+descending)?;\\s*",Pattern.CASE_INSENSITIVE);
	private String query,field;
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = orderCommand.matcher(input);
		if(match.matches())
		{
			query=match.group(1);
			field=match.group(2);
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
		System.out.println("This is a correct order command");
	}


}
