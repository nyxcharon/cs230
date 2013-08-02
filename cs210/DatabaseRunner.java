/* Barry Martin
 * CS210 
 * Database.java
 */
package cs210;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

import org.xml.sax.SAXException;

import cs210.command.BackupCommand;
import cs210.command.Command;
import cs210.command.DefineIndexCommand;
import cs210.command.DefineTableCommand;
import cs210.command.DeleteCommand;
import cs210.command.DropCommand;
import cs210.command.ExitCommand;
import cs210.command.InsertCommand;
import cs210.command.IntersectionCommand;
import cs210.command.JoinCommand;
import cs210.command.MinusCommand;
import cs210.command.OrderCommand;
import cs210.command.PrintCommand;
import cs210.command.ProjectionCommand;
import cs210.command.ReadCommand;
import cs210.command.RenameCommand;
import cs210.command.RestoreCommand;
import cs210.command.SelectCommand;
import cs210.command.UnionCommand;
import cs210.command.UpdateCommand;
import cs210.database.Table;
import cs210.xml.DatabaseReader;
import cs210.xml.XMLWriter;

/**
 * Allows a user to create a database which can then
 * be used interactively or manipulate by a file. 
 */
public class DatabaseRunner 
{
	
	private Command[] commands;
	
	
	/**
	 * Instantiates a new database.
	 */
	public DatabaseRunner()
	{
	   commands=new Command[]{
				new BackupCommand(),
				new ExitCommand(),
				new PrintCommand(),
				new ReadCommand(),
				new RestoreCommand(),
				new DefineTableCommand(),
				new DefineIndexCommand(),
				new DeleteCommand(),
				new DropCommand(),
				new InsertCommand(),
				new UpdateCommand(),
				new SelectCommand(),
				new UnionCommand(),
				new IntersectionCommand(),
				new JoinCommand(),
				new MinusCommand(),
				new OrderCommand(),
				new ProjectionCommand(), 
				new RenameCommand()};
	}
	
	/**
	 * Main method that creates and starts the database program.
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args) throws SAXException, IOException 
	{
		//Check to see if the user previously used the program, if so restore what they had.
		File dir=new File("simpleDB");
		if(!dir.exists())
			dir.mkdir();
		File dbxml=new File("simpleDB"+File.separator+"simpledb.xml");
		if(dbxml.exists())
		{
			DatabaseReader dbr=new DatabaseReader();
			dbr.parse(dbxml.getAbsolutePath());
		}
			
		
		System.out.println(">>Database Command Line Interface. Input your command(s):");
		DatabaseRunner db = new DatabaseRunner();
				 	   db.run();
	}

	/**
	 * Creates the database and then waits for user input.
	 */
	private void run() 
	{
	    Scanner in=new Scanner(System.in);
	    while(true)
	    {
	    	read(in);
	    }
	   
	}
	
	/**
	 * Checks if the users input matches any known command pattern.
	 * If so, it process the input and then runs that command.
	 * @param input the user specified input
	 */
	public void eval(String input)
	{
			boolean found=false;
			for(Command currentCommand: commands)
			{
				if(currentCommand.matches(input))
				{
					currentCommand.run();
					found=true;
					break;
				}	
			}
			if(!found)
				System.out.println("This is not a valid command");
	}

	/**
	 * Takes in a scanner and then evaluates each line of input
	 * which is provided either by a file or by an interactive prompt.
	 * @param sc the scanner object to process
	 */
	public void read(Scanner sc) 
	{
		while(sc.hasNextLine())
		{ 
			String lastEntered="";
			while(!lastEntered.contains(";") && sc.hasNextLine())
			{
				if(sc.hasNextLine())
				lastEntered+=" "+sc.nextLine();	
			}
			if (lastEntered.contains(";"))
			eval(lastEntered);
		}
	}
	
	
}
