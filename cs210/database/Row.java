package cs210.database;
import java.util.ArrayList;

import cs210.datatype.Datatype;
public class Row 
{
	int length;
	ArrayList<Datatype> rowData;
	
	public Row(ArrayList<Datatype> data)
	{
		rowData=data;
		length=0;
			
	}

}
