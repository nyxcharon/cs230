/*
 * Barry Martin
 * CS210 
 * RenameCommand.java
 */
package cs210.command;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.Database;
import cs210.database.Table;
import cs210.database.TableException;
import cs210.xml.XMLWriter;

/**
 * Allows a user to rename a table.
 */
public class RenameCommand implements Command 
{
	private Pattern renamePattern=Pattern.compile("\\s*rename\\s+table\\s+(\\S+)\\s+to\\s+(\\S+)\\s*;\\s*",Pattern.CASE_INSENSITIVE);
	private String oldName,newName;
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = renamePattern.matcher(input);
		if (match.matches())
		{
			oldName=match.group(1);
			newName=match.group(2);
			return true;
		}	
		return false;
	}

	/**
	 * Changes the previous name of a table
	 * to the new specified name.
	 */
	@Override
	public void run() 
	{
		Database db=Database.getDatabase();
		
			try 
			{
				db.renameTable(oldName, newName);
			} catch (TableException e) 
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
