/*
 * Copyright (c) 2013, Erika Nana
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of Project 1 nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY Erika Nana ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Erika Nana BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/** 
 * Allows the user to run tests on the three data structures at one time.
 * Also supports individual operations on the data structures.
 * 
 * @author Erika Nana
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class UserDriver {
	
	/** Driver to run the program. */
	static String[] randomValues = new String[10]; 
	
	/** The array. */
	static String[] array;
	
	/** The input reader. */
	static Scanner inputReader = new Scanner(System.in);
	
	/** The size. */
	static int size;
	
	/** Colors for output in the console*/
	public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";

	
	/**
	 * The user interface.
	 *
	 * @param args Not used
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String key;
		DynamicSet<String> bst = new BTree<String>();
		DynamicSet<String> dll = new DLinkedList<String>();
		DynamicSet<String> skip = new SkipList<String>();
		
		while (true) {
			String loadStatus = loadFile();
			if (loadStatus.equals("quit")) {
				return;
			}
			else if (loadStatus.equals("error")) {
				continue;
			}
			else {
				while (true) {
				System.out.println(PURPLE + "\nWhat would you like to do?");
				System.out.println(PURPLE + "Options available:  runtest, insert, search, delete, pred, " +
								  "succ, min, max, loadNew, quit" + RESET);
				
				String choice = inputReader.nextLine();
				if (!Utils.isEnum(choice)) {
					System.out.println(RED + "That command is not recognized");
					continue;
				}
				if (Utils.Command.valueOf(choice) == Utils.Command.quit) {
					break;
				}
				switch(Utils.Command.valueOf(choice)) {
					case delete:
						if (bst.size() == 0) {
							System.out.println(RED + "The ADT's are emtpy!");
							break;
						}
						System.out.println(BLUE + "Please type in the key:  " + RESET);
						key = inputReader.nextLine();
						bst.delete(key);
						dll.delete(key);
						skip.delete(key);
						System.out.println("if successful,deleted:  " + key);
						break;
					case insert:
						System.out.println(BLUE + "Please type in the key:  " + RESET);
						key = inputReader.nextLine();
						bst.insert(key,null);
						dll.insert(key,null);
						skip.insert(key,null);
						break;
					case max:
						if (bst.size() == 0) {
							System.out.println(RED + "The ADT's are emtpy!");
							break;
						}
						System.out.println(GREEN + "maximum of binary search tree:  " + ((BNode<String>)bst.maximum()).getKey()+ RESET);
						System.out.println(GREEN + "maximum of doubly-linked list:  " + ((DNode<String>)dll.maximum()).getValue() + RESET);
						System.out.println(GREEN + "maximum of skip list:  " + ((SkipListEntry<String>)skip.maximum()) + RESET);
						break;
					case min:
						if (bst.size() == 0) {
							System.out.println(RED + "The ADT's are emtpy!");
							break;
						}
						System.out.println(GREEN + "minimum of binary search tree:  " + ((BNode<String>)bst.minimum()).getKey()+ RESET);
						System.out.println(GREEN + "minimum of doubly-linked list:  " + ((DNode<String>)dll.minimum()).getValue() + RESET);
						System.out.println(GREEN + "minimum of skip list:  " + ((SkipListEntry<String>)skip.minimum()) + RESET);
						break;
					case pred:
						if (bst.size() == 0) {
							System.out.println(RED + "The ADT's are emtpy! is empty!");
							break;
						}
						System.out.println(BLUE + "Please type in the key:  " + RESET);
						key = inputReader.nextLine();
						Object node1 = bst.predecessor(key);
						if (node1 == null) { //if there is none for bst, there shouldn't be any for the others
							System.out.println(RED + "The key has no predecessor.");
							 break;
						}
						else {
							System.out.println(GREEN + "Predecessor of binary search tree:  " + ((BNode<String>)bst.predecessor(key)).getKey());
							System.out.println(GREEN + "Predecessor of doubly-linked list:  " + ((DNode<String>)dll.predecessor(key)).getValue());
							System.out.println(GREEN + "Predecessor of skip list:  " + ((SkipListEntry<String>)skip.predecessor(key)));
						}
						break;
					case runtest:
						runTest();
						break;
					case search:
						System.out.println(BLUE + "Please type in the key:  " + RESET);
						key = inputReader.nextLine();
						if (bst.size() == 0) {
							System.out.println(RED + "The ADT's are emtpy!");
							break;
						}
						Object search = bst.search(key);
						if (search == null) { //if there is none for bst, there shouldn't be any for the others
							System.out.println(RED + "The key does not exist");
							 break;
						}
						else {
							System.out.println(GREEN + "Found key:  " + ((BNode<String>)bst.search(key)).getKey() + " in binary search tree");
							System.out.println(GREEN + "Found key:  " + ((DNode<String>)dll.search(key)).getValue() + " in doubly-linked list");
							System.out.println(GREEN + "Found key:  " + ((SkipListEntry<String>)skip.search(key))+ " in skip list");
						}
						break;
					case succ:
						if (bst.size() == 0) {
							System.out.println(RED + "The ADT's are emtpy!");
							break;
						}
						System.out.println(BLUE + "Please type in the key:  " + RESET);
						key = inputReader.nextLine();
						Object node = bst.successor(key);
						if (node == null) { //if there is none for bst, there shouldn't be any for the others
							System.out.println(RED + "The key has no successor.");
							 break;
						}
						else {
							System.out.println(GREEN + "Successor of binary search tree:  " + ((BNode<String>)bst.successor(key)).getKey());
							System.out.println(GREEN + "Successor of doubly-linked list:  " + ((DNode<String>)dll.successor(key)).getValue());
							System.out.println(GREEN + "Successor of skip list:  " + ((SkipListEntry<String>)skip.successor(key)));
						}
						break;
					case loadNew:
						loadStatus = loadFile();
						if (loadStatus.equals("quit")) {
							return;
						}
						else if (loadStatus.equals("error")) {
							break;
						}
						break;
					default:
						break;
				}
			}}
		}
	}
	
	/**
	 * Prints out the table of results
	 */
	public static void runTest() {
		
		DynamicSet<String> set = new DLinkedList<String>();
		System.out.printf("size:  %-7d\n", size);
		Utils.printDivider();
		System.out.println("               | Insert                     " +
				"| Search                     " +
				"| Predecessor                " +
				"| Successor                  " +
				"| Minimum  " +
				"| Maximum  |");
		Utils.printDivider();
		System.out.print("Linked List    | ");
		Utils.printRow(set, array, randomValues);
		
		set = new SkipList<String>();
		System.out.print("Skip List      | ");
		Utils.printRow(set, array, randomValues);
		
		set = new BTree<String>();
		System.out.print("Binary Tree    | ");
		Utils.printRow(set, array, randomValues);
	}
	
	/**
	 * Reads in the file and generates the input array and the array of
	 * random values.
	 *
	 * @return The status of the driver
	 */
	public static String loadFile() {
		String fileName;			
		System.out.println(PURPLE + "\nPlease type in file name to read, or quit to exit:" + RESET);
		fileName = inputReader.nextLine();
		//check if file exists
		if (fileName.equals("quit")) {
			System.out.println(GREEN + "Goodbye!" + RESET);
			inputReader.close();
			return "quit";
		}
		File file = new File(fileName);
		int index = 0;
		try {
			//create an array based on the file name
			if (fileName.matches(".*100\\.txt")) {
				size = 100;
				array = new String[100];
			}
			else if(fileName.matches(".*1000\\.txt")) {
				size = 1000;
				array = new String[1000];
			}
			else if(fileName.matches(".*10000\\.txt")) {
				size = 10000;
				array = new String[10000];
			}
			else if(fileName.matches(".*100000\\.txt")) {
				size = 100000;
				array = new String[100000];
			}
			else if(fileName.matches(".*1000000\\.txt")) {
				size = 1000000;
				array = new String[1000000];
			}
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			//read file in line by line
			Scanner fileReader = new Scanner(br);
			fileReader.useDelimiter("(\n)");
			
			while (fileReader.hasNext()) {
				array[index] = fileReader.next();
				index++;
			}
			fileReader.close();
			//generate an array of random strings
			for (int i = 0; i < 10; i++) {
				int random = new Random().nextInt(array.length);
				randomValues[i] = array[random];
			}
			return "continue";
		}
		catch (FileNotFoundException e) {
			System.out.println(RED + "File not found.  Please try again.");
			return "error";
		}
	}
}
