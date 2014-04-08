package proj3;

import java.util.Arrays;

/**
 * This is a generic max heap class. This class was adapted from the textbook, "Data Structures and Algorithms
 * in Java" by Mark Weiss.
 * 
 * @version 04/07/14
 * @author Megan Straub <mstraub1@umbc.edu>
 * CMSC 341 - Spring 2014 - Project 3
 * Section 4
 */
public class MaxHeap <T extends Comparable<? super T>>{
	
	/**
	 * The default number of nodes in the array for the heap.
	 */
	private static final int DEFAULT_SIZE = 10;
	
	/**
	 * The number of items in the actual array. This is for seeing if the array is full.
	 */
	private int size;
	
	/**
	 * The heap array.
	 */
	private T[] array;
	
	/**
	 * A constructor for MaxHeap that creates the heap on a default size.
	 */
	public MaxHeap(){
		this (DEFAULT_SIZE);
	}
	
	/**
	 * A second constructor for MaxHeap that creates the heap..
	 */
	public MaxHeap(int capacity){
		array = (T[]) new Comparable[capacity + 1];	
		size = 0;
	}
	
	/**
	 * This method inserts an item into the heap. If the heap is full the array is resized.
	 * If the heap is empty the item is inserted as the root. If the heap is not empty, the item
	 * is inserted at the end of the array and is then bubbled up to the correct location.
	 * 
	 * @param x the item to be inserted
	 */
	public void insert (T x){

		if(isFull())
			array = this.resize();
		
		int position;
		
		if(isEmpty()){
			position = 1;
			array[position] = x;
			size++;
		}else{

		//Moves the inserted item up the heap to the correct location
		position = ++size;
		for (; position > 1 && x.compareTo(array[position/2]) > 0; position /=2){
			array[position] = array[position/2];
		}
		
		array[position] = x;	
		}
	}
	
	/**
	 * This method returns the size of the heap.
	 * 
	 * @return size of the array
	 */
	public int getSize(){
		return size;
	}
	
	/**
	 * This method finds the maximum value of the heap.
	 * 
	 * @return the maximum value
	 */
	public T findMax(){
		if(isEmpty())
			return null;
		return array[1];
	}
	
	/**
	 * This method checks if the heap is empty.
	 * 
	 * @return true if the array is empty; false otherwise
	 */
	public boolean isEmpty(){
		return array[1] == null;
	}
	
	/**
	 * This method checks if the heap is full.
	 * 
	 * @return true if the array is full; false otherwise
	 */
	public boolean isFull(){
		return size == array.length -1;
	}
	
	/**
	 * This method prints the root of the heap.
	 */
	public void printHeapRoot(){
		System.out.println(findMax());
	}
	
	/**
	 * This method prints the entire heap.
	 */
	public void printHeap(){
		for(int i = 1; i <= size; i++)
			System.out.println(array[i]);
	}
	
	/**
	 * This method prints the first three values of the heap.
	 */
	public void printImmediateOptions(){
		for(int i = 1; i <= 3; i++){
			System.out.println(array[i]);
		}
	}
	
	/**
	 * This method is a custom toString method that formats the data of the heap.
	 * 
	 * @return the string to be printed
	 */
	public String toString(){
		String str = " ";
		for (int i = 1; i <= size; i++){
			str += "[" + (i) + "] " + array[i].toString() + "\n";
		}
		return str;
	}
	
	/**
	 * This is an internal method that resizes the array when the array becomes full.
	 * 
	 * @return the new resized array
	 */
	private T[] resize(){
		return Arrays.copyOf(array, array.length * 2);
	}
}