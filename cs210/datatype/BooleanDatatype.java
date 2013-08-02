package cs210.datatype;

import java.io.IOException;
import java.io.RandomAccessFile;

import cs210.database.TableException;

public class BooleanDatatype extends Datatype<BooleanDatatype>
{
	private final boolean data;
	
	public BooleanDatatype(String data) throws TableException 
	{
		if (data.replace("'", "").trim().equalsIgnoreCase("true"))
			this.data=true;
		else if  (data.replace("'", "").trim().equalsIgnoreCase("false"))
			this.data=false;
		else
			throw new TableException("Error: Invalid Boolean Data");
		
	}
	
	public BooleanDatatype(RandomAccessFile raf) throws IOException
	{
		data=raf.readBoolean();
	}
	
	@Override
	public int compareTo(BooleanDatatype bd) 
	{
		return (data==bd.data ? 0:1);
	}

	@Override
	public void writeToFile(RandomAccessFile raf) throws IOException 
	{
			raf.seek(raf.length());
			raf.writeBoolean(data);	
	}

	@Override
	public String toString() 
	{
		return ""+data;
	}

	@Override
	public void writeToFile(RandomAccessFile raf, int offset)
			throws IOException {
		raf.seek(offset);
		raf.writeBoolean(data);	
	}

}
