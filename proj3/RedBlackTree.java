/**
 * adapted from the book blah blah blah
 */
package proj3;

/**
 * @author Megan
 * 
 * constructor done
 * compare done
 * insert done
 * contains
 * reorient done
 * rotate done
 * printTree done
 * printRoot done
 *
 */
public class RedBlackTree <T extends Comparable<? super T>>{
	
	private RedBlackNode<T> pointer;
	private RedBlackNode<T> nullNode;

	private static final int BLACK = 1;
	private static final int RED = 0;
	
	private RedBlackNode<T> current;
	private RedBlackNode<T> parent;
	private RedBlackNode<T> grandParent;
	private RedBlackNode<T> greatGrandParent;
	
	public RedBlackTree(){
		
		nullNode = new RedBlackNode<T>(null);
		nullNode.left = nullNode.right = nullNode;
		
		pointer = new RedBlackNode<T>(null);
		pointer.left = pointer.right = nullNode;
	}
	
	//test if tree is empty
	public boolean isEmpty(){
		return pointer.right == nullNode;
	}
	
	//returns true if the thing is there
	public boolean contains(Partial x){		
		return findNode(x) != null;
	}
	
	//find stuff in the tree and returns it
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
	
	public T retreiveIfItContains(Partial x){
		return findNode(x);
	}
	
	//insert into the tree
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
	
	//reorients the tree if a node has 2 children
	//thing is the inserted item
	private void reorientTree(T thing){
		//color flip
		current.color = RED;
		current.left.color = BLACK;
		current.right.color = BLACK;
		
		if (parent.color == RED){ // need to rotate
			grandParent.color = RED;
			
			if ((compare(thing, grandParent) < 0) != (compare(thing, parent) < 0)){
				parent = rotate(thing, grandParent); //start double rotate
				current = rotate(thing, greatGrandParent);
				current.color = BLACK;
			}
			
			pointer.right.color = BLACK; // makes root black
		}
	}
	
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
	
	//compares data and x.data to see whichis bigger?
	private int compare(T thing, RedBlackNode<T> x){
		if (x == pointer)
			return 1;
		else
			return thing.compareTo(x.item);
	}
	
	//rotate tree with left child
	private RedBlackNode<T> rotateWithLeftChild(RedBlackNode<T> node2){
		RedBlackNode<T> node1 = node2.left;
		node2.left = node1.right;
		node1.right = node2;
		return node1;
	}
	
	//rotate tree with right child
	private RedBlackNode<T> rotateWithRightChild(RedBlackNode<T> node1){
		RedBlackNode<T> node2 = node1.right;
		node1.right = node2.left;
		node2.left = node2;
		return node2;
	}
	
	//print the tree in sorted order, in order
	public void printTree(){
		if(isEmpty())
			System.out.println("This tree has no nodes");
		else
			printTree(pointer.right);
	}
	
	//internal method to print subtree in inroder
	private void printTree(RedBlackNode<T> x){
		if(x != nullNode){
			printTree(x.left);
			System.out.println(x.item);
			printTree(x.right);
		}
	}
	
	//prints the root of the tree
	public void printRoot(){
		if(isEmpty())
			System.out.println("The tree is empty.");
		else
			System.out.println(pointer.right.item);
	}
	
	//prints the root of the tree
	public T getRoot(){
		if(isEmpty())
			return null;
		else
			return pointer.right.item;
	}
	
	private static class RedBlackNode<T>{
		
		T item; // data in the node
		RedBlackNode<T> left; //left chld
		RedBlackNode<T> right; //right child
		int color; // of node
		
		RedBlackNode(T theData){
			this(theData, null, null);
		}
		
		RedBlackNode(T theData, RedBlackNode<T> leftChild, RedBlackNode<T> rightChild){
			item = theData;
			left = leftChild;
			right = rightChild;
			color = BLACK;
		}
		
	}
}
