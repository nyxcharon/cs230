package cs210.database.field;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.TableException;
import cs210.datatype.Datatype;
import cs210.datatype.DateDatatype;

/**
 * Allows creation of a field with type date.
 */
public class DateField extends Field 
{

	private Pattern datePattern = Pattern.compile("\\s*'\\d\\d/\\d\\d/\\d\\d\\d\\d'\\s*");
	
	public DateField(String fieldName) 
	{
		super(fieldName, "date", 8);
	}
	
	public void toFile(String data, RandomAccessFile raf) throws TableException 
	{
		try 
		{
	        new DateDatatype(data).writeToFile(raf);
	    } 
		catch (IOException e)
		{
			throw new TableException("Error: Write failed");
		}
	}

	@Override
	public Datatype readFile(RandomAccessFile file) throws TableException 
	{
		try 
		{
			return new DateDatatype(file);
		}
		catch (IOException e) 
		{
			throw new TableException("Error: File does not exist");
		}
		
	}

	@Override
	public Datatype addDatatype(String input) throws TableException 
	{
		return new DateDatatype(input);
	}

	@Override
	public void toFile(String data, RandomAccessFile file, int offset)throws TableException 
	{
		 try 
		 {
			new DateDatatype(data).writeToFile(file, offset);
		 } 
		 catch (IOException e) 
		 {
			throw new TableException("Error: Write failed");
		}
		
	}

}
