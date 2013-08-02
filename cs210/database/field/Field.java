package cs210.database.field;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import cs210.database.TableException;
import cs210.datatype.Datatype;

/**
 * Abstract Field that all field types must implement.
 */
public abstract class Field 
{
	protected String fieldName;
	protected String fieldType;
	public final int WRITE_SIZE;
	/**
	 * Creates a field with the specified name and type.
	 * @param fieldName the name of the new field
	 * @param fieldType the type of the field. 
	 */
	public Field(String fieldName, String fieldType, int WRITE_SIZE)
	{
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.WRITE_SIZE=WRITE_SIZE;
	}
	/**
	 * Returns a string representation of a field for 
	 * displaying the contents of a table.
	 */
	public String toString()
	{
		return fieldName+" <"+fieldType+">";
	}
	
	/**
	 * Returns the name of the field.
	 * @return the field name.
	 */
	public String getName()
	{
		return fieldName;
	}
	
	public String getFieldType()
	{
		return fieldType;
	}
	
	/**
	 * Returns an XML string of the field
	 * @return the xml string of this field
	 */
	public String toXML()
	{
		return "\t\t<FIELD>\n\t\t\t<NAME>"+fieldName+"</NAME>\n\t\t\t<TYPE>"+fieldType+"</TYPE>\n\t\t</FIELD>\n";
	}
	
	/**
	 * Writes this field to a binary file
	 * @throws IOException 
	 */
	public abstract void toFile(String data, RandomAccessFile file) throws TableException;
	public abstract void toFile(String data, RandomAccessFile file, int offset) throws TableException;
	public abstract Datatype readFile(RandomAccessFile file) throws TableException;
	public abstract Datatype addDatatype(String input)throws TableException;
	
	public int getWriteSize()
	{
		return WRITE_SIZE;
	}

}
