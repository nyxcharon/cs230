package cs210.database.field;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;

import cs210.database.TableException;
import cs210.datatype.Datatype;
import cs210.datatype.DateDatatype;
import cs210.datatype.RealDatatype;

/**
 * Allows creation of a field with type real.
 */
public class RealField extends Field 
{
	
	public RealField(String fieldName) 
	{
		super(fieldName, "real", 8);
		
	}

	@Override
	public void toFile(String data, RandomAccessFile raf) throws TableException 
	{
		try 
		{
			 new RealDatatype(data).writeToFile(raf);
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
			return new RealDatatype(file);
		} 
		catch (IOException e) 
		{
			throw new TableException("Error: Read failed");
		}
	}

	@Override
	public Datatype addDatatype(String input) throws TableException 
	{
		return new RealDatatype(input);
	}

	@Override
	public void toFile(String data, RandomAccessFile file, int offset)throws TableException 
	{
		try 
		{
			 new RealDatatype(data).writeToFile(file,offset);
		} 
		catch (IOException e) 
		{
			throw new TableException("Error: Write failed");
		}
		
	}


}
