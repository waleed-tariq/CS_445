import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T>
{
	private CDLL_Node<T> head;  // pointer to the front (first) element of the list
	private int count=0;
	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See executeRitual() method 
	public CDLL_JosephusList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE
	
	public CDLL_JosephusList( String infileName ) throws Exception
	{
		BufferedReader infile = new BufferedReader( new FileReader( infileName ) );	
		while ( infile.ready() )
		{	@SuppressWarnings("unchecked") 
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail( data ); 
		}
		infile.close();
	}
	

	
	// ########################## Y O U   W R I T E / F I L L   I N   T H E S E   M E T H O D S ########################
	
	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		CDLL_Node<T> scout = head;
		CDLL_Node<T> newNode = new CDLL_Node<T>(data);
		newNode.setPrev(newNode);
		newNode.setNext(newNode);
		
		if (head == null) {
			head = newNode;
		
		}
		else {
			scout = head.getPrev();
			scout.setNext(newNode);
			newNode.setPrev(scout);
			head.setPrev(newNode);
			newNode.setNext(head);
		}
		
		count++;
	}

	
	public int size()
	{	
		return count;
	}
	
	// RETURN REF TO THE FIRST NODE CONTAINING  KEY. ELSE RETURN NULL
	public CDLL_Node<T> search( T key )
	{	
		
		CDLL_Node<T> curr = head;
        while (curr != null) 
        { 
            if (curr.getData().equals(key)) 
                return curr;    
            curr = curr.getNext(); 
        } 
        return null;
		
	}
	
	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString()
	{
		CDLL_Node<T> newNode = head;
		String toString = "";
		
		for (newNode = head; !head.equals(newNode.getNext()); newNode = newNode.getNext())
		{
			toString += newNode.getData();
			if (!newNode.getNext().equals(head))
				toString += "<=>";
		}

		return toString + newNode;
		
	}
	
	void removeNode( CDLL_Node<T> deadNode )
	{
		// MUST CHCK FOR NULL HEAD OR NULL DEADNODE!!!!!!!
		if (head == null || deadNode == null) {
			return;
		}
		CDLL_Node<T> curr = deadNode;
		
		curr.getPrev().setNext(curr.getNext());
		curr.getNext().setPrev(curr.getPrev());
		count--;
		
	}
	
	public void executeRitual( T first2Bdeleted, int skipCount )
	{
		
		if (size() < 1 ) return;
		CDLL_Node<T> curr = search( first2Bdeleted );
		System.out.println(curr);
		if ( curr==null ) return;
		
		// OK THERE ARE AT LEAST 2 NODES AND CURR IS SITING ON first2Bdeleted
		do
		{
			CDLL_Node<T> deadNode = curr;
			T deadName = deadNode.getData();
			
			System.out.println("Stopping at " +deadName+ " to delete " +deadName);
			
			if (skipCount < 0)
				curr = deadNode.getPrev();
			else
				curr = deadNode.getNext();
			
			
			if (head == deadNode) {
				head = curr;
			}
			
			// BEFORE YOU REMOVE NODE. YOU HAVE BOTH CURR AND DEADNODE POINTING AT THE NODE
			// ASSIGN CURR TO EITHER DENONDE NEXT OR PREV DEPENDING ON WHICH DIRECTION WE R GOING
			// NOW 1 MORE BIG THING - IF HEAD IS POININIG AT HE DEADNODE THEN SET HEAD = CURR
			// NOW YOU MAY REMOVE NODE  WITH HAED AND CURR SAFELY POINTING AN NON-DEADNODES 
			
			removeNode(deadNode);
			
			System.out.println("deleted. list now: " + toString());
			
			if (size() == 1) return;

	
			if (skipCount < 0) {
				System.out.println("resuming at " + curr + ", skipping " + curr + " + " + (skipCount * (-1) - 1) + " nodes COUNTER_CLOCKWISE after");
			}
			else {
				System.out.println("resuming at " + curr + ", skipping " + curr + " + " + (skipCount - 1) + " nodes CLOCKWISE after");
			}
			for (int i = 1; i <= Math.abs(skipCount); i ++) {
				if (skipCount < 0) {
					curr = curr.getPrev();
				}
				else {

					curr = curr.getNext();
				}
			}
	
			
	
		} while ( size() > 1 );
		
	}
	
} // END CDLL_LIST CLASS