package proj3;

/**
 * This is a generic red black tree used in the HastedRBTs class.
 * RedBlackTree contains a private class called RedBlackNode which is the nodes of the 
 * red black tree. These classes were adapted from the textbook, "Data Structures and Algorithms
 * in Java" by Mark Weiss.
 * 
 * @version 04/07/14
 * @author Megan Straub <mstraub1@umbc.edu>
 * CMSC 341 - Spring 2014 - Project 3
 * Section 4
 */
public class RedBlackTree <T extends Comparable<? super T>>{
	
	/**
	 * This points to the red black tree.
	 */
	private RedBlackNode<T> pointer;
	
	/**
	 * This is a null node used for inserting purposes and comparisons.
	 */
	private RedBlackNode<T> nullNode;
	
	/**
	 * The nodes value for being black.
	 */
	private static final int BLACK = 1;
	
	/**
	 * The nodes value for being red.
	 */
	private static final int RED = 0;
	
	/**
	 * This points to the current node.
	 */
	private RedBlackNode<T> current;
	
	/**
	 * This points to the parent of the current node.
	 */
	private RedBlackNode<T> parent;
	
	/**
	 * This points to the grand parent of the current node.
	 */
	private RedBlackNode<T> grandParent;
	
	/**
	 * This points to the great grand parent of the current node.
	 */
	private RedBlackNode<T> greatGrandParent;
	
	/**
	 * A constructor that creates the tree.
	 */
	public RedBlackTree(){
		
		nullNode = new RedBlackNode<T>(null);
		nullNode.left = nullNode.right = nullNode;
		
		pointer = new RedBlackNode<T>(null);
		pointer.left = pointer.right = nullNode;
	}
	
	/**
	 * This method determines if the red black tree is empty.
	 * 
	 * @return true if the tree is empty; false otherwise
	 */
	public boolean isEmpty(){
		return pointer.right == nullNode;
	}
	
	/**
	 * This method determines if a specific Partial object exists in
	 * the red black tree.
	 * 
	 * @param x the Partial object to be found
	 * @return true if the object was found; false otherwise
	 */
	public boolean contains(Partial x){		
		return findNode(x) != null;
	}
	
	/**
	 * This method finds a specific Partial object in the red black tree.
	 * @param x the Partial object to be found
	 * @return the found Partial object
	 */
	public T findNode(Partial x){
		
		nullNode.item = (T)x;
		current = pointer.right;
		
		for (;;){
			if (current == nullNode)
				return null;
			else if (x.compareTo(current.item) < 0)
				current = current.left;
			else if (x.compareTo(current.item) > 0)
				current = current.right;
			else
				return current.item;
		}
	}
	
	/**
	 * This method retrieves a specific Partial object in the red black tree.
	 * 
	 * @param x the Partial object to be found
	 * @return the found Partial object
	 */
	public T retreiveIfItContains(Partial x){
		return findNode(x);
	}
	
	/**
	 * This method inserts a node into the tree.
	 * 
	 * @param thing the object being inserted
	 */
	public void insert(T thing){
		current = parent = grandParent = pointer;
		nullNode.item = thing;
		
		while(compare(thing, current) != 0){
			greatGrandParent = grandParent;
			grandParent = parent;
			parent = current;
			
			current = compare(thing, current) < 0 ? current.left : current.right;
			
			//check if kids are red, then fix
			if(current.left.color == RED && current.right.color == RED)
				reorientTree(thing);
		}
		
		//insert fails if already there
		if (current != nullNode)
			return ;
		current = new RedBlackNode<> (thing, nullNode, nullNode);
		
		//attach to parent
		if (compare(thing, parent) < 0)
			parent.left = current;
		else
			parent.right = current;
		
		reorientTree(thing);
	}
		
	/**
	 * This is an internal method that reconfigures the tree so it is
	 * balanced with colors. This is called when a node has 2 red children.
	 * 
	 * @param thing the object being inserted
	 */
	private void reorientTree(T thing){
		//color flip
		current.color = RED;
		current.left.color = BLACK;
		current.right.color = BLACK;
		
		if (parent.color == RED){ // need to rotate
			grandParent.color = RED;
			
			if ((compare(thing, grandParent) < 0) != (compare(thing, parent) < 0))
				parent = rotate(thing, grandParent); //start double rotate
			
			current = rotate(thing, greatGrandParent);
			current.color = BLACK;
		}
		
		pointer.right.color = BLACK; // makes root black
	}
	
	/**
	 * This is an internal method that does a single or double rotation
	 * depending on the situation.
	 * 
	 * @param thing the object to be compared and rotated
	 * @param parent the parent node for the rotated subtree root
	 * @return the rotated subtree root
	 */
	private RedBlackNode<T> rotate(T thing, RedBlackNode<T> parent){
		if(compare(thing, parent) < 0){
			return parent.left = compare(thing, parent.left) < 0 ?
					rotateWithLeftChild (parent.left) :
					rotateWithRightChild (parent.left);
		}
		else {
			return parent.right = compare(thing, parent.right) < 0 ?
					rotateWithLeftChild(parent.right) :
					rotateWithRightChild(parent.right);
		}
	}
	
	/**
	 * This method compares two objects to see which is bigger.
	 * 
	 * @param thing the object to be compared
	 * @param x the node to compare the object with
	 * @return -1, 0, or 1 is returned; determines if one is bigger or not
	 */
	private int compare(T thing, RedBlackNode<T> x){
		if (x == pointer)
			return 1;
		else
			return thing.compareTo(x.item);
	}
	
	/**
	 * This is an internal method that rotates the tree with the left child.
	 * 
	 * @param node2 the node to rotate
	 * @return the rotated node
	 */
	private RedBlackNode<T> rotateWithLeftChild(RedBlackNode<T> node2){
		RedBlackNode<T> node1 = node2.left;
		node2.left = node1.right;
		node1.right = node2;
		return node1;
	}
	
	/**
	 * This is an internal method that rotates the tree with the right child.
	 * 
	 * @param node1 the node to rotate
	 * @return the rotated node
	 */
	private RedBlackNode<T> rotateWithRightChild(RedBlackNode<T> node1){
		RedBlackNode<T> node2 = node1.right;
		node1.right = node2.left;
		node2.left = node1;
		return node2;
	}
	
	/**
	 * This method prints the red black tree in sorted order.
	 */
	public void printTree(){
		if(isEmpty())
			System.out.println("This tree has no nodes");
		else{
			printTree(pointer.right);
		}
	}
	
	/**
	 * This is an internal method used to print the tree.
	 * 
	 * @param x the node being printed
	 */
	private void printTree(RedBlackNode<T> x){
		if(x != nullNode){
			printTree(x.left);
			
			String str = x.item.toString(); 
			
			str = str.replaceAll("\\n", " "); //removes unwanted characters
			
			String [] s = str.split(" "); //splits the string so it can be printed
			
			int count = 0; //tracks when to insert a new line
			
			System.out.println(s[0] + " " + s[1] + " --> The heaps contains: ");
			
			for (int i = 2; i < s.length; i++){
				System.out.print(s[i] + " ");
				count++;
				
				if(count == 4){
					System.out.print("\n");
					count = 0;
				}
			}
			
			System.out.print("\n");
			printTree(x.right);
		}
	}
	
	/**
	 * This method prints the root of the tree.
	 */
	public void printRoot(){
		if(isEmpty())
			System.out.println("The tree is empty.");
		else{
			
			String str = pointer.right.item.toString();
			
			str = str.replaceAll("\\n", " "); //removes unwanted characters

			String [] s = str.split(" "); //splits the string so it can be printed
			
			int count = 0; //tracks when to insert a new line
			
			System.out.println(s[0] + " " + s[1] + " --> The heaps contains: ");
			
			for (int i = 2; i < s.length; i++){
				System.out.print(s[i] + " ");
				count++;
				
				if(count == 4){
					System.out.print("\n");
					count = 0;
				}
			}
		}
	}
	
	/**
	 * This method gets the data in the root of the tree.
	 * 
	 * @return the data of the root; if there is no root null is returned
	 */
	public T getRoot(){
		if(isEmpty())
			return null;
		else
			return pointer.right.item;
	}
	
	/**
	 * A private class that deals with the nodes in the 
	 * generic red black tree.
	 */
	private static class RedBlackNode<T>{
		
		/**
		 * The data in the node.
		 */
		private T item;
		
		/**
		 * The left child of the node.
		 */
		RedBlackNode<T> left;
		
		/**
		 * The right child of the node.
		 */
		RedBlackNode<T> right;
		
		/**
		 * The color of the node.
		 */
		int color; 
		
		/**
		 * 
		 * @param theData the data in the node
		 */
		public RedBlackNode(T theData){
			this(theData, null, null);
		}
		
		/**
		 * A second constructor that sets the rest of the variables for a node.
		 * The nodes default color is set to black.
		 * 
		 * @param theData the data in the node
		 * @param leftChild the left child
		 * @param rightChild the right child
		 */
		public RedBlackNode(T theData, RedBlackNode<T> leftChild, RedBlackNode<T> rightChild){
			item = theData;
			left = leftChild;
			right = rightChild;
			color = BLACK;
		}	
	}
}