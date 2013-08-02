/*
 * Barry Martin
 * CS210 
 * PrintCommand.java
 */
package cs210.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.Database;
import cs210.database.TableException;
/**
 * Allows a user to print the contents of a table or the entire database.
 */
public class PrintCommand implements Command 
{
	private Pattern printPattern= Pattern.compile("\\s*print\\s+(\\S+)\\s*;\\s*",Pattern.CASE_INSENSITIVE);
	private String tableName;
	boolean all;
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = printPattern.matcher(input.trim());
		if(match.matches())
		{
			tableName=match.group(1);
			if(tableName.equalsIgnoreCase("dictionary"))
				all=true;
			else
			all=false;
			return true;
			
		}	
		return false;
	}
	
	/**
	 * If the specified table exist this method will display
	 * a string representation of that table.
	 */
	@Override
	public void run() 
	{
		Database db=Database.getDatabase();
		if(!all)
		{
			try 
			{
				System.out.println(db.toStringTable(tableName));
				System.out.println(readFile());
			} catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
		}
		else
		{	
			System.out.print(db.toStringAllTables());
		}
	}
	
	public String readFile() throws TableException, Exception
	{
		return Database.getDatabase().getTable(tableName).readData();
	}
	
}
