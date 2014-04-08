/*
 * diff checker says things are out of order in input 1
 * 
 * input 3 some heaps words are out of order, nodes 2/3 are swapped in heap MUST FIX ASAP
 * 
 * 
 * FIX:
 * indexes in max heap so they start at 1 instead of 0, go to page 231 to fix insert of binary heap
	
	DOCUMENTATIONS:
 * hashtedrbts DONE
 * rbt
 * maxheap
 * 
 */
package proj3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class creates a generic Red Black Tree that holds generic objects to be manipulated.
 * The class accepts functions such as: a constructor, reading the file, printing the results, and
 * printing at a certain location within the tree.
 * 
 * @version 04/07/14
 * @author Megan Straub <mstraub1@umbc.edu>
 * CMSC 341 - Spring 2014 - Project 3
 * Section 4
 */
public class HashedRBTs <T>{
	
	/**
	 * The ArrayList that holds the Red Black Tree objects.
	 */
	private ArrayList<RedBlackTree<Partial>> table;
	
	/**
	 * A constructor method for the HashedRBTs class.
	 * 
	 * @param size the size for the array list
	 */
	public HashedRBTs(int size){

		table = new ArrayList<RedBlackTree<Partial>>(size);
		
		for(int i = 0; i < size; i++){
			RedBlackTree<Partial> tree = new RedBlackTree<Partial>();
			table.add(i,tree);
		}
	}
	
	/**
	 * This reads the file, removes any unwanted characters from the file, and inserts objects
	 * of the Partial class into the specific red black tree. If there are duplicate classes of the 
	 * Partial class, the Node class (which is a part of the Partial class) is inserted into a max heap.
	 * 
	 * @param fileName the name of the file to be manipulated
	 */
	public void fileReader(String fileName){
		
		File textFile = new File(fileName);
		
		String str = " ";
		char letter = 'z';
		int index;
		int frequency;
		String word = " ";
		Partial p;
		Node n;
		
		try {
			Scanner scanFile = new Scanner(textFile); //reads the input file
			
			while (scanFile.hasNextLine()){
				str = scanFile.nextLine();
	
				str = str.replaceAll("[\\W]|frequency|word|Node|Empty tree", "");	//removes any unwanted characters
				
				if (str.length() > 0){
					
					letter = str.charAt(0);
					
					word = str.replaceAll("[0-9]|\\s", "");
					frequency = Integer.parseInt(str.replaceAll("[a-zA-Z]|\\s", ""));
				
					n = new Node(word, frequency);
					p = new Partial(n);
					
					//Inserting into the upper case letters; using ASCII characters.
					if(letter - 65 >= 0 && letter - 65 <= 25){		
						index = letter - 65;
						
						if(table.get(index).isEmpty()){	//inserts root node of the red black tree
							
							table.get(index).insert(p);
							
						}else if (!table.get(index).isEmpty() && table.get(index).contains(p)){ //checks for duplicates
							
							table.get(index).findNode(p).insertNodeIntoHeap(n);

						}else { //regular insertion into the red black tree
							
							table.get(index).insert(p);
						}

					}
					
					//Inserting into the lower case letters; using ASCII characters.
					if(letter - 97 >= 0 && letter - 97 <= 25){
						index = letter - 71;
						
						if(table.get(index).isEmpty()){ //inserts root node of the red black tree
							
							table.get(index).insert(p);
							
						}else if (!table.get(index).isEmpty() && table.get(index).contains(p)){ //checks for duplicates
							
							table.get(index).findNode(p).insertNodeIntoHeap(n);

						}else { //regular insertion into the red black tree
							
							table.get(index).insert(p);
						}
					}
				}				
			}
			scanFile.close();		
		}// end try
		catch (FileNotFoundException e){			
			System.out.println("File not found.");
			e.printStackTrace();
		}// end catch
	}
	
	/**
	 * This method retrieves a specific tree from a certain index in the array list.
	 * 
	 * @param index
	 * @return the tree at the specific index in the array list
	 */
	public RedBlackTree<Partial> retreiveHashedRBTat(int index){
		return table.get(index);
	}
	
	/**
	 * This method prints the results of the red black tree. Only roots, the heaps within those roots
	 * are printed. If a node doesn't contain any information the print statement merely says: 
	 * "This tree starts has no nodes". 
	 */
	public void printHashCountResults(){
		
		for(int i = 0; i < table.size(); i++){
			
			System.out.print("This tree starts ");
			
			if (table.get(i).getRoot() != null){
				
				System.out.print("with ");
				table.get(i).printRoot();
			}	
			else{
				System.out.println("has no nodes");
			}
		}
	}	
}
