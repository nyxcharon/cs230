package cs210.datatype;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.TableException;

public class DateDatatype extends Datatype<DateDatatype>
{
	private final long data;
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private Pattern datePattern = Pattern.compile("\\s*'\\d\\d/\\d\\d/\\d\\d\\d\\d'\\s*");
	
	public DateDatatype(String data) throws TableException
	{
		Matcher match=datePattern.matcher(data);
		if(!match.matches())
			throw new TableException("Error: Invalid Date Data");
		try
		{
			this.data=Date.parse(data.replaceAll("'", "").trim());
		}
		catch (NumberFormatException ex)
		{
			throw new TableException("Error: Invalid Data");
		}
	}
	
	public DateDatatype(RandomAccessFile raf) throws IOException
	{
		data=raf.readLong();	
	}
	
	@Override
	public int compareTo(DateDatatype ndata) 
	{
		if(this.data>ndata.data)
			return 1;
		else if(this.data<ndata.data)
			return -1;
		else
			return 0;
	}

	@Override
	public void writeToFile(RandomAccessFile raf) throws IOException 
	{
		raf.seek(raf.length());
		raf.writeLong(data);
	}

	@Override
	public String toString() 
	{
		return dateFormat.format(data);
	}

	@Override
	public void writeToFile(RandomAccessFile raf, int offset)throws IOException 
	{
		raf.seek(offset);
		raf.writeLong(data);
	}

}
