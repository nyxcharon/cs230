package cs210.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.TransformerConfigurationException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import cs210.database.Database;
import cs210.database.Table;
import cs210.database.TableException;
import cs210.database.field.*;


/**
 * DatabaseReader allows the Database program to restore it to it's previously used state. 
 * @author bmartin4
 *
 */
public class DatabaseReader 
{

	XMLReader reader;
	public DatabaseReader() throws SAXException, IOException
	{
	    reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(new DatabaseHandler());
	}
	
	public void parse(String file) throws IOException, SAXException
	{
		reader.parse(file);
	}
	
	public static void main(String[] args) throws SAXException, IOException, TransformerConfigurationException 
	{
		
		new DatabaseReader().saxReader();
		
	}

	public void saxReader() throws SAXException, IOException 
	{
		XMLReader reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(new DatabaseHandler());
	} 



}

/**
 * DatabaseHandler parses the xml file specified and attempts to create tables from it
 * @author bmartin4
 *
 */
class DatabaseHandler extends DefaultHandler
{
	boolean name,type, tableName, val;
	String tbName, fieldName;

	

	/**
	 * Turns on flags as the parser enters that block of XML
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{
		
		if(qName.equals("TABLENAME"))
				tableName=true;
		else if(qName.equals("NAME"))
				name=true;
		else if (qName.equals("TYPE"))
				type=true;
		else if (qName.equals("VALUE"))
				val=true;
		
	}
	

    /**
     * Turns off flags as the parser leaves that block of XML
     */
	public void endElement(String uri, String localName, String qName)throws SAXException 
	{
		if(qName.equals("TABLENAME"))
			tableName=false;
		else if(qName.equals("NAME"))
			name=false;
		else if (qName.equals("TYPE"))
			type=false;
		else if (qName.equals("VALUE"))
			val=false;
		
	} 
	

	/**
	 * Collects the information between the tags and makes a fieldlist with which can be used to make a table
	 */
	public void characters(char[] ch, int start, int length)
	throws SAXException 
	{
		String value = new String(ch, start, length); 
		if(tableName)
		{
			tbName=value;
			try 
			{
				Database.getDatabase().addTable(tbName);
			} catch (TableException e) 
			{
				System.out.println(e.getMessage());
			}
		}
		else if(name)
			fieldName=value;
		else if(type)
		{
			if(!value.equals("char"))
				try 
				{
					Database.getDatabase().getTable(tbName).addField(fieldName, value, "");
				} catch (TableException e)
				{
					System.out.println("XML Reading Error: "+e.getMessage());
				}
		}
		else if (val)
		{
			try 
			{
				Database.getDatabase().getTable(tbName).addField(fieldName, "char", value);
			} catch (TableException e) 
			{
				System.out.println("XML Reading error "+e.getMessage());
			}
		}
	} 
}



