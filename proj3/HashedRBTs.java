/**
 * 
 */
package proj3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Megan
 *
 */
public class HashedRBTs <AnyType>{

	// create ArrayList called table
	
	/**
	 * A constructor that accepts any size for the hashed table.
	 * 
	 * @param size the size for the array list table
	 */
	public HashedRBTs(int size){
		//instantiate the table
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
		
		try {
			Scanner scanFile = new Scanner(textFile); // reads the input file
			
			while (scanFile.hasNext()){
				str = scanFile.next();
	
				str = str.replaceAll("\\W|[0-9]", "");	//removes any unwanted characters
				
				if (str.length() > 0){
					letter = str.charAt(0);
					
					/*
					 * 90 and 65 are the ASCII characters of the upper case letters
					 * 90 minus 65 is 25 the letter Z
					 * 65 minus 65 is 0 the letter A
					 */					
					
					if(letter - 65 >= 0 && letter - 65 <= 25){		
						index = letter - 65;
						//n = new Node(str);
						//table.get(index).insert(n);
					}
					
					if(letter - 97 >= 0 && letter - 97 <= 122){
						index = letter - 97;
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
	public void retreiveHashedRBTat(int index){
		
	}
	
	/**
	 * this is a grading and debugging tool; should run in O(n) time
	 */
	public void printHashCountResults(){
		
	}
	
	
}
