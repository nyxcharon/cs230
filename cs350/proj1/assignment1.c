/*
Barry Martin
CS350 Project 1
assignment1.c
*/

#include <stdio.h>
#include <stdlib.h>

#define ASCII_a 97
#define ASCII_z 122
#define ASCII_A 65
#define ASCII_Z 90
char result[100];

int toInt(char input[]);
double toFloat(char input[]);
void toUpper(char *input);
void toLower(char *input);
char* reverseString(char *input);
int getLength(char *input) ;
void isPalindrome(char *input);
void reverseSentence(char * str);
void reverseWord(char* str, int len);
void parseList(char *input);
void contains(char *str, char *target);
void pcopy(char *str, char letter);


/* Runs the main program. Prints the result of the specified function. */
int main(int argc, char *argv[])
{
  
  char func=*argv[argc-1]; //The function number.
   if (argc<3 ||  argc>4)
   {
     printf("Error: Invalid number of arguments given\n");
     return 0;
   }
     
   if (toInt(argv[argc-1]) == 1) //Convert To Integer
   {
     if (argc != 3)
     {
       printf("Error: Function 1 only takes 3 arguments\n");
       return 0;
     }
     printf("%d\n",toInt(argv[argc-2]));
   }
   else if (func=='2') //Convert To float
   {
     if (argc != 3)
     {
       printf("Error: Function 2 only takes 3 arguments\n");
       return 0;
     }
     printf("%f\n",toFloat(argv[argc-2]));
   }
   else if (func=='3') //Parse string for integers
   {
     if (argc != 3)
     {
       printf("Error: Function 3 only takes 3 arguments\n");
       return 0;
     }
     parseList(argv[argc-2]);
   }
   else if (func=='4') //Covert string to upper case
   {
     if (argc != 3)
     {
       printf("Error: Function 4 only takes 3 arguments\n");
       return 0;
     }
     toUpper(argv[argc-2]);
   }
   else if (func=='5') //Convert string to lower case
   {
     if (argc != 3)
     {
       printf("Error: Function 5 only takes 3 arguments");
       return 0;
     }
     toLower(argv[argc-2]);
   }
   else if (func=='6') //Reverse a string
   {
     if (argc != 3)
     {
       printf("Error: Function 6 only takes 3 arguments\n");
     }
     printf("%s\n",reverseString(argv[argc-2]));
   }
   else if (func=='7') //Check string to see if it's a palindrome
   {
     if (argc != 3)
     {
       printf("Error: Function 7 only takes 3 arguments\n");
       return 0;
     }
     isPalindrome(argv[argc-2]);
   }
   else if (func=='8') //Reverse list of words
   {
     if (argc != 3)
     {
       printf("Error: Function 8 only takes 3 arguments\n");
       return 0;
     }
     reverseSentence(argv[argc-2]);
   }
   else if (func=='9') //Subset check
   {
     if (argc != 4)
     {
       printf("Error: Function 9 only takes 4 arguments\n");
       return 0;
     }
     contains(argv[argc-3],argv[argc-2]);
   }
   else if (toInt(argv[argc-1]) == 10) //Partial Copy
   {
     if (argc != 4)
     {
       printf("Error: Function 10 only takes 4 arguments\n");
       return 0;
     }
     pcopy(argv[argc-3],*argv[argc-2]);
   }
   else
     printf("Error: Invalid function specified. Must be between 1-10\n");

   printf("\n");







  return 0;
}

/* Convert char[] to float */
int toInt(char input[])
{
  int i,num,neg;
  
  neg=0;
  num=0;
  i=0;

  if(input[0] == '-')
  {
      neg=1;
      i=1;
  }

  while(input[i]!='\0')
  {
    if (input[i]>='0' && input[i] <= '9')
      num=10*num+(input[i]-'0');
    else
      {
	printf("Error: Non numeric value given\n");
	exit(0);
      }
    i++;
  }
   
  if(neg==1)
    num=num*-1;

  return num;
}

/*Convert char[] to double*/
double toFloat( char input[] )
{
  int i, sign;
  double val, power;
  
  for( i = 0; isspace( input[i] ); ++i ){}

  if (input[i]=='-')
    {
      sign=-1;
      i++;
    }
  else if (input[i]=='+')
    {
      sign=1;
      i++;
    }
  else
    sign=1;
  
  for( val = 0.0;isdigit(input[i]); ++i )
  {
    val = (10.0*val) + (input[i]-'0');
  }

  if(input[i]=='.')
  {
    ++i;
  }

  for(power=1.0;isdigit(input[i]);++i)
    {
      val=(10.0*val)+(input[i]-'0');
      power *= 10.0;
    }

  return sign * val / power;
}

/*Convert string to uppercase*/
void toUpper(char *input)
{
  char result[100];
  int i=0;
  while(input[i] != '\0')
  {
    if((input[i]>=ASCII_a) && (input[i]<=ASCII_z)) 
      result[i] = input[i]-'a'+'A';  
    else
      result[i] = input[i]; 
     i++;
  } 

  printf("%s\n",result);
}

/*Convert String to lowercase*/
void toLower(char *input)
{
  char result[100];
  int i=0;
  while(input[i] != '\0')
  {
    if((input[i]>=ASCII_A) && (input[i]<=ASCII_Z)) 
      result[i] = input[i]+(ASCII_a-ASCII_A);
    else
      result[i] = input[i]; 
    i++;
  } 

  printf("%s\n",result);
}

/*Reverses a given string in it's entirety*/
char* reverseString(char *input)
{
  int i=0;
  int j=getLength(input)-1;
  while(input[i] != '\0')
  {
    result[i]=input[j];
    i++;
    j--;
  }
  result[i+1]='\0';
  return result;
  //printf("%s\n",result);
}

/*Returns the length of a character array*/
int getLength(char *input)
{
  int len = 0;
  while (*input != '\0')
  {
    input++;
    len++;
  }
  return len;
}

/*Checks if a given string is a palindrome*/
void isPalindrome(char *input)
{
  int i=0;
  char *reverse;
  reverse=reverseString(input);
  int palindrome=1;
  int j=0;
  while(input[i] !='\0')
  {
    if(input[i] != reverse[j])
    {
      palindrome=0;
      break;
    }
    j++;
    i++;
  }

  if(palindrome==1)
    printf("TRUE\n");
  else
    printf("FALSE\n");


}
/*Reverses the order of words in a sentence*/
void reverseSentence(char * str)
{  
  int i=0,j=0;
  reverseWord(str,getLength(str)); 
  while(*(str+j)!='\0')
  {
    if(*(str+j)==' ') 
      {
	reverseWord(str+i,j-i);
	i=j+1;
      }
      j++;
  }
  reverseWord(str+i,j-i);
  printf ("%s\n",str);
}

/*Reverses a word in an array*/
void reverseWord(char* str, int len)
{
  int i, j;
  char temp;
  i=j=temp=0;

  j=len-1;
  for (i=0; i<j; i++, j--)
    {
      temp=str[i];
      str[i]=str[j];
      str[j]=temp;
    }
}

/*Parses a list of characters for integers.
  Finds positive integers only. */
void parseList(char *input)
{
  int i=0;
  int j=0;
 while(input[i]!='\0')
  {
    if (input[i]>='0' && input[i] <= '9')
      {
        result[j]=input[i];
       	printf("%c",result[j]);
      }
    else
      {
       	printf(" ");
      }
    
    i++;
    j++;
    
  }
 result[j+1]='\0';
}

/*Checks if str contains target*/
void contains(char *str, char *target) 
{
  if (!*target)
    { printf("TRUE\n");
      return;
    }
  char *p1 = (char*)str;
  while (*p1) {
    char *p1Begin = p1, *p2 = (char*)target;
    while (*p1 && *p2 && *p1 == *p2) {
      p1++;
      p2++;
    }
    if (!*p2)
      {
	printf("TRUE\n");
	return;
      }
    p1 = p1Begin + 1;
  }
  printf("FALSE\n");
}

/*Prints the string start with the first
  occurance of letter*/
void pcopy(char *str, char letter)
{
  int i=0;
  int start=0;
  while (str[i]!='\0')
  {
    if(str[i]==(char)letter)
    {
      start=i;
      break;
    }
    i++;
  }

  while (str[i]!='\0')
  {
    printf("%c",str[i]);
    i++;
  }

  printf("\n");
}


