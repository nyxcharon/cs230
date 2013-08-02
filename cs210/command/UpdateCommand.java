/*
 * Barry Martin
 * CS210 
 * UpdateCommand.java
 */
package cs210.command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.Database;
import cs210.database.TableException;

/**
 * Allows a user to change a value for a given table and optionally
 * specify a where clause.
 */
public class UpdateCommand implements Command 
{
	
	private Pattern updatePattern=Pattern.compile ("\\s*update\\s+(\\S+)\\s+set\\s+(\\S+)\\s*=\\s*(\\S+)\\s*(?:\\s+where\\s+(.+))?\\s*;\\s*",Pattern.CASE_INSENSITIVE);
	private String tableName;
	private String whereClause;
	private String fieldName;
	private String newVal;
	private boolean where;
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		Matcher match = updatePattern.matcher(input);
		if(match.matches())
			{	
				tableName=match.group(1);
				fieldName=match.group(2);
				newVal=match.group(3);
				if(match.group(4)!=null) 
				{
					where=true;
					whereClause = match.group(4);
				}
				else
					where=false;
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
		
		
		if (!where) 
		{
			try 
			{
				Database.getDatabase().getTable(tableName).update(fieldName,newVal);
				System.out.println("Updated");
			} 
			catch (TableException e) 
			{
				System.out.println(e.getMessage());
			}
		}
		else
		{
			String[] whereA = null;
			ArrayList<Integer>rows = new ArrayList<Integer>();
			whereA=whereClause.split("\\s+");
			try 
			{
				if(whereClause.contains("'"))
				{
					int start=whereClause.indexOf("'");
					int end=whereClause.lastIndexOf("'");
					if(start != -1 && end != -1)
						whereA[2]=whereClause.substring(start,end+1);	
					else throw new TableException("Error: Invalid where");
				}
				if (whereA.length == 3) 
					rows = Database.getDatabase().getTable(tableName).select(whereA[0], whereA[1], whereA[2]);
				else
					System.out.println("Invalid where clause");
				Database.getDatabase().getTable(tableName).update(rows, whereA[0], whereA[2]);
				System.out.println("Updated");
			} 
			catch (TableException e) 
			{
				System.out.println(e.getMessage());
			}	
		}
		
	}

}
