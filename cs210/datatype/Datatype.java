package cs210.datatype;

import java.io.IOException;
import java.io.RandomAccessFile;

public abstract class Datatype<T> implements Comparable<T> 
{
	
	public Datatype() 
	{
		super();
	}	

	public abstract void writeToFile(RandomAccessFile raf) throws IOException;
	public abstract void writeToFile(RandomAccessFile raf, int offset) throws IOException;
	public abstract String toString();
	
}