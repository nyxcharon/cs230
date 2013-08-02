package cs210.database.field;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

import cs210.database.TableException;
import cs210.datatype.BooleanDatatype;
import cs210.datatype.Datatype;

/**
 * Allows creation of a field with type boolean.
 */
public class BooleanField extends Field 
{
	
	public BooleanField(String fieldName) 
	{
		super(fieldName, "boolean",1);
	}

	public void toFile(String data, RandomAccessFile raf) throws  TableException 
	{
		try 
		{
			new BooleanDatatype(data).writeToFile(raf);
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
			return new BooleanDatatype(file);
		} 
		catch (IOException e) 
		{
			throw new TableException(e.getMessage());
		}
	}

	@Override
	public Datatype addDatatype(String input) throws TableException 
	{
		return new BooleanDatatype(input);
	}

	@Override
	public void toFile(String data, RandomAccessFile file, int offset)
			throws TableException {
		try 
		{
			new BooleanDatatype(data).writeToFile(file, offset);
		} 
		catch (IOException e) 
		{
			throw new TableException("Error: Write failed");
		}
		
	}
}
