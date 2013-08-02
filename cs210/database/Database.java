package cs210.database;

import java.util.HashMap;

/**
 * This class stores all the tables for the program as well
 * as allows user to modify and remove them.
 */
public class Database 
{
	private static Database db;
	private HashMap<String,Table> tables;
	
	private Database()
	{

		tables= new HashMap<String,Table>();
	}
	
	/**
	 * If needed, creates the database otherwise returns the current one.
	 * @return the Database object.
	 */
	public static Database getDatabase()
	{
		if (db==null)
			db=new Database();
		return db;
	}
	
	/**
	 * Adds another table to the database.
	 * @param table the table to add to the database.
	 */
	public void addTable(String name, String input) throws TableException
	{
		if(!hasTable(name))
		tables.put(name, new Table(name,input));
		else throw new TableException("Error: Table with name "+name+" already defined");
	}
	
	public Table getTable(String name) throws TableException
	{
		if(hasTable(name))
			return tables.get(name);
			else throw new TableException("Error: Table with name "+name+" doesn't exist");
	}
	
	/**
	 * Adds an empty table to the database.
	 * @param name the name of the database
	 * @throws TableException 
	 */
	 public void addTable(String name) throws TableException
	 {
		 if(!hasTable(name))
				tables.put(name, new Table(name));
		 else throw new TableException("Error: Table with name "+name+" already defined");
	 }
	
	/**
	 * Allows the removal of a table from the database.
	 * @param name the key for the specified table to remove.
	 */
	public void removeTable(String name) throws TableException
	{
		if(hasTable(name))
		{
			tables.remove(name);
			System.out.println("Table "+name+" dropped");
		}
		else throw new TableException("Error: Table with name "+name+" does not exist");
	}
	
	/**
	 * Returns a string version of a table from the database given its name.
	 * @param name the name of the table to get.
	 * @return the specified table from the database.
	 * @throws TableException if the table does not exist. 
	 */
	public String toStringTable(String name) throws TableException
	{
		if(db.hasTable(name))
			return tables.get(name).toString();
		else throw new TableException("Error: Table with name "+name+" does not exist");
	}
	
	/**
	 * Returns all tables from the database.
	 * @return the hashmap of tables.
	 */
	public String toStringAllTables()
	{
		String allTables="";
		for (Table currentTable : tables.values()) 
		{
		    try {
				allTables+=currentTable.toString()+"\n"+currentTable.readData()+"\n";
			} catch (Exception e) {
				allTables+="";
			} 
		}
		return allTables;
	}
	
	/**
	 * Check to see if this database contains the specified table.
	 * @param name the key for the specified table to remove.
	 * @return if the database contains the specified table.
	 */
	private boolean hasTable(String name)
	{
		return tables.containsKey(name);
	}
	
	/**
	 * Renames a table in the database to a new specified name
	 * @param tableName the current table name
	 * @param newName the new table name
	 * @throws TableException if the old table doesn't exist or if the new table already exist
	 */
	public void renameTable(String tableName,String newName) throws TableException
	{
		if(hasTable(tableName))
		{
			if(!db.hasTable(newName))
			{
				tables.get(tableName).setTable(newName); //rename old table
				tables.put(newName, tables.get(tableName)); //put into hashmap
				tables.remove(tableName); // remove old table
				System.out.println("Table "+tableName+" renamed");
			}
			else throw new TableException("Error: Table with name "+newName+" already exist");
	    }
		else throw new TableException("Error: Table with name "+tableName+" does not exist");
	}
	
	/**
	 * Returns an xml string that represents this database
	 * @return the xml string of the database
	 */
	public String toXML()
	{
		String allTables="<DATABASE>\n";
		for (Table currentTable : tables.values()) 
		{
		    allTables+=currentTable.toXML()+"\n";
		}
		return allTables+"</DATABASE>\n";
	}
	
	public void writeTable(String tbName, String data) throws  TableException
	{
		if (hasTable(tbName))
			tables.get(tbName).writeData(data);
		else
			throw new TableException("Table '"+tbName+"' doesn't exist");
	}
	
	
}
