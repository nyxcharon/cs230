/*
 * Barry Martin
 * CS210 
 * Table.java
 */
package cs210.database;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cs210.database.field.BooleanField;
import cs210.database.field.CharField;
import cs210.database.field.DateField;
import cs210.database.field.Field;
import cs210.database.field.IntegerField;
import cs210.database.field.RealField;
import cs210.database.field.VarcharField;
import cs210.datatype.BooleanDatatype;
import cs210.datatype.CharDatatype;
import cs210.datatype.Datatype;
import cs210.datatype.DateDatatype;
import cs210.datatype.IntegerDatatype;
import cs210.datatype.RealDatatype;
import cs210.datatype.VarcharDatatype;

/**
 * Allows a user to persistently store data
 * during execution time.
 */
public class Table 
{

	private String name;
	private ArrayList<Field> fields;
	private String filepath;
	private int rowByteSize; 
	
	/**
	 * Instantiates a new table with a given list of fields
	 *
	 * @param name the name of the table
	 * @param fields the list of fields for this table
	 */
	public Table(String name,String fieldList) throws TableException
	{
		this(name);
		rowByteSize=0;
		fields=new ArrayList<Field>();
		filepath="simpleDB"+File.separator+name;
		parseInput(fieldList);
	}
	
	/**
	 * Instantiates a new table with a given name
	 * @param name the name of the table
	 */
	public Table(String name) 
	{
		this.name=name;
		filepath="simpleDB"+File.separator+name;
		fields=new ArrayList<Field>();
		rowByteSize=0;
	}
	
	public void addField(String name, String type, String value)throws TableException
	{
		Field field;
		if(type.equalsIgnoreCase("integer"))
			field=new IntegerField(name);
		else if(type.equalsIgnoreCase("varchar"))
			field=new VarcharField(name);
		else if(type.equalsIgnoreCase("boolean"))
			field=new BooleanField(name);
		else if(type.equalsIgnoreCase("real"))
			field=new RealField(name);
		else if(type.equalsIgnoreCase("date"))
			field=new DateField(name);
		else if(type.equals("char"))
			field=new CharField(name,value);
		else
			throw new TableException("Error: Invalid Field Type Specified");
		fields.add(field);
		rowByteSize+=field.getWriteSize();
	}
	
	
	/**
	 * Gets the table name.
	 *
	 * @return the table name
	 */
	public String getTableName()
	{
		return name;
	}
	
	public void setTable(String newName)
	{
		name=newName;
	}
	
	/**
	 * Returns the contents of the entire table
	 */
	@Override
	public String toString()
	{
		String tableString="Table: "+name+" | ";
		for(Field f:fields)
			tableString+=f.toString()+" | ";
		return tableString;
		
	}
	
	public String toStringRow(int rowNum) throws TableException
	{
	
		 RandomAccessFile raf;
		 String row="\t\t";
		 try
		 {
			 raf = new RandomAccessFile(new File(filepath), "r");
			 raf.seek(rowNum*rowByteSize+2);
			 for(Field f:fields)
				 row+=f.readFile(raf).toString()+"\t";

			 raf.close();
		 }
		 catch (IOException e)
		 {
			 throw new TableException("Error: Write to file did not finish cleanly");
		 }
		
		
		 return row;
	}
	
	/**
	 * Parses the input
	 * @param input the user specified field list
	 * @throws TableException if the field list is improperly written 
	 */
	private void parseInput(String input)throws TableException
	{
		if (input.length()==0)
			throw new TableException("Error: No parameters specified");
		String[] fieldList=input.trim().split(",");
		for(String field: fieldList)
		{
			String[] currF=field.trim().split("\\s+");
			if(currF.length!=2 && !field.contains("char"))
				throw new TableException("Error: Invalid Table Parameters");
			
			String currName=currF[0];
			String currType=currF[1];
			
			if(field.contains("char") && !field.contains("varchar"))
			{
				fields.add(new CharField(name,currF));
			}
			else
				addField(currName,currType,"");
		}
		checkFields();
		
	}
	
	
	
	/**
	 * Checks the list of fields to make sure there are no duplicates
	 * @throws TableException if a duplicate field is found
	 */
	private void checkFields() throws TableException
	{
		for(Field cField: fields)
		{
			int count=0;
			for(Field tempField:fields)
			{
				if (cField.getName().equals(tempField.getName()))
					count++;
			}
			if (count>1)
				throw new TableException("Error: Fields names can only be used once");
		}
	}
	
	/**
	 * Returns a XML string of the table
	 * @return the tables XML string.
	 */
	public String toXML()
	{
		String tableXML="\t<TABLE>\n\t\t<TABLENAME>"+name+"</TABLENAME>\n";
		for(Field cField: fields)
		{
			tableXML+=cField.toXML();
		}
		return tableXML+"\t</TABLE>";
	}
	
	public void writeData(String data) throws TableException
	{
		if(data ==null)
			throw new TableException("Error: Input is null");
		
		String[] dataList=data.trim().split(",");
		Datatype[] dataTypes=new Datatype[fields.size()];
		
		if (dataList.length!=fields.size() )
			throw new TableException("Error: Invalid number of parameters specified");
		
		for(int i=0;i<fields.size();i++)
		{
				dataTypes[i]=fields.get(i).addDatatype(dataList[i]);
		}
	
		RandomAccessFile raf;
		try 
		{
			raf = new RandomAccessFile(new File(filepath), "rw");
			raf.seek(raf.length());
			raf.writeBoolean(true);//Delete byte.
			rowByteSize++;
			for(Datatype d:dataTypes)
			{
				d.writeToFile(raf);
			}
			raf.close();
		} 
		catch (IOException e)
		{
				throw new TableException("Error: Write to file did not finish cleanly");
		}
		
		
	}
	
	public String readData() throws TableException 
	{
		File check = new File("simpleDB" + File.separator + name);
		if (!check.exists())
			return "";

		String dataS = "\t\t";

		try 
		{
			RandomAccessFile raf = new RandomAccessFile(new File(filepath), "r");
			while (raf.getFilePointer() < raf.length()) 
			{
				boolean read=raf.readBoolean();
				if(read==true)
				{
					for (int i = 0; i < fields.size(); i++) 
					{
						dataS += fields.get(i).readFile(raf).toString() + " \t ";
					}
					dataS += "\n\t\t";
				}
				else
					raf.seek(raf.getFilePointer()+rowByteSize-1);

			}
			raf.close();
		} 
		catch (IOException e) 
		{
			throw new TableException("Error: Read failed");
		}
		return dataS + "\n";
	}
	
	public ArrayList<Integer> select(String name, String relop, String value) throws TableException
	{
		
		ArrayList<Integer> rows=new ArrayList<Integer>();
		if(!this.toString().contains(name))
			return rows;
			
		int valOffset=0;
		Field cField = null;
		for(Field cf:fields)
		{
			if(!cf.getName().equals(name))
				valOffset+=cf.getWriteSize();
			else
			{ 
				cField=cf;
				break;
			}
		}
		if(cField==null)
			throw new TableException("Error: Field "+name+" doesn't exist");
		Datatype d2=cField.addDatatype(value);
		
		try 
		{
			RandomAccessFile raf = new RandomAccessFile(new File(filepath), "r");
			int count=0;
			while (raf.getFilePointer() < raf.length()) 
			{
				boolean readLine=raf.readBoolean();
				if(readLine)
				{
					raf.skipBytes(valOffset);
					Datatype d1=cField.readFile(raf);
					if(eval(d1,d2,relop))
					{
						rows.add(new Integer(count));
					}

				}
				raf.skipBytes(rowByteSize-cField.WRITE_SIZE);
				count++;
			}
			raf.close();
		} 
		catch (IOException e) 
		{
			throw new TableException("Error: Read failed");
		}
		return rows;
		
	}
	
	private boolean eval(Datatype d1, Datatype d2, String relop) throws TableException
	{
		if (relop.equals("="))
			return d1.compareTo(d2) == 0;
		else if (relop.equals("!="))
			return d1.compareTo(d2) != 0;
		else if (relop.equals("<"))
			return d1.compareTo(d2) < 0;
		else if (relop.equals(">"))
			return d1.compareTo(d2) > 0;
		else if (relop.equals("<="))
			return d1.compareTo(d2) <=0;
		else if (relop.equals(">="))
			return d1.compareTo(d2) >= 0;
			else throw new TableException("Error: Eval failed");
	}
	
	public void update(ArrayList<Integer> rowNums, String fieldname, String newValue)throws TableException
	{
		int valOffset=1;
		Field cField = null;
		for(Field cf:fields)
		{
			if(!cf.getName().equals(fieldname))
				valOffset+=cf.getWriteSize();
			else
			{
				cField=cf;
				break;
			}
		}
		if(cField==null)
			throw new TableException("Error: Field "+name+" doesn't exist");
		try 
		{
			RandomAccessFile raf = new RandomAccessFile(new File(filepath), "rw");
				for (int i = 0; i < rowNums.size(); i++) 
				{
					valOffset=valOffset+(rowByteSize*rowNums.get(i))+2;
					cField.toFile(newValue, raf, valOffset);
				}
			raf.close();
		} 
		catch (IOException e) 
		{
			throw new TableException("Error: Update failed");
		}
	
	}
	
	public void update(String fieldname, String newValue) throws TableException
	{
		ArrayList<Integer> allrows=new ArrayList<Integer>();
		try 
		{
			RandomAccessFile raf = new RandomAccessFile(new File(filepath), "rw");
			int rowNums=(int)raf.length()/rowByteSize;
				for (int i = 0; i < rowNums; i++) 
					allrows.add(i);	
			raf.close();
		} 
		catch (IOException e) 
		{
			throw new TableException("Error: Update failed");
		}
		update(allrows,fieldname, newValue);
	}
	
	public void deleteAllRows()
	{
		File deleteme= new File(filepath);
		deleteme.delete();
	}
	
	public void deleteRow(int rowNum) throws TableException
	{
		try 
		{
			RandomAccessFile raf = new RandomAccessFile(new File(filepath), "rw");
			raf.seek(rowByteSize*rowNum);
			raf.writeBoolean(false);
			raf.close();
		} 
		catch (IOException e) 
		{
			throw new TableException("Error: Update failed");
		}
	}
	
}
