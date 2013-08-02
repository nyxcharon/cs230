/*
 * Barry Martin
 * CS210 
 * ReadCommand.java
 */
package cs210.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.DatabaseRunner;

/**
 * Allows a user to specify a file and will then process it one command at a time
 */
public class ReadCommand implements Command 
{
	private Pattern readPattern=Pattern.compile("\\s*read\\s+'(.+)'\\s*;\\s*",Pattern.CASE_INSENSITIVE);
	String filename;
	
	/* (non-Javadoc)
	 * @see cs210.command.Command#matches(java.lang.String)
	 */
	@Override
	public boolean matches(String input) 
	{
		
		Matcher match = readPattern.matcher(input.trim());
		if(match.matches())
		{
			filename = match.group(1);
			return true;
		}
		return false;
	}

	
	
	/** 
	 * Checks that the specified file exist and if so, creates
	 * a new database and processes each command in the file.
	 */
	@Override
	public void run() 
	{
		Scanner f=null;
		try 
		{
			f=new Scanner(new File(filename));
			new DatabaseRunner().read(f);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: "+filename);
		}
		
		
	
	}
	


}
