/**
 * printHashCounts
 * fileReader
 * retreiveHashedRBTat
 * 
 */

/*
 * diff checker says things are out of order in input 1
 * lacking space so its [1] Node
 * 
 * input 3 some heaps words are out of order, nodes 2/3 are swapped in heap MUST FIX ASAP
 * 
 * 
 * FIX:
 * printing root
 * printing tree
 * 
 * WHAT WORKS:
 * immediate printing
 * 
 */
package proj3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Megan
 *
 */
public class HashedRBTs <T>{

	// create ArrayList called table
	private ArrayList<RedBlackTree<Partial>> table;
	/**
	 * A constructor that accepts any size for the hashed table.
	 * 
	 * @param size the size for the array list table
	 */
	public HashedRBTs(int size){
		//instantiate the table
		table = new ArrayList<RedBlackTree<Partial>>(size);
		
		for(int i = 0; i < size; i++){
			RedBlackTree<Partial> tree = new RedBlackTree<Partial>();
			table.add(i,tree);
		}
	}
	
	/**
	 * this will open the file, filters the useless text into distinct words, and use frequency, the place
	 * those words into the appropriate RBT in the arraylist table, then into the appropriate Partial
	 * then into a max heap.
	 * 
	 * should run in O(n) time
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
			Scanner scanFile = new Scanner(textFile); // reads the input file
			
			while (scanFile.hasNextLine()){
				str = scanFile.nextLine();
	
				str = str.replaceAll("[\\W]|frequency|word|Node|Empty tree", "");	//removes any unwanted characters
				
				if (str.length() > 0){
					
					letter = str.charAt(0);
					
					word = str.replaceAll("[0-9]|\\s", "");
					frequency = Integer.parseInt(str.replaceAll("[a-zA-Z]|\\s", ""));
				
					n = new Node(word, frequency);
					p = new Partial(n);

					if(letter - 65 >= 0 && letter - 65 <= 25){		
						index = letter - 65;
						
						if(table.get(index).isEmpty()){
							
							table.get(index).insert(p);
							
						}else if (!table.get(index).isEmpty() && table.get(index).contains(p)){
							
							table.get(index).findNode(p).insertNodeIntoHeap(n);

						}else {
							
							table.get(index).insert(p);
						}

					}
					
					if(letter - 97 >= 0 && letter - 97 <= 25){
						index = letter - 71;
						
						if(table.get(index).isEmpty()){
							
							table.get(index).insert(p);
							
						}else if (!table.get(index).isEmpty() && table.get(index).contains(p)){
							
							table.get(index).findNode(p).insertNodeIntoHeap(n);

						}else {
							
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
	 * returns the rbt at that index
	 * 
	 * @param index
	 */
	public RedBlackTree<Partial> retreiveHashedRBTat(int index){
		return table.get(index);
	}
	
	/**
	 * this is a grading and debugging tool; should run in O(n) time
	 */
	public void printHashCountResults(){
		
		for(int i = 0; i < table.size(); i++){
			
			if (table.get(i).getRoot() != null){
				
				System.out.print("This tree starts with ");
				table.get(i).printRoot();
				//table.get(i).printTree();
				/*
				String str = table.get(i).getRoot().toString();
				str = str.replaceAll("\\n", " ");
				
				//splits the string so it can be printed
				String [] s = str.split(" ");
				
				System.out.print("This tree starts with " + s[0] + " "+ s[1] + " --> The heap contains:\n");
				System.out.println(s[2] + " " + s[3] + " "+ s[4]);
				*/
			//	table.get(i).printTree();
			}	
			else
				System.out.println("This tree has no nodes");
		}
	}	
}
