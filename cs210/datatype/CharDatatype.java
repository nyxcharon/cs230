package cs210.datatype;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.Database;
import cs210.database.TableException;

public class CharDatatype extends Datatype<CharDatatype>
{
	private final String data;
	private Pattern charPattern = Pattern.compile("\\s*'\\p{Alpha}+\\'\\s*");
	
	public CharDatatype(String data, int length) throws TableException
	{
		Matcher match=charPattern.matcher(data);
		if (!match.matches())
			throw new TableException("Error: Invalid Char Data");
		try
		{
			this.data=data.replace("'", "").trim();
			if(this.data.length()!=length)
				throw new TableException("Error: Invalid Char Data");
		}
		catch (NumberFormatException ex)
		{
			throw new TableException("Error: Invalid Char Data");
		}
	}

	public CharDatatype(RandomAccessFile raf, int size) throws TableException
	{
		String chars="";
		for(int i=0;i<size;i++)
		{		
		 try 
		 {
			chars+=""+raf.readChar();
		  } 
		 catch (IOException e) 
		  {
			 e.printStackTrace();
			throw new TableException("Error: read failed");
	      }
		}
		data=chars;
	}

	@Override
	public int compareTo(CharDatatype cd) 
	{
		return data.compareTo(cd.data);
	}

	@Override
	public void writeToFile(RandomAccessFile raf) throws IOException 
	{
		raf.seek(raf.length());
		raf.writeChars(data);
		
	}

	@Override
	public String toString() 
	{
		return data;
	}

	@Override
	public void writeToFile(RandomAccessFile raf, int offset)throws IOException 
	{
		raf.seek(offset);
		raf.writeChars(data);
	}
	
}
