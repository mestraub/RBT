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
public class MaxHeap <T extends Comparable<? super T>>{
	
	private static final int DEFAULT_SIZE = 10; // default number of nodes in da heap
	private int size; // number of elements in heap
	private T[] array; // the heap array
	
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
		array = (T[]) new Comparable[capacity];	
		size = 0;
	}

	public void insert (T x){
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
	public T findMax(){
		if(isEmpty())
			return null;
		return array[0];
	}
	
	//delete max from the heap
	public T deleteMax(){
		if(isEmpty())
			return null;
		
		T maxItem = findMax();
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
	//seems okay
	public void printImmediateOptions(){
		for(int i = 0; i < 3; i++){
			System.out.println(array[i]);
		}
	}
	
	//custom toString to print heap in an array
	/*
	public String toString(){
		
		String str = "\n";
		
		for (int i = 0; i < size; i++){
			str += "[" + (i + 1) + "]" + array[i].toString() + "\n";
		}
		return str;
	}*/
	public String toString(){
		return Arrays.toString(array);
	}
	
	//moves down the heap
	private void bubbleDown(int position){
		int child = 0;
		
		T temp = array[position];
		
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
	
	// resizes the array
	private T[] resize(){
		return Arrays.copyOf(array, array.length * 2);
	}
}