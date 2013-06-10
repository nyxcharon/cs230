public class ListTest
{
    public static void main(String[] args)
    {
	MyList<Person> l=new MyList<Person>();
	l.add(new Person());
	l.add(new Person("B","a",20));
	l.add(new Person("C", "a",20));
	l.display();
	System.out.println("-----");
	l.remove();
	l.remove();
	l.display();
	System.out.println("--------");
	l.add(new Person("D","a",20));
	l.display();
	System.out.println("------------");
	System.out.println(l.front());
    }

}