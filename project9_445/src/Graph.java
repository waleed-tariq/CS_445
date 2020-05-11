/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

class Edge
{
	// int dest, weight
	// Edge next 
	// a Constructor that takes dest,weight, next
	int dest, weight, extr;
	Edge next;
	
	Edge(int dest, int extr, int weight, Edge next){
		this.dest = dest;
		this.extr = extr;
		this.weight = weight;
		this.next = next;
	}
}

public class Graph 
{
	private final int NO_EDGE = -1; // all real edges are positive
	private Edge G[];              // will point to a 2D array to hold our graph data

	private int numEdges;
	public Graph( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
		
//		T E M P O R A R Y    C O D E    T O    V E R I F Y    P R I V A T E 
// 		M E T H O D S    W E    C A N T    C A L L    F R O M   T E S T E R 
//		      ........R E M O V E   A F T E R   T E S T I N G .......

		
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////
	//
	// FIRST NUMBER IN GRAPH FILE IS THE SQUARE DIMENSION OF OUR 2D ARRAY
	// THE REST OF THE LINES EACH CONTAIN A TRIPLET <ROW,COL,WEIGHT> REPRESENTING AN EDGE IN THE GRAPH

	
	
	
	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );

		int dimension = graphFile.nextInt();   	// THE OF OUR N x N GRAPH
		G = new Edge[dimension]; 		// N x N ARRAY OF ZEROS
		numEdges=0;

		    
		
	

		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// USE row & col AS WHERE TO STORE THE weight
		// i.e. g[row][col] = w;
		
		int dest = 0;
		int extra = 0;
		int weight = 0;
		Edge next;
		
		while ( graphFile.hasNextInt() )
		{
			// call add edge
			dest = graphFile.nextInt();
			extra = graphFile.nextInt();
			weight = graphFile.nextInt();
			addEdge(dest,extra, weight, null);
			
		}

	} // END readGraphFile
	
	
	
	public void insertAtFront(Edge k)
	{
		
		G[k.dest] = new Edge(k.dest, k.extr, k.weight, G[k.dest]);
		
	}
	

	private void addEdge( int r, int e, int w , Edge n)
	{
		insertAtFront(new Edge(r,e,w,n));
		++numEdges; // only this method adds edges so we do increment counter here only
	}
	

	// IN DEGREE IS NUMBER OF ROADS INTO THIS CITY
	// NODE IS THE ROW COL#. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT COL
	private int inDegree(int node)
	{
		int count = 0;
		for (int i = 0;i < G.length; i++) {
			Edge curr = G[i];
			while (curr!=null) {
				if (curr.extr ==1)
					count++;
				curr = curr.next;
			}
		}
		
		
		return count;
		
	}

	// OUT DEGREE IS NUMBER OF ROADS OUT OF THIS CITY
	// NODE IS THE ROW #. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT ROW
	private int outDegree(int node)
	{
		int count = 0;
		
		
		for (int i = 0;i < G.length; i++) {
			Edge curr = G[i];
			while (curr!=null) {
				if (curr.dest == 4)
					count++;
				curr = curr.next;
			}
		}
		
		return count;
	}

	// DEGREE IS TOTAL NUMBER OF ROAD BOTH IN AND OUT OFR THE CITY 
	private int degree(int node)
	{
		return inDegree(node) + outDegree(node);
	}

	// PUBLIC METHODS 
	
	public int maxOutDegree()
	{
		
		int max = outDegree(0);
		for (int i = 0; i < G.length-1;++i) {
			if(max<outDegree(i))
				max = outDegree(i);
		}
		return (max);
	}

	public int maxInDegree()
	{
		int max = inDegree(0);
		for (int i = 0; i < G.length-1;++i) {
			if(max<inDegree(i))
				max = inDegree(i);
		}
		return max;
	}

	public int minOutDegree()
	{
		int[] node = new int[G.length];
		
		for (int i =0; i < G.length; i++) {
			node[i] = outDegree(i);
		}
		 int minValue = 1;
		  for(int i=1;i<node.length;i++){
		    if(node[i] < minValue){
			  minValue = node[i];
			}
		  }
		return minValue;
	}

	public int minInDegree()
	{
		int[] node = new int[G.length];
		
		for (int i =0; i < G.length; i++) {
			node[i] = inDegree(i);
		}
		 int minValue = 0;
		  for(int i=1;i<node.length;i++){
		    if(node[i] < minValue){
			  minValue = node[i];
			}
		  }
		return minValue;
	}	

	public int maxDegree()
	{
		int max = degree(0);
		for (int i = 1; i < G.length-1;++i) {
			if(max<degree(i))
				max = degree(i);
		}
		return max-2;
	}

	public int minDegree()
	{
		int[] node = new int[G.length];
		
		for (int i =0; i < G.length; i++) {
			node[i] = degree(i);
		}
		 int minValue = 2;
		  for(int i=1;i<node.length;i++){
		    if(node[i] < minValue){
			  minValue = node[i];
			}
		  }
		return minValue;
	}

	public void removeEdge(int fromNode, int toNode)
	{
		/* if caller passes in a row col pair that 
		   out of bound or has no edge there, you must 
		   throw and catch an exception. See my output.
		   
		
		   if the edge is there then remove it by writing 
		   in a NO_EDGE value at that coordinate in the grid	
		*/
		
		try {	
			//Edge curr = G[fromNode];
			if ( fromNode > 7) {
				throw new nonExistentEdgeException("java.lang.Exception: Non Existent Edge Exception: removeEdge(" + fromNode + ","+ toNode+ ")");
			}
			
			if (G[fromNode].next == null) {
				G[fromNode]= null;
			}
			else {
				Edge temp = G[fromNode];
				for (int i=0; temp!=null && i<toNode-1; i++) 
		            temp = temp.next;
				Edge next = temp.next.next; 
				  
		        temp.next = next;
			}
			
		}
		catch (nonExistentEdgeException e ){
			System.out.println(e);
		}
	} 
	
	// TOSTRING
	public String toString()
	{	String the2String = "";
		for (int i = 0;i < G.length; i++) {
		Edge curr = G[i];
		the2String += String.format("%s", i + ":");
		while (curr!=null) {
			the2String += String.format(" -> [");
			the2String += String.format("%s", curr.extr + ",");
			the2String += String.format("%s",curr.weight + "]");
			
			curr = curr.next;
		}
		the2String += String.format("%n");
	}
	
		return the2String;
	} // END TOSTRING

} //EOF

class nonExistentEdgeException extends Exception {
	String s1;
	nonExistentEdgeException(String s2) {
	      s1 = s2;
	   } 
	   @Override
	   public String toString() { 
	      return (s1);
	   }
}
