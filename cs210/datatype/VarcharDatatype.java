package cs210.datatype;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.TableException;

public class VarcharDatatype extends Datatype<VarcharDatatype>
{
	private String vData;
	private final String varcharFile="simpleDB"+File.separator+"varchar";
	private Pattern varcharPattern = Pattern.compile("\\s*'\\s*(.+)\\s*'\\s*");
	
	public VarcharDatatype(String vData) throws TableException 
	{
		Matcher match=varcharPattern.matcher(vData);
		if (!match.matches())
			throw new TableException("Error: Invalid Varchar Data");
		this.vData=vData.replace("'","").trim();
	}
	
	public VarcharDatatype(RandomAccessFile file) throws IOException
	{
		vData="";
		RandomAccessFile varchar = new RandomAccessFile(new File(varcharFile), "rw");
		long startPos = file.readLong();
		int wordL = file.readInt();
		varchar.seek(startPos);

		for (int i = 0; i < wordL; i++)
			vData += varchar.readChar();
		vData = vData.replace("'", "").trim();
		varchar.close();
	}

	@Override
	public int compareTo(VarcharDatatype vd) 
	{
		return vData.compareTo(vd.vData);
	}

	@Override
	public String toString() 
	{
		return vData;
	}

	@Override
	public void writeToFile(RandomAccessFile raf) throws IOException 
	{
		RandomAccessFile varchar=new RandomAccessFile(new File(varcharFile), "rw");
		raf.seek(raf.length());
		raf.writeLong(varchar.length());
		raf.writeInt(vData.length());
		varchar.seek(varchar.length());
		varchar.writeChars(vData.replace("'", "").trim());
		varchar.close();	
	}

	@Override
	public void writeToFile(RandomAccessFile raf, int offset) throws IOException 
	{
		raf.seek(offset);
		RandomAccessFile varchar=new RandomAccessFile(new File(varcharFile), "rw");
		raf.writeLong(varchar.length());
		raf.writeInt(vData.length());
		varchar.seek(varchar.length());
		varchar.writeChars(vData.replace("'", "").trim());
		varchar.close();	
		
	}
	
	
}
