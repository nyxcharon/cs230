package cs210.database.field;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.TableException;
import cs210.datatype.CharDatatype;
import cs210.datatype.Datatype;

/**
 * Allows creation of a field with type char.
 */
public class CharField extends Field 
{
	public final int num;
	private final Pattern charFieldP=Pattern.compile("\\s*char\\s*\\(\\s*(\\d+)\\s*\\)\\s*");
	
	
	public CharField(String fieldName, String num) 
	{
		super(fieldName, "char",2 );
		this.num=Integer.parseInt(num);
	}
	
	public CharField(String fieldName, String[] data) throws TableException
	{
		super(fieldName, "char",2);
		try
		{
			this.num=Integer.parseInt(parseChar(data));
		}
		catch (NumberFormatException ex)
		{
			throw new TableException("Error: Invalid char data");
		}
	}
	public String toString()
	{
		return fieldName+" <"+fieldType+"("+num+")>";
	}
	
	public int size()
	{
		return num;
	}
	public String toXML()
	{
		return "\t\t<FIELD>\n\t\t\t<NAME>"+fieldName+"</NAME>\n\t\t\t<TYPE>"+fieldType+"</TYPE>\n\t\t\t<VALUE>"+num+"</VALUE>\n\t\t</FIELD>\n";
	}
	
	public void toFile(String data, RandomAccessFile raf) throws TableException 
	{
		try 
		{
			new CharDatatype(data,num).writeToFile(raf);
		} 
		catch (IOException e) 
		{
			throw new TableException("Error: Write failed");
		}		
	}

	@Override
	public Datatype readFile(RandomAccessFile file) throws TableException
	{
		return new CharDatatype(file,num);
	}

	@Override
	public Datatype addDatatype(String input) throws TableException 
	{
		return new CharDatatype(input,num);
	}
	private String parseChar(String[] currF) throws TableException
	{
		String currType="";
		for(int i=1;i<currF.length;i++)
			currType+=currF[i];
		Matcher match=charFieldP.matcher(currType);
		if(match.matches())
			return match.group(1);
		else
			throw new TableException("Error: Invalid char data");
		
	}

	@Override
	public void toFile(String data, RandomAccessFile file, int offset) throws TableException 
	{
		try 
		{
			new CharDatatype(data,num).writeToFile(file, offset);
		} 
		catch (IOException e) 
		{
			throw new TableException("Error: Write failed");
		}		
		
	}
	
}
