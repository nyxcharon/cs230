/*
 * Barry Martin
 * CS210 
 * DefineTableCommand.java
 */
package cs210.command;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.jar.Attributes.Name;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.Database;
import cs210.database.Table;
import cs210.database.TableException;
import cs210.database.field.*;
import cs210.xml.XMLWriter;
/**
 * Allows a user to create(define) a table. 
 */
public class DefineTableCommand implements Command 
{
	private Pattern definePattern= Pattern.compile("\\s*define\\s*table\\s*(\\S+)\\s*having\\s*fields\\s*\\((.+)\\)\\s*;",Pattern.CASE_INSENSITIVE);
	private String tableName,group;
	private ArrayList<Field> fieldList;
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = definePattern.matcher(input);
		if(match.matches())
		{
			tableName=match.group(1);
			group=match.group(2);
			return true;
		}
		return false;
	}


	 /**
	  * Creates the table object with the specified name
	  * and fields given in the define table statement and
	  * adds it to the database.
	 */
	@Override
	public void run() 
	{
		Database db=Database.getDatabase();
		try{
			db.addTable(tableName, group);
			System.out.println("Table '"+tableName+"' defined");
		}
		catch (TableException ex)
		{
			System.out.println(ex.getMessage());
		}
		 XMLWriter xw=new XMLWriter();
		    try {
				xw.write();
			} catch (FileNotFoundException e) {
				System.out.println("XML Error");
			}
			
	}
	
	
	
}
