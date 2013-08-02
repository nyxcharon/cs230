package cs210.database.field;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

import cs210.database.TableException;
import cs210.datatype.Datatype;
import cs210.datatype.IntegerDatatype;

/**
 * Allows creation of a field with type integer.
 */
public class IntegerField extends Field 
{

	
	public IntegerField(String fieldName) 
	{
		super(fieldName, "integer",4 );	
	}

	@Override
	public void toFile(String data, RandomAccessFile raf) throws TableException 
	{
		try 
		{
			new IntegerDatatype(data).writeToFile(raf);
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
			return new IntegerDatatype(file);
		} 
		catch (IOException e) 
		{
			throw new TableException("Error: Read failed ");
		}
	
	}

	@Override
	public Datatype addDatatype(String input) throws TableException 
	{
		return new IntegerDatatype(input);
	}

	@Override
	public void toFile(String data, RandomAccessFile file, int offset)throws TableException 
	{
		try 
		{
			new IntegerDatatype(data).writeToFile(file, offset);
		} 
		catch (IOException e) 
		{
			throw new TableException("Error: Write failed");
		}
		
	}

}
