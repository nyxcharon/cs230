public class MyArrayList<Object>
{
    Object[] a;
    int length;
    public MyArrayList()
    {
	length=0;
	a=new Object[5];
	//a=(Object[])Array.newInstance(Object.class,5);
    }
    
    public Object front()
    {
	return a[0];
	
    }

    public void add(Object it)
    {
	//You said to check for empty and return null, but it's a void 
	//method so I just kinda omitted that since i didn't see the point..
	if(length>=a.length)
	    resize();
	a[length]=it;
	length++;
    }

    public Object remove()
    {
	if(empty())
	    return null;
	Object[] temp=new Object[length--];
	Object removed=a[0];
	for(int i=0;i<length-1;i++)
	    temp[i]=a[i+1];
	a=temp;
	length--;
	return removed;
    }
    
    public void resize()
    {
	Object[] temp=new Object[a.length+10];
	for(int i=0;i<a.length;i++)
	    temp[i]=a[i];
	a=temp;
    }

    public boolean empty()
    {
	return length==0;
    }

    public void display()
    {
	for(Object c:a)
	    System.out.println(c);
    }
    

    
}
