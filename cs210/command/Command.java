/*
 * Barry Martin
 * CS210 
 * Command.java
 */
package cs210.command;

/**
 * This is the generic interface all commands implement. 
 */
public interface Command 
{
	
	/**
	 * Checks to see if the user given input matches the commands pattern
	 *
	 * @param input the user specified command
	 * @return true, if the input matches the command pattern
	 */
	public boolean matches(String input);
	
	/**
	 * Process the users input and modifys the database accordingly. 
	 */
	public void run();
	
}
