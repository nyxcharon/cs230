//package sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Barry Martin
 * CS111-04 
 * TA Corey Hinkle
 * Sudoku.java
 * 
 * Solves a 6x6 or 9x9 sudoku puzzle 
 */
public class Sudoku 
{
    static int[][] puzzle;
    static int row=0,col=0;
    
    public static void main(String[] args) 
    {
        System.out.println("Sudoku Solver.");
        System.out.println("This program will solve both 6x6 and 9x9 problems");
        System.out.println("If no arguments are specified, it will solve the"
                + " default 5star.txt 9x9 puzzle");
        try { Thread.sleep(3000);}//Give user time to read
        catch (InterruptedException ex) {}
        System.out.println("The files  that are included with program are:");
        System.out.println("1star.txt (9x9)");
        System.out.println("3star.txt (6x6)");
        System.out.println("5star.txt (9x9)");
        
        String filename="5star.txt";
        if(args.length ==1)
        {
            filename=args[0];
            System.out.println("Procedding to setup and solve "+filename);
        }
        else
        {
            System.out.println("No/Inavalid arguments; Using the default 5star.txt puzzle");
        }
        
        try { Thread.sleep(2000);}//Give user time to read
        catch (InterruptedException ex) {}
        System.out.println("Solving...");
         
         
        try { setupPuzzle(filename);} 
        catch (FileNotFoundException ex) 
        {
           System.out.println("Error: File not found!");
        }
     
        if(puzzle!=null)
        {
            if (solve(0,0,puzzle))   
                printPuzzle();
            else 
                System.out.println("I couldn't solve this...");
        }

        
    }
    
    /**
     * Method that finds the size of a puzzle and initializes it into the array
     * Pre-Condition: File must be setup correctly
     * @param filepath The file to use for the puzzle 
     * @throws FileNotFoundException 
     * Responses: Throws exception
     */
    public static void setupPuzzle(String filepath) throws FileNotFoundException
    {
        File file=new File(filepath);
        Scanner sc=new Scanner(file);
     
       //Get maze size and declare array
        while(sc.hasNextLine())
        {
           row++;
           String current=sc.nextLine();
           current=current.replaceAll(" ","");
           col=current.length();
        }
        if(!(row==6 || row==9) || !(col==6 || col==9))
        {
            System.out.println("Puzzle in incorrect format; exiting");
            System.exit(-1);
        }
            
        puzzle=new int[row][col];
        //re-setup scanner and put maze into array
        sc=new Scanner(file);
        int count=0;
        while(sc.hasNextLine())
        {
            for(int i=0;i<row;i++)
            {
               int curr=sc.nextInt();
               puzzle[count][i]=curr;
            }
            count++;
        } 
    }
    
    /**
     * Simple method to display a formatted puzzle
     */
    public static void printPuzzle()
    {
        String w="--";
        for(int i=0;i<=row;i++)
            w+="-";
        if(row==9)
            w+="-";
        System.out.println(w); 
        
        for(int j=0;j<row;j++)
        {
            if(row/3==2)
            {
                if (j==2||j==4)
                    System.out.println(w);
            }
            if(row/3==3)
            {
                if (j==3||j==6)
                    System.out.println(w);
                    
            }
            System.out.print("|");
            for(int k=0; k<col;k++)
            {
                if(row/3==2 && k==3)
                    System.out.print("|");
                if (row/3==3 )
                    if(k==3 || k==6)
                        System.out.print("|");
                System.out.print(puzzle[j][k]);
            }
            System.out.println("|");
        } 
        System.out.println(w);
    }
    
    /**
     * Recursive method to solve  the puzzle
     * @param r the current row value
     * @param c the current column value
     * @param puz the puzzles array
     * @return if the puzzle is solved
     */
    public static boolean solve(int r, int c, int[][] puz) 
    {
	if (r==row) 
        {
	    r=0;
	    if(c++==row-1) 
		return true; 
	}
	if (puz[r][c]!=0)  
	    return solve(r+1,c,puz);
	
	for (int i=1;i<=row;i++)
        {
	    if (validMove(r,c,i))
            {  
		puz[r][c] = i;       
		if (solve(r+1,c,puz))  
		    return true;
	    }
	}
	puz[r][c] = 0;
	return false;
    }
    
    /**
     * Method to determine if the number num can be placed at (r,c) in the array
     * @param r the row value
     * @param c the column value
     * @param num the number to check for
     * @return if num can be placed at (r,c) in the array
     */
    private static boolean validMove(int r, int c, int num) 
    {
        //Check across\\
        for(int i=0;i<row;i++)
        {
          if(puzzle[r][i]==num && puzzle[r][i]!=0)
           return false;
        }
        
        //Check Down\\
        for(int i=0;i<col;i++)
        {
          if(puzzle[i][c]==num  && puzzle[i][c]!=0)
           return false;
        }
        
        //Check box \\
        //Determine box column 
        int boxCol=0;
        if(c<3)
            boxCol=0;
        else if(c>=3 && c<=5)
            boxCol=3;
        else if(c>5)
            boxCol=6;
        //Determine box row
        int boxRow;
        if(row==6)
        {
        if(r>=4)
            boxRow=4;
        else if(r>=2 && r<=3)
            boxRow=2;
        else
            boxRow=0;
        }
        else
        {
            if(r>=6)
            boxRow=6;
        else if(r>=3 && r<=5)
            boxRow=3;
        else
            boxRow=0;
        }
        int rowSize=row/3;
        
        for(int i=boxRow;i<boxRow+rowSize;i++)
        {
            for(int j=boxCol;j<boxCol+3;j++)
            {
               if(puzzle[i][j]==num)
                   return false;
            }
        }
        return true;
    }
 
}
