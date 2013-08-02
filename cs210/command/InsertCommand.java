/*
 * Barry Martin
 * CS210 
 * InsertCommand.java
 */
package cs210.command;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.Database;
import cs210.database.Table;
import cs210.database.TableException;
import cs210.database.field.Field;
import cs210.database.field.VarcharField;

/**
 * Allows a user to insert a given value of a specified type into a table.
 */
public class InsertCommand implements Command 
{
	private Pattern insertPattern=Pattern.compile("\\s*insert\\s*\\((.+)\\)\\s*into\\s+(\\S+)\\s*;\\s*",Pattern.CASE_INSENSITIVE);

	String input, tbName;
	Matcher match;
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String in) 
	{
		 match = insertPattern.matcher(in.trim());
		if(match.matches())
		{
			input=match.group(1);
			tbName=match.group(2);
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
	
		try {
			Database.getDatabase().writeTable(tbName, input);
			System.out.println("Insert OK");
		} catch (TableException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

}
