package cs210.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import cs210.database.Database;

/**
 * Writes the Database to a file.
 * @author bmartin4
 *
 */
public class XMLWriter 
{
	String xml;
	String xmlFile;
	public XMLWriter()
	{
		xml=Database.getDatabase().toXML();
		xmlFile="simpleDB"+File.separator+"simpledb.xml";
	}
	
	/**
	 * Write the database to a specific filename and location
	 * @param file the filepath and name for the database
	 * @throws FileNotFoundException
	 */
	public void write(String file) throws FileNotFoundException
	{
		
		PrintWriter out = new PrintWriter(new FileOutputStream(file));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println(xml);
		out.close();
	}
	
	/**
	 * Writes the database to the current directory with the name simpledb.xml
	 * @throws FileNotFoundException
	 */
	public void write() throws FileNotFoundException
	{
		//Check to see if our directory is there
		File dir=new File("simpleDB");
		if(!dir.exists())
			dir.mkdir();
		
		PrintWriter out = new PrintWriter(new FileOutputStream(xmlFile));
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println(xml);
		out.close();
	}
}
