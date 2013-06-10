/**********************
 *Barry Martin
 *CS 111 LAB Section 4
 *MartinBarry3.java
 *********************/

public class MartinBarry3
{
    public static void main(String[] args)
    {
	System.out.println("2 to the third power: "+ power(3));
	System.out.println("Hello World in revese: "+reverse("Hello World"));


    }

    public static int  power(int n)
    {
	if(n==1)
	    return 2;
	else
	    return 2* power(n-1);
    }

    public static String reverse(String s)
    {
	if(s.length()==1)
	    return s;
	else
	    return s.substring(s.length()-1,s.length())+reverse(s.substring(0,s.length()-1));
    }
}