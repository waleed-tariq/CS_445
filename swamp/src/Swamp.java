import java.io.*;
import java.util.*;

// DO NOT!! IMPORT JAVA.LANG

public class Swamp
{
	static int[][] swamp;  // NOW YOU DON'T HAVE PASS THE REF IN/OUT METHODS

 	public static void main(String[] args) throws Exception
	{
		int[] dropInPt = new int[2]; // row and col will be on the 2nd line of input file;
		swamp = loadSwamp( args[0], dropInPt );
		int row=dropInPt[0], col = dropInPt[1];
		String path = ""; // with each step grows to => "[2,3][3,4][3,5][4,6]" etc
		dfs( row, col, path );
	} // END MAIN

 	// JUST FOR YOUR DEBUGGING - DELETE THIS METHOD AND ITS CALL BEFORE HANDIN
	// ----------------------------------------------------------------
	private static void printSwamp(String label, int[][] swamp )
	{
		System.out.println( label );

	}
	// --YOU-- WRITE THIS METHOD (LOOK AT PRINTSWAMP FOR CLUES)
   	// ----------------------------------------------------------------
	private static int[][] loadSwamp( String infileName, int[] dropInPt  ) throws Exception
	{
		// OPEN UP A SCANNER USING THE INCOMING FILENAME
		// THE FIRST NUMBER ON THE FIRST LINE WILL BE THE NUMBER OF ROWS & COLS
		// THE SECOND & THIRD NUMBER ON 1st LINE WILL BE THE DROP IN POINT X,Y
		// STORE SEOND NUMBER INTO dropInPt[0] THIRD # INTO dropInPt[1]
		// USING ROW, COL DEFINE A 2D ARRAY OF INT
		// USE A NESTED LOOP. OUTER LOOP ROWS, INNER LOOP COLS
		// READ IN THE GRID OF VALUES FROM THE INPUT FILE
		// CLOSE THE SCANNER
		// RETURN THE 2D ARRAY WITH VALUES LOADED INTO IT
		Scanner keyboard = new Scanner(new File(infileName));
		int rows = keyboard.nextInt();
		int columns = rows;
		dropInPt[0] = keyboard.nextInt();
		dropInPt[1] = keyboard.nextInt();
		int[][] swamp = new int[rows][columns]; 
		for(int r = 0; r < rows ; r++)
		{
			 for(int c = 0; c < columns; c++)
			 {
			 	swamp[r][c] = keyboard.nextInt();
			 }
		}
		keyboard.close();
		return swamp;

		//return null;  // JUST TO MAKE IT COMPILE
	}

	static void dfs( int row, int col, String path ) // dfs = DEPTH FIRST SEARCH
	{
		// IMPLEMENT THE DFS ALGORITHM IN HERE
		path += "["+row+","+col+"]";
		
		if(((row==0)||(row==swamp.length-1)) || ((col==0)||(col==swamp.length-1)))
		{
			printSwamp(path, swamp);
			path += "\n";
			return;
		}
		if(swamp[row-1][col] == 1)
		{
			swamp[row][col] = 0;
			dfs( row-1, col, path);
			swamp[row-1][col] = 1;
		}
		if (swamp[row-1][col+1] == 1)
		{
			swamp[row][col] = 0;
			dfs( row-1, col+1, path );
			swamp[row-1][col+1] = 1;
		}
		if (swamp[row][col+1] == 1)
		{
			swamp[row][col] = 0;
			dfs( row, col+1, path );
			swamp[row][col+1] = 1;
		}
		if (swamp[row+1][col+1] == 1)
		{
			swamp[row][col] = 0;
			dfs( row+1, col+1, path );
			swamp[row+1][col+1] = 1;
		}
		if (swamp[row+1][col] == 1)
		{
			swamp[row][col] = 0;
			dfs( row+1, col, path );
			swamp[row+1][col] = 1;
		}
		if (swamp[row+1][col-1] == 1)
		{
			swamp[row][col] = 0;
			dfs( row+1, col-1, path );
			swamp[row+1][col-1] = 1;
		}
		if (swamp[row][col-1] == 1)
		{
			swamp[row][col] = 0;
			dfs( row, col-1, path );
			swamp[row][col-1] = 1;
		}
		if (swamp[row-1][col-1] == 1)
		{
			swamp[row][col] = 0;
			dfs( row-1, col-1, path );
			swamp[row-1][col-1] = 1;
		}
	}	
}