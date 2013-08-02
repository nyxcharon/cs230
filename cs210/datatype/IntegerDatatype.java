package cs210.datatype;

import java.io.IOException;
import java.io.RandomAccessFile;

import cs210.database.TableException;

public class IntegerDatatype extends Datatype<IntegerDatatype>
{
	private final int idata;
	

	public IntegerDatatype(RandomAccessFile raf) throws IOException
	{
		this.idata=raf.readInt();			
	}
	
	public IntegerDatatype(String idata) throws TableException
	{
		try
		{
			this.idata=Integer.parseInt(idata.trim());
		}
		catch (NumberFormatException ex)
		{
			throw new TableException("Error: Invalid Integer Data");
		}
	}
	 
	public void writeToFile(RandomAccessFile raf) throws IOException
	{
		raf.seek(raf.length());
		raf.writeInt(idata);
	}
	
	@Override
	public int compareTo(IntegerDatatype id) 
	{
		if(idata > id.idata)
			return 1;
		else if (idata < id.idata)
			return -1;
		else
			return 0;
	}
	
	public String toString()
	{
		return ""+idata;
	}

	@Override
	public void writeToFile(RandomAccessFile raf, int offset)throws IOException 
	{
		raf.seek(offset);
		raf.writeInt(idata);
		
	}
	
	
}
