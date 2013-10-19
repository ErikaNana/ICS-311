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
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String fileName;
		Scanner inputReader = new Scanner(System.in);
		String key;
			
		System.out.println("Please type in file name to read:");
		fileName = inputReader.nextLine();
		//check if file exists
			
		File file = new File(fileName);
		int index = 0;
		try {
			//create an array based on the file name
			String [] array = null;
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
			System.out.println("The file exists!");

			//read file in line by line
			Scanner fileReader = new Scanner(br);
			fileReader.useDelimiter("(\n)");
			
			while (fileReader.hasNext()) {
				array[index] = fileReader.next();
				index++;
			}
			fileReader.close();
			//generate an array of random strings
			String[] randomValues = new String[10];
			
			for (int i = 0; i < 10; i++) {
				int random = new Random().nextInt(array.length);
				randomValues[i] = array[random];
			}
			DynamicSet<String> bst = new BTree<String>();
			DynamicSet<String> dll = new DLinkedList<String>();
			DynamicSet<String> skip = new SkipList<String>();
			
			while (true) {
				System.out.println("\nWhat would you like to do?");
				System.out.println("Options available:");
				System.out.println("runtest, insert, search, delete, pred, succ, min, max");
				
				String choice = inputReader.nextLine();
				if (!Utils.isEnum(choice)) {
					System.out.println("That command is not recognized");
					continue;
				}
				switch(Utils.Command.valueOf(choice)) {
				case delete:
					if (bst.size() == 0) {
						System.out.println("The tree is empty!");
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
						System.out.println("The tree is empty!");
						break;
					}
					System.out.println("maximum of binary search tree:  " + ((BNode<String>)bst.maximum()).getKey());
					System.out.println("maximum of doubly-linked list:  " + ((DNode<String>)dll.maximum()).getValue());
					System.out.println("maximum of skip list:  " + ((SkipListEntry<String>)skip.maximum()));
					break;
				case min:
					if (bst.size() == 0) {
						System.out.println("The tree is empty!");
						break;
					}
					System.out.println("minimum of binary search tree:  " + ((BNode<String>)bst.minimum()).getKey());
					System.out.println("minimum of doubly-linked list:  " + ((DNode<String>)dll.minimum()).getValue());
					System.out.println("minimum of skip list:  " + ((SkipListEntry<String>)skip.minimum()));
					break;
				case pred:
					if (bst.size() == 0) {
						System.out.println("The tree ise empty!");
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
						System.out.println("predecessor of binary search tree:  " + ((BNode<String>)bst.predecessor(key)).getKey());
						System.out.println("predecessor of doubly-linked list:  " + ((DNode<String>)dll.predecessor(key)).getValue());
						System.out.println("predecessor of skip list:  " + ((SkipListEntry<String>)skip.predecessor(key)));
					}

					break;
				case runtest:
					runTest(array,randomValues);
					break;
				case search:
					System.out.println("Please type in the key:  ");
					key = inputReader.nextLine();
					if (bst.size() == 0) {
						System.out.println("The tree is empty!");
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
						System.out.println("The tree is empty!");
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
						System.out.println("successor of binary search tree:  " + ((BNode<String>)bst.successor(key)).getKey());
						System.out.println("successor of doubly-linked list:  " + ((DNode<String>)dll.successor(key)).getValue());
						System.out.println("successor of skip list:  " + ((SkipListEntry<String>)skip.successor(key)));
					}
					break;
				case quit:
					System.out.println("Goodbye!");
					inputReader.close();
					return;
				default:
					System.out.println("That command is not recognized");
					break;
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found.  Please try again. \n");
		}
	}
	
	public static void runTest(String[] array, String[] randomValues) {
		DynamicSet<String> bst = new BTree<String>();
		DynamicSet<String> dll = new DLinkedList<String>();
		DynamicSet<String> skip = new SkipList<String>();
		
		System.out.println("");
		System.out.println("Testing bst \n----------");
		System.out.println("insert");
		Utils.testSet(bst, array, Utils.INSERT);
		System.out.println("search");
		Utils.testSet(bst,randomValues,Utils.SEARCH);
		System.out.println("successor");
		Utils.testSet(bst,randomValues,Utils.SUCCESSOR);
		System.out.println("predecessor");
		Utils.testSet(bst,randomValues,Utils.PREDECESSOR);
		System.out.println("minimum");
		Utils.testMinMax(bst, Utils.MINIMUM);
		System.out.println("maximum");
		Utils.testMinMax(bst, Utils.MAXIMUM);		
		System.out.println("");
		bst = null;
		
		System.out.println("Testing dll \n------------");
		System.out.println("insert");
		Utils.testSet(dll, array, Utils.INSERT);
		System.out.println("search");
		Utils.testSet(dll,randomValues,Utils.SEARCH);
		System.out.println("successor");
		Utils.testSet(dll,randomValues,Utils.SUCCESSOR);
		System.out.println("predecessor");
		Utils.testSet(dll,randomValues,Utils.PREDECESSOR);
		System.out.println("minimum");
		Utils.testMinMax(dll, Utils.MINIMUM);
		System.out.println("maximum");
		Utils.testMinMax(dll, Utils.MAXIMUM);
		System.out.println("");
		dll = null;
		
		System.out.println("Testing skip \n------------");
		System.out.println("insert");
		Utils.testSet(skip, array, Utils.INSERT);
		System.out.println("search");
		Utils.testSet(skip,randomValues,Utils.SEARCH);
		System.out.println("successor");
		Utils.testSet(skip,randomValues,Utils.SUCCESSOR);
		System.out.println("predecessor");
		Utils.testSet(skip,randomValues,Utils.PREDECESSOR);
		System.out.println("minimum");
		Utils.testMinMax(skip, Utils.MINIMUM);
		System.out.println("maximum");
		Utils.testMinMax(skip, Utils.MAXIMUM);
		skip = null;
		System.out.println("");
	}
}
