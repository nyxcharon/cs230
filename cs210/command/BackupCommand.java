/*
 * Barry Martin
 * CS210 
 * BackupCommand.java
 */
package cs210.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Allows a user to backup the database to a file.
 */
public class BackupCommand implements Command 
{
	private Pattern backupPattern = Pattern.compile("\\s*backup\\s+to\\s+'(\\S+)'+\\s*;*");
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		input=input.toLowerCase();
		Matcher match = backupPattern.matcher(input);
		if (match.matches())
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
		System.out.println("This is a correct backup command");
	}

}
