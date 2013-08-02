package cs210.database.field;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.TableException;
import cs210.datatype.Datatype;
import cs210.datatype.VarcharDatatype;

/**
 * Allows creation of a field with type varchar
 */
public class VarcharField extends Field 
{
	private Pattern varcharPattern = Pattern.compile("\\s*'\\s*(.+)\\s*'\\s*");

	public VarcharField(String fieldName) 
	{
		super(fieldName, "varchar", 12);
		
	}

	@Override
	public void toFile(String data, RandomAccessFile raf) throws TableException 
	{
		try 
		{
			new VarcharDatatype(data).writeToFile(raf);
		} catch (IOException e) 
		{
			throw new TableException("Error: Write failed");
		}

	}
	

	@Override
	public Datatype readFile(RandomAccessFile file) throws TableException 
	{
		try 
		{
			return new  VarcharDatatype(file);
		} 
		catch (IOException e) 
		{ 
			throw new TableException("Error: File doesn't exist");
		}
	}

	@Override
	public Datatype addDatatype(String input) throws TableException 
	{
		return new VarcharDatatype(input);
	}

	@Override
	public void toFile(String data, RandomAccessFile file, int offset)throws TableException 
	{
		try 
		{
			new VarcharDatatype(data).writeToFile(file);
		} catch (IOException e) 
		{
			throw new TableException("Error: Write failed");
		}
	
		
	}

}
