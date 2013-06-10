public class MyList<T>
{
    Node<T> head;
    public MyList()
    {
	head=null;
    }
    
    public T front()
    {
	return head.getData();
    }

    public void add(T it)
    {
	if(head==null)
	    {
	    head=new Node(it);
	    }
	else
	    {
		Node<T> curr=head;
		while(curr.getNext()!=null)
		    {
			curr=curr.getNext();
		       
		    }
		curr.setNext(new Node<T>(it));
	    }
    }

    public T remove()
    {
	if(empty())
	    return null;
	else
	    head=head.getNext();

	return head.getData();
    }

    public boolean empty()
    {
	return head == null; 
    }

    public void display()
    {
	Node<T> c=head;
	while(c!=null)
	      {
		  System.out.println(c.getData());
		  c=c.getNext();
	      }
    }
}