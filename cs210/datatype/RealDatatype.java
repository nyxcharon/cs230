package cs210.datatype;

import java.io.IOException;
import java.io.RandomAccessFile;

import cs210.database.TableException;

public class RealDatatype extends Datatype<RealDatatype>
{
	private final Double rdata;
	
	public RealDatatype(String rdata) throws TableException 
	{
		try
		{
			this.rdata=Double.parseDouble(rdata);
		}
		catch (NumberFormatException ex)
		{
			throw new TableException("Error: Invalid Real Data");
		}
	}
	
	public RealDatatype(RandomAccessFile raf) throws IOException
	{
		this.rdata=raf.readDouble();
	}
	
	@Override
	public int compareTo(RealDatatype rd) 
	{
		if(this.rdata>rd.rdata)
			return 1;
		else if(this.rdata<rd.rdata)
			return -1;
		else
			return 0;
	}

	@Override
	public void writeToFile(RandomAccessFile raf) throws IOException 
	{
		raf.seek(raf.length());
		raf.writeDouble(rdata);
	}

	@Override
	public String toString() 
	{
		return ""+rdata;
	}

	@Override
	public void writeToFile(RandomAccessFile raf, int offset)throws IOException 
	{
		raf.seek(offset);
		raf.writeDouble(rdata);
		
	}

}
