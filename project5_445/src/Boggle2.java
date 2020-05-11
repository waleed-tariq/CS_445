import java.io.*;
import java.util.*;

// just generates all the strings & prints them as they are generated

public class Boggle2
{	
	static String[][] board;
	static long startTime,endTime; // for timing
	static final long MILLISEC_PER_SEC = 1000;
	static ArrayList<String> dict = new ArrayList<String>();
	static ArrayList<String> finalWord = new ArrayList<String>();
	static int size;

	public static void main( String args[] ) throws Exception
	{	startTime= System.currentTimeMillis();
		board = loadBoard( args[1] );
		
		Scanner s = new Scanner(new File(args[0]));
		while (s.hasNext()){
		    dict.add(s.nextLine());
		}
		s.close();
		Collections.sort(dict);
		size = dict.size();
		
		for (int row = 0; row < board.length; row++)
			for (int col = 0; col < board[row].length; col++)
				dfs( row, col, ""  ); // FOR EACH [R][C] THE WORD STARTS EMPTY
		System.out.println("we have left the dfs");
		
		String[] a = new String[finalWord.size()];
		a = finalWord.toArray(a);
		
		for (int i = 0; i < finalWord.size(); i++) {
			System.out.println(a[i]);
		}
	
		// EVENTUALLY YOU ADD HERE
		// PRINT OUT YOUR SORTED HITS CONTAINER ONE WORD PER LINE
		
		endTime =  System.currentTimeMillis(); // for timing
		System.out.println("GENERATION COMPLETED: runtime=" + (endTime-startTime)/MILLISEC_PER_SEC );
		
	} // END MAIN ----------------------------------------------------------------------------

	static void dfs( int r, int c, String word  )
	{	
		word += board[r][c];
	//	System.out.println( word ); // EVENTUALLY REPLACE WITH IF FOUND IN DICT ADD TO HITS CONTAINER
		if (!contains(dict, word)) {
			String unMarked = board[r][c];
			board[r][c]=unMarked;
		}
		
		int result = binarySearch(dict, word);
		
		if (result == -1) {} 
          
        else
            System.out.println("Element found at "
                              + "index " + result); 
		
		
		// NORTH IS [r-1][c]
		if ( r-1 >= 0 && board[r-1][c] != null )   
		{	String unMarked = board[r][c]; 
			board[r][c] = null;
			dfs( r-1, c, word );
			board[r][c] = unMarked;
		}
		
		// NE is [r-1][c+1]
		if ( (r-1) >= 0 && (c+1) < board[r].length && board[r-1][c+1] != null)
		{	String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r-1, c+1, word);
			board[r][c] = unMarked;
		}
		
		// E is [r][c+1]
		if (c+1 < board[r].length && board[r][c+1] != null)
		{	String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r, c+1, word);
			board[r][c] = unMarked;
		}
		
		// SE is [r+1][c+1]
		if (r+1 < board.length && c+1 < board[r].length && board[r+1][c+1] != null)
		{	String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r+1, c+1, word);
			board[r][c] = unMarked;
		}
		
		// S is [r+1][c]
		if (r+1 < board.length && board[r+1][c] != null)
		{	String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r+1, c, word);
			board[r][c] = unMarked;
		}
		
		// SW is [r+1][c-1]
		if (r+1 < board.length && c-1 >= 0 && board[r+1][c-1] != null)
		{	String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r+1, c-1, word);
			board[r][c] = unMarked;
		}
		
		// W is [r][c-1]
		if (c-1 >= 0 && board[r][c-1] != null)
		{	String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r, c-1, word);
			board[r][c] = unMarked;
		}
		
		// NW is [r-1][c-1]
		if (r-1 >=0 && c-1 >=0 && board[r-1][c-1] != null)
		{	String unMarked = board[r][c];
			board[r][c] = null;
			dfs( r-1, c-1, word);
			board[r][c] = unMarked;
		}
		
		
		
	} // END DFS ----------------------------------------------------------------------------

	//=======================================================================================
	static String[][] loadBoard( String fileName ) throws Exception
	{	Scanner infile = new Scanner( new File(fileName) );
		int rows = infile.nextInt();
		int cols = rows;
		String[][] board = new String[rows][cols];
		for (int r=0; r<rows; r++)
			for (int c=0; c<cols; c++)
				board[r][c] = infile.next();
		infile.close();
		return board;
	} //END LOADBOARD 
	
	public static boolean contains(ArrayList<String> dict, String word) {
		
		String[] a = new String[dict.size()];
		a = dict.toArray(a);
		
		for (int i = 0; i < dict.size(); i++) {
			if (a[i].startsWith(word)) {
				return true;
			}
		}
		
		return false;
	}
	
	static int binarySearch(ArrayList<String> dict, String word) 
    { 
		String[] a = new String[dict.size()];
		a = dict.toArray(a);
        int l = 0, r = a.length - 1; 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
  
            int res = word.compareTo(a[m]); 
  
            // Check if x is present at mid 
            if (res == 0) 
                return m; 
  
            // If x greater, ignore left half 
            if (res > 0) 
                l = m + 1; 
  
            // If x is smaller, ignore right half 
            else
                r = m - 1; 
        } 
  
        return -1; 
    } 
	
	
} // END BOGGLE CLASS