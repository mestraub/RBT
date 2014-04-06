/**
 * printHashCounts
 * fileReader
 * retreiveHashedRBTat
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
	
				word = str.replaceAll("[0-9]|\\s", "");
				frequency = Integer.parseInt(str.replaceAll("[a-zA-Z]|\\s", ""));
				
				if (str.length() > 0){
					//letter = str.charAt(0);
					
					/*
					 * 90 and 65 are the ASCII characters of the upper case letters
					 * 90 minus 65 is 25 the letter Z
					 * 65 minus 65 is 0 the letter A
					 */					
					n = new Node(word, frequency);
					p = new Partial(n);
					
					System.out.println(" n node in file " + n + "\n");

					if(letter - 65 >= 0 && letter - 65 <= 25){		
						index = letter - 65;
						//n = new Node(str);
					//	table.get(index).insert(p);
						//if(table.get(index).contains(p))
						
						if(!table.get(index).isEmpty()){
							table.get(index).insert(p);
						}
						
						//table.get(index).contains(p, n);
					//	System.out.println(str);

					}
					
					if(letter - 97 >= 0 && letter - 97 <= 122){
						index = letter - 97;
						//System.out.println(str);
						
					//	n = new Node(word, frequency);
					//	p = new Partial(n);
						
						if(table.get(index).isEmpty()){
							
							table.get(index).insert(p);
							
							System.out.println("p partial in file " + p + "\n");
							System.out.println("p partial in file, getting word: " + p.getWord() + "\n");
							
						}else if (!table.get(index).isEmpty() && table.get(index).contains(p)){
							
							table.get(index).findNode(p).insertNodeIntoHeap(n);
							
							System.out.println("p partial in file contains " + p + "\n");
							System.out.println("p partial in file contains, getting word: " + p.getWord() + "\n");
						}else {
							
							table.get(index).insert(p);
							
							System.out.println("p partial in file else " + p + "\n");
							System.out.println("p partial in file else, getting word: " + p.getWord() + "\n");
						}
						//table.get(index).contains(p, n);
						//p.printImmediateOptions();
					//	table.get(index).insert(p);
					}
					
				}				
			}
			scanFile.close();		
		}// end try
		catch (FileNotFoundException e){			
			System.out.println("File not found.");
			e.printStackTrace();
		}// end catch
		/*
		for(int i = 0; i < table.size(); i++)
			table.get(i).printTree();
			*/
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
		/*
		for (int i = 0; i < table.size(); i++){
			System.out.print("This tree starts with ");
			table.get(i).printRoot();
			System.out.println("");
		}
		*/
		/*
		for(int i = 0; i < table.size(); i++){
			//System.out.println(i);
			System.out.println(i);
			//table.get(i).printRoot();
			//System.out.println("");
			table.get(i).printTree();
		}
		*/
		System.out.println(25);
		table.get(25).printTree();
	}
	
	
}
