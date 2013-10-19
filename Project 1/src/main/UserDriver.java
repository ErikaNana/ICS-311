package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

import skip.SkipList;
import skip.SkipListEntry;
import utils.Utils;
import bst.BNode;
import bst.BTree;
import dll.DLinkedList;
import dll.DNode;

public class UserDriver {
	/**
	 * Driver to run the program
	 */
	static String[] randomValues = new String[10]; 
	static String[] array;
	static Scanner inputReader = new Scanner(System.in);
	
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
				System.out.println("\nWhat would you like to do?");
				System.out.println("Options available:");
				System.out.println("runtest, insert, search, delete, pred, succ, min, max, loadNew, quit");
				
				String choice = inputReader.nextLine();
				if (!Utils.isEnum(choice)) {
					System.out.println("That command is not recognized");
					continue;
				}
				if (Utils.Command.valueOf(choice) == Utils.Command.quit) {
					break;
				}
				switch(Utils.Command.valueOf(choice)) {
					case delete:
						if (bst.size() == 0) {
							System.out.println("The ADT's are emtpy!");
							break;
						}
						System.out.println("Please type in the key:  ");
						key = inputReader.nextLine();
						bst.delete(key);
						dll.delete(key);
						skip.delete(key);
						System.out.println("deleted:  " + choice);
						break;
					case insert:
						System.out.println("Please type in the key:  ");
						key = inputReader.nextLine();
						bst.insert(key,null);
						dll.insert(key,null);
						skip.insert(key,null);
						break;
					case max:
						if (bst.size() == 0) {
							System.out.println("The ADT's are emtpy!");
							break;
						}
						System.out.println("maximum of binary search tree:  " + ((BNode<String>)bst.maximum()).getKey());
						System.out.println("maximum of doubly-linked list:  " + ((DNode<String>)dll.maximum()).getValue());
						System.out.println("maximum of skip list:  " + ((SkipListEntry<String>)skip.maximum()));
						break;
					case min:
						if (bst.size() == 0) {
							System.out.println("The ADT's are emtpy!");
							break;
						}
						System.out.println("minimum of binary search tree:  " + ((BNode<String>)bst.minimum()).getKey());
						System.out.println("minimum of doubly-linked list:  " + ((DNode<String>)dll.minimum()).getValue());
						System.out.println("minimum of skip list:  " + ((SkipListEntry<String>)skip.minimum()));
						break;
					case pred:
						if (bst.size() == 0) {
							System.out.println("The ADT's are emtpy! ise empty!");
							break;
						}
						System.out.println("Please type in the key:  ");
						key = inputReader.nextLine();
						Object node1 = bst.predecessor(key);
						if (node1 == null) { //if there is none for bst, there shouldn't be any for the others
							System.out.println("The key has no predecessor.");
							 break;
						}
						else {
							System.out.println("Predecessor of binary search tree:  " + ((BNode<String>)bst.predecessor(key)).getKey());
							System.out.println("Predecessor of doubly-linked list:  " + ((DNode<String>)dll.predecessor(key)).getValue());
							System.out.println("Predecessor of skip list:  " + ((SkipListEntry<String>)skip.predecessor(key)));
						}
						break;
					case runtest:
						runTest(array,randomValues);
						break;
					case search:
						System.out.println("Please type in the key:  ");
						key = inputReader.nextLine();
						if (bst.size() == 0) {
							System.out.println("The ADT's are emtpy!");
							break;
						}
						Object search = bst.search(key);
						if (search == null) { //if there is none for bst, there shouldn't be any for the others
							System.out.println("The key does not exist");
							 break;
						}
						else {
							System.out.println("Found key:  " + ((BNode<String>)bst.search(key)).getKey() + " in binary search tree");
							System.out.println("Found key:  " + ((DNode<String>)dll.search(key)).getValue() + " in doubly-linked list");
							System.out.println("Found key:  " + ((SkipListEntry<String>)skip.search(key))+ " in skip list");
						}
						break;
					case succ:
						if (bst.size() == 0) {
							System.out.println("The ADT's are emtpy!");
							break;
						}
						System.out.println("Please type in the key:  ");
						key = inputReader.nextLine();
						Object node = bst.successor(key);
						if (node == null) { //if there is none for bst, there shouldn't be any for the others
							System.out.println("The key has no predecessor.");
							 break;
						}
						else {
							System.out.println("Successor of binary search tree:  " + ((BNode<String>)bst.successor(key)).getKey());
							System.out.println("Successor of doubly-linked list:  " + ((DNode<String>)dll.successor(key)).getValue());
							System.out.println("Successor of skip list:  " + ((SkipListEntry<String>)skip.successor(key)));
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
					default:
						break;
				}
			}}
		}
	}
	
	public static void runTest(String[] array, String[] randomValues) {
		
		DynamicSet<String> set = new BTree<String>();
		
		System.out.println("");
		System.out.println("Testing bst \n----------");
		System.out.println("insert");
		Utils.testSet(set, array, Utils.INSERT);
		System.out.println("search");
		Utils.testSet(set,randomValues,Utils.SEARCH);
		System.out.println("successor");
		Utils.testSet(set,randomValues,Utils.SUCCESSOR);
		System.out.println("predecessor");
		Utils.testSet(set,randomValues,Utils.PREDECESSOR);
		System.out.println("minimum");
		Utils.testMinMax(set, Utils.MINIMUM);
		System.out.println("maximum");
		Utils.testMinMax(set, Utils.MAXIMUM);		
		System.out.println("");
		
		set = new DLinkedList<String>();
		System.out.println("Testing dll \n------------");
		System.out.println("insert");
		Utils.testSet(set, array, Utils.INSERT);
		System.out.println("search");
		Utils.testSet(set,randomValues,Utils.SEARCH);
		System.out.println("successor");
		Utils.testSet(set,randomValues,Utils.SUCCESSOR);
		System.out.println("predecessor");
		Utils.testSet(set,randomValues,Utils.PREDECESSOR);
		System.out.println("minimum");
		Utils.testMinMax(set, Utils.MINIMUM);
		System.out.println("maximum");
		Utils.testMinMax(set, Utils.MAXIMUM);
		System.out.println("");
		
		set = new SkipList<String>();
		System.out.println("Testing skip \n------------");
		System.out.println("insert");
		Utils.testSet(set, array, Utils.INSERT);
		System.out.println("search");
		Utils.testSet(set,randomValues,Utils.SEARCH);
		System.out.println("successor");
		Utils.testSet(set,randomValues,Utils.SUCCESSOR);
		System.out.println("predecessor");
		Utils.testSet(set,randomValues,Utils.PREDECESSOR);
		System.out.println("minimum");
		Utils.testMinMax(set, Utils.MINIMUM);
		System.out.println("maximum");
		Utils.testMinMax(set, Utils.MAXIMUM);
		System.out.println("");
	}
	public static String loadFile() {
		String fileName;			
		System.out.println("\nPlease type in file name to read, or quit to exit:");
		fileName = inputReader.nextLine();
		//check if file exists
		if (fileName.equals("quit")) {
			System.out.println("Goodbye!");
			inputReader.close();
			return "quit";
		}
		File file = new File(fileName);
		int index = 0;
		try {
			//create an array based on the file name
			if (fileName.matches(".*100\\.txt")) {
				array = new String[100];
			}
			else if(fileName.matches(".*1000\\.txt")) {
				array = new String[1000];
			}
			else if(fileName.matches(".*10000\\.txt")) {
				array = new String[10000];
			}
			else if(fileName.matches(".*100000\\.txt")) {
				array = new String[100000];
			}
			else if(fileName.matches(".*1000000\\.txt")) {
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
			System.out.println("File not found.  Please try again.");
			return "error";
		}
	}
}
