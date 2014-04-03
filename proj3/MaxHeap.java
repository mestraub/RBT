/**
 * this is adapted from the text book blah blah
 * changed to work for my liking
 */
package proj3;

import java.util.Arrays;

/**
 * @author Megan
 *
 */
public class MaxHeap <AnyType extends Comparable<? super AnyType>>{
	
	private static final int DEFAULT_SIZE = 10; // default number of nodes in da heap
	private int size; // number of elements in heap
	private AnyType[] array; // the heap array
	
	/**
	 * construct the binary heap with a default size.
	 */
	public MaxHeap(){
		this (DEFAULT_SIZE);
	}
	
	/**
	 * construct binary heap.
	 */
	public MaxHeap(int capacity){
		array = (AnyType[]) new Comparable[capacity + 1];	
		size = 0;
	}

	public void insert (AnyType x){
		// will resize the array if needed
		if(isFull())
			array = this.resize();
		int position;
		
		if(isEmpty()){
			position = size;
			array[position] = x;
		}else{

		//move up
		size++;
		position = size;
		for (; position > 0 && x.compareTo(array[position/2]) > 0; position /=2)
			array[position] = array[position/2];
		
		array[position] = x;
		}
	}
	
	// return size of the heap
	public int getSize(){
		return size;
	}
	
	//takes the root since this is a max heap
	public AnyType findMax(){
		if(isEmpty())
			return null;
		return array[0];
	}
	
	//delete max from the heap
	public AnyType deleteMax(){
		if(isEmpty())
			return null;
		
		AnyType maxItem = findMax();
		array[0] = array[size--];
		bubbleDown(0);
		
		return maxItem;
	}
	
	//checks if heap is empty
	public boolean isEmpty(){
		return array[0] == null;
	}
	
	//checks if heap is full
	public boolean isFull(){
		return size == array.length -1;
	}
	
	//prints root of heap
	public void printHeapRoot(){
		System.out.println(findMax());
	}
	
	//prints the entire heap
	public void printHeap(){
		for(int i = 0; i <= size; i++)
			System.out.println(array[i]);
	}
	
	//prints first three values in heap
	public void printImmediateOptions(){
		for(int i = 0; i < 3; i++){
			System.out.println(array[i]);
		}
	}
	
	//custom toString to print heap in an array
	public String toString(){
		return Arrays.toString(array);
	}
	
	//moves down the heap
	private void bubbleDown(int position){
		int child = 0;
		
		AnyType temp = array[position];
		
		for(; position * 2 < size; position = child){
			child = position * 2;
			
			if(child != size && array[child+1].compareTo(array[child]) > 0 )
				child++;
			if(array[child].compareTo(temp) > 0)
				array[position] = array[child];
			else
				;
		}	
		array[position] = temp;
	}
	
	/*
	private void bubbleUp(){
		int position = this.size;	
	}
	*/
	
	// resizes the array
	private AnyType[] resize(){
		return Arrays.copyOf(array, array.length * 2);
	}
}

/*
//position of the parent
public int parent(int position){
	return (position - 1)/2;
}

//position of left child
public int leftChild(int position){
	return 2*position + 1;
}

//position of right child
public int rightChild (int position){
	return 2*position + 2;
}
*/

