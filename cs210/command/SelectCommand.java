/*
 * Barry Martin
 * CS210 
 * SelectCommand.java
 */
package cs210.command;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.Database;
import cs210.database.TableException;
/**
 * Allows a user to select a table to work with. 
 */
public class SelectCommand implements Command 
{
	
	private Pattern selectPattern=Pattern.compile("select\\s+(\\S+)\\s*(?:\\s+where\\s+(.*))?;\\s*", Pattern.CASE_INSENSITIVE);
	private String tableName;
	private String whereClause;
	private boolean where;
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		input=input.toLowerCase();
		Matcher match = selectPattern.matcher(input.trim());
		if(match.matches())
		{
			tableName = match.group(1);
			if(match.group(2)!=null) 
			{
				where=true;
				whereClause = match.group(2);
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
		Database db=Database.getDatabase();
		if (!where) 
		{
			try 
			{
				System.out.println(db.toStringTable(tableName));
				System.out.println(Database.getDatabase().getTable(tableName).readData());
			} 
			catch (TableException e) 
			{
				System.out.println(e.getMessage());
			}
		}
		else
		{
		
			ArrayList<Integer>rows = new ArrayList<Integer>();
				try 
				{
					String[] whereA=whereClause.split("\\s+");

					if(whereClause.contains("'"))
					{
						int start=whereClause.indexOf("'");
						int end=whereClause.lastIndexOf("'");
						if(start != -1 && end != -1)
							whereA[2]=whereClause.substring(start,end+1);	
						else throw new TableException("Error: Invalid where");
					}
					if (whereA.length==3)
					{
						rows=Database.getDatabase().getTable(tableName).select(whereA[0],whereA[1],whereA[2]);
					}
					else
						System.out.println("Invalid where clause");
					
					System.out.println(db.getTable(tableName.toString()));
					for(int i=0;i<rows.size();i++)
						System.out.println(db.getTable(tableName).toStringRow(rows.get(i)));
				} 
				catch (TableException e) 
				{
					System.out.println(e.getMessage());
				}
			
					
				
		}
	}
	
	

}
