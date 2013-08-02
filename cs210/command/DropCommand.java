/*
 * Barry Martin
 * CS210 
 * DropCommand.java
 */
package cs210.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.Database;
import cs210.database.TableException;
import cs210.xml.XMLWriter;

/**
 * Allows a user to remove the table from the database.
 */
public class DropCommand implements Command
{
	private Pattern dropPattern=Pattern.compile("\\s*drop\\s+table\\s+(\\S+)\\s*;\\s*",Pattern.CASE_INSENSITIVE);
	private String tableName;
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = dropPattern.matcher(input);
		if(match.matches())
		{
			tableName=match.group(1);
			return true;
		}
		return false;

	}

	/**
	 * Removes the specified table from the database.
	 */
	@Override
	public void run() 
	{
		Database db=Database.getDatabase();
		try 
		{
			File tableF=new File("simpleDB"+File.separator+tableName);
			if(tableF.exists())
				tableF.delete();
			db.removeTable(tableName);
		} 
		catch (TableException e) 
		{
			System.out.println(e.getMessage());
		}
		
		 XMLWriter xw=new XMLWriter();
		    try {
				xw.write();
			} catch (FileNotFoundException e) {
				System.out.println("XML Error");
			}
		 

	}

}
