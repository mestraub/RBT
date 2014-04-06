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
	
	private RedBlackNode<T> root;
	private RedBlackNode<T> nullNode;
	
	private static final int BLACK = 1;
	private static final int RED = 0;
	
	private RedBlackNode<T> current;
	private RedBlackNode<T> parent;
	private RedBlackNode<T> grandParent;
	private RedBlackNode<T> greatGrandParent;
	
	public RedBlackTree(){
		nullNode = new RedBlackNode<T>(null);
		nullNode.leftChild = nullNode.rightChild = nullNode;
		root = new RedBlackNode<T>(null);
		root.leftChild = root.rightChild = nullNode;
	}
	
	//test if tree is empty
	public boolean isEmpty(){
		return root.rightChild == nullNode;
	}
	
	
/*
	public void contains(T x, Node n){
		System.out.println(" x " + x);
		System.out.println("root " + root.rightChild.data);
		
		if (root.rightChild != null && findNode(x) == x){
			Partial p = (Partial)findNode(x);
			p.insertNodeIntoHeap(n);
			System.out.println("in contains find Node area");
		} else{
			insert(x);
			System.out.println("in the else part of contains");
		}
		
	}
*/
	
	public boolean contains(T x){
		
		System.out.println("x in contains: before statements " + x);
		Partial p = (Partial) x;
		
		return root != nullNode && contains(p, root.rightChild);
	}

    private boolean contains(Partial x, RedBlackNode<T> n)
    {
        if (n != nullNode)
        {
            contains(x, n.leftChild);
            if(x.compareTo(n.data) == 0)
            {
                return true;
            }
            contains(x, n.rightChild);
        }
        return false;
    }
	
	public T findNode(T x){
		return findNode(x, root.rightChild);
	}

	private T findNode(T x, RedBlackNode<T> t){
		/*
		if (t ==  nullNode)
			return null;
		else if (compare (x , t) > 0)
			return findNode(x, t.leftChild);
		else if (compare(x, t) < 0)
			return findNode(x, t.rightChild);
		else
			return t.data;
			*/
		
		Partial p = (Partial)x;
		
		if (t != nullNode){
			findNode(x, t.leftChild);
			if (p.compareTo(x) == 0){
				return t.data;
			}
			findNode(x, t.rightChild);
		}
		return null;
	}
	/*
    private T findNode(T x, RedBlackNode<T> n)
    {
       // System.out.println(n.data);
        if(n != nullNode)
        {
            findNode(x, n.leftChild);
            if (compare(x, n) == 0)
            {
                return n.data;
            }
            findNode(x, n.rightChild);
        }
        return null;
    }
*/
	//insert into the tree
	public void insert(T thing){
		current = parent = grandParent = root;
		nullNode.data = thing;
		
		while(compare(thing, current) != 0){
			greatGrandParent = grandParent;
			grandParent = parent;
			parent = current;
			
			current = compare(thing, current) < 0 ? current.leftChild : current.rightChild;
			
			//check if kids are red, then fix
			if(current.leftChild.color == RED && current.rightChild.color == RED)
				reorientTree(thing);
		}
		
		//insert fails if already there
		if (current != nullNode)
			return ;
		current = new RedBlackNode<> (thing, nullNode, nullNode);
		
		//attach to parent
		if (compare(thing, parent) < 0)
			parent.leftChild = current;
		else
			parent.rightChild = current;
		
		reorientTree(thing);
	}
	
	//reorients the tree if a node has 2 children
	//thing is the inserted item
	private void reorientTree(T thing){
		//color flip
		current.color = RED;
		current.leftChild.color = BLACK;
		current.rightChild.color = BLACK;
		
		if (parent.color == RED){ // need to rotate
			grandParent.color = RED;
			
			if ((compare(thing, grandParent) < 0) != (compare(thing, parent) < 0)){
				parent = rotate(thing, grandParent); //start double rotate
				current = rotate(thing, greatGrandParent);
				current.color = BLACK;
			}
			
			root.rightChild.color = BLACK; // makes root black
		}
	}
	
	private RedBlackNode<T> rotate(T thing, RedBlackNode<T> parent){
		if(compare(thing, parent) < 0){
			return parent.leftChild = compare(thing, parent.leftChild) < 0 ?
					rotateWithLeftChild (parent.leftChild) :
					rotateWithRightChild (parent.leftChild);
		}
		else {
			return parent.rightChild = compare(thing, parent.rightChild) < 0 ?
					rotateWithLeftChild(parent.rightChild) :
					rotateWithRightChild(parent.rightChild);
		}
	}
	
	//compares data and x.data to see whichis bigger?
	private int compare(T thing, RedBlackNode<T> x){
		if (x == root)
			return 1;
		else
			return thing.compareTo(x.data);
	}
	
	//rotate tree with left child
	private RedBlackNode<T> rotateWithLeftChild(RedBlackNode<T> node2){
		RedBlackNode<T> node1 = node2.leftChild;
		node2.leftChild = node1.rightChild;
		node1.rightChild = node2;
		return node1;
	}
	
	//rotate tree with right child
	private RedBlackNode<T> rotateWithRightChild(RedBlackNode<T> node1){
		RedBlackNode<T> node2 = node1.rightChild;
		node1.rightChild = node2.leftChild;
		node2.leftChild = node2;
		return node2;
	}
	
	//print the tree in sorted order, in order
	public void printTree(){
		if(isEmpty())
			System.out.println("This tree has no nodes");
		else
			printTree(root.rightChild);
	}
	
	//internal method to print subtree in inroder
	private void printTree(RedBlackNode<T> x){
		if(x != nullNode){
			printTree(x.leftChild);
			System.out.println(x.data);
			printTree(x.rightChild);
		}
	}
	
	//prints the root of the tree
	public void printRoot(){
		if(isEmpty())
			System.out.print("The tree is empty.");
		else
			System.out.print(root.rightChild.data);
	}
	
	private static class RedBlackNode<T>{
		
		T data; // data in the node
		RedBlackNode<T> leftChild; //left chld
		RedBlackNode<T> rightChild; //right child
		int color; // of node
		
		RedBlackNode(){
			this(null, null, null);
		}
		
		RedBlackNode(T theData){
			this(theData, null, null);
		}
		
		RedBlackNode(T theData, RedBlackNode<T> leftChild, RedBlackNode<T> rightChild){
			data = theData;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			color = BLACK;
		}
		
		public T getData(){
			return data;
		}
		
	}
}
