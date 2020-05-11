import java.io.*;
import java.util.*;

///////////////////////////////////////////////////////////////////////////////
class BSTNode<T>
{	T key;
	BSTNode<T> left,right;
	BSTNode( T key, BSTNode<T> left, BSTNode<T> right )
	{	this.key = key;
		this.left = left;
		this.right = right;
	}
}
///////////////////////////////////////////////////////////////////////////////////////
class Queue<T>
{	LinkedList<BSTNode<T>> queue;
	Queue() { queue =  new LinkedList<BSTNode<T>>(); }
	boolean empty() { return queue.size() == 0; }
	void enqueue( BSTNode<T>  node ) { queue.addLast( node ); }
	BSTNode<T>  dequeue() { return queue.removeFirst(); }
	// THROWS NO SUCH ELEMENT EXCEPTION IF Q EMPTY
}
////////////////////////////////////////////////////////////////////////////////
class BSTreeP7<T>
{
	private BSTNode<T> root;
	private boolean addAttemptWasDupe=false;
	@SuppressWarnings("unchecked")
	public BSTreeP7( String infileName ) throws Exception
	{
		root=null;
		Scanner infile = new Scanner( new File( infileName ) );
		while ( infile.hasNext() )
			add( (T) infile.next() ); // THIS CAST RPODUCES THE WARNING
		infile.close();
	}

	// DUPES BOUNCE OFF & RETURN FALSE ELSE INCR COUNT & RETURN TRUE
	@SuppressWarnings("unchecked")
	public boolean add( T key )
	{	addAttemptWasDupe=false;
		root = addHelper( this.root, key );
		return !addAttemptWasDupe;
	}
	@SuppressWarnings("unchecked")
	private BSTNode<T> addHelper( BSTNode<T> root, T key )
	{
		if (root == null) return new BSTNode<T>(key,null,null);
		int comp = ((Comparable)key).compareTo( root.key );
		if ( comp == 0 )
			{ addAttemptWasDupe=true; return root; }
		else if (comp < 0)
			root.left = addHelper( root.left, key );
		else
			root.right = addHelper( root.right, key );

		return root;
    } // END addHelper




	//////////////////////////////////////////////////////////////////////////////////////
	// # # # #   WRITE THE REMOVE METHOD AND ALL HELPERS / SUPPORTING METHODS   # # # # # 


	public int countNodes() 
	{
		return countNodes( this.root );
	}
	private int countNodes( BSTNode<T> root )
	{	
		if (root == null) return 0;
		return 1 + countNodes(root.left) + countNodes(root.right);
	}

	public void printInOrder()
	{	printInOrder( this.root );
		System.out.println();
	}
	private void printInOrder( BSTNode<T> root )
	{	
		BSTNode<T> curr = root;
		
		if (curr == null) {
			return;
		}
		else {
			printInOrder(curr.left);
			System.out.print(curr.key + " ");
			printInOrder(curr.right);
		}
	}

	public void printPreOrder()
	{	printPreOrder( this.root );
		System.out.println();
	}
	private void printPreOrder( BSTNode<T> root )
	{	
		System.out.print(root.key + " ");
		BSTNode<T> curr = root;
		if (curr.left != null) {
			printPreOrder(curr.left);
		}
		if (curr.right != null) {
			printPreOrder(curr.right);
		}
	}

	public void printPostOrder()
	{	printPostOrder( this.root );
		System.out.println();
		
	}
	private void printPostOrder( BSTNode<T> root )
	{	
		if (root == null) return; 
  
        printPostOrder(root.left); 
        printPostOrder(root.right); 
        System.out.print(root.key + " "); 
	}

	public void printLevelOrder()
	{	
		if (root == null) return;
		Queue<T> q = new Queue<T>();
		q.enqueue(this.root);
		
		while (!q.empty()) {
			BSTNode<T> curr = q.dequeue();
			System.out.print(curr.key + " ");
			if (curr.left != null) {
				q.enqueue(curr.left);
			}
			if (curr.right != null) {
				q.enqueue(curr.right);
			}
		}
		
	}
	public int countLevels()
	{	
		return countLevels( root ) + 1;
	}
	private int countLevels( BSTNode root)
	{	
		int countR = 0;
		int countL = 0;
		if (root == null || root.right == null && root.left == null) return 0;
		
		countR = 1 + countLevels(root.right);
		
		countL = 1 + countLevels(root.left);
		int max = Math.max(countR, countL);
		
		return max ;
	}
	public int[] calcLevelCounts()
	{	
		int levelCounts[] = new int[countLevels()];
		calcLevelCounts( root, levelCounts, 0 );
		return levelCounts;
	}
	private void calcLevelCounts( BSTNode root, int levelCounts[], int level )
	{
		if (root==null)return;
	    ++levelCounts[level];
	    calcLevelCounts( root.left, levelCounts, level+1 );
	    calcLevelCounts( root.right, levelCounts, level+1 );
		
	}
	
	
	
	// return true only if it finds/removes the node
	boolean removeAttemp;
	public boolean remove( T key2remove )
	{
		removeAttemp=false;
		root = removeHelper( this.root, key2remove );
		return !removeAttemp;
	}
	private BSTNode<T> removeHelper(BSTNode<T> root, T key){
		
		if (root == null) {
			removeAttemp = true;
            return null;
        }
		int comp = ((Comparable)key).compareTo( root.key );
        if (comp < 0) {
            root.left = removeHelper(root.left, key);
        } else if (comp > 0) {
            root.right = removeHelper(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                BSTNode<T> leftsRightmost = root.left; // relation order predecessor
                while (leftsRightmost.right != null) {
                    leftsRightmost = leftsRightmost.right;
                }
                leftsRightmost.right = root.right;
                return root.left;
            }
        }
        return root;
		
		
	}
	
  
} // END BSTREEP7 CLASS